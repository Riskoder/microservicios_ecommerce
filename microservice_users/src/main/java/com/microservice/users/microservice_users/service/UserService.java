package com.microservice.users.microservice_users.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.users.microservice_users.client.AuthClient;
import com.microservice.users.microservice_users.dto.AuthUserInfo;
import com.microservice.users.microservice_users.dto.UserDetailDto;
import com.microservice.users.microservice_users.dto.UserListDto;
import com.microservice.users.microservice_users.dto.UserResponse;
import com.microservice.users.microservice_users.model.User;
import com.microservice.users.microservice_users.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthClient authClient;

    // Verificar si el usuario es admin usando el token
    private void verifyAdminRole(String token) {
        try {
            AuthUserInfo authInfo = authClient.validateToken(token);
            if (!"admin".equalsIgnoreCase(authInfo.getRol())) {
                throw new RuntimeException("Acceso denegado: Solo administradores");
            }
        } catch (Exception e) {
            throw new RuntimeException("Token inválido o expirado");
        }
    }

    // Obtener lista simple de usuarios (solo para admins)
    public List<UserListDto> getAllUsers(String token) {
        verifyAdminRole(token);
        
        return userRepository.findAll().stream()
            .map(user -> new UserListDto(
                user.getId(),
                user.getNombre(),
                user.getRol()
            ))
            .collect(Collectors.toList());
    }

    // Obtener información detallada de usuarios (solo para admins)
    public List<UserDetailDto> getAllUsersDetailed(String token) {
        verifyAdminRole(token);
        
        return userRepository.findAll().stream()
            .map(user -> new UserDetailDto(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRol(),
                user.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }

    // Obtener usuario por ID (cualquier usuario autenticado puede ver su propio perfil)
    public UserResponse getUserById(Long id, String token) {
        // Validar token
        AuthUserInfo authInfo = authClient.validateToken(token);
        
        // Verificar que el usuario está accediendo a su propio perfil o es admin
        if (!authInfo.getUserId().equals(id) && !"admin".equalsIgnoreCase(authInfo.getRol())) {
            throw new RuntimeException("No tienes permisos para ver este perfil");
        }

        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UserResponse(
            user.getId(),
            user.getNombre(),
            user.getEmail(),
            user.getRol(),
            user.getCreatedAt()
        );
    }

    // Buscar usuarios por nombre o email (solo admins)
    public List<UserDetailDto> searchUsers(String query, String token) {
        verifyAdminRole(token);
        
        return userRepository.findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query)
            .stream()
            .map(user -> new UserDetailDto(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRol(),
                user.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }

    // Filtrar usuarios por rol (solo admins)
    public List<UserDetailDto> getUsersByRole(String rol, String token) {
        verifyAdminRole(token);
        
        return userRepository.findByRolIgnoreCase(rol).stream()
            .map(user -> new UserDetailDto(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRol(),
                user.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }

    // Actualizar rol de usuario (solo admins)
    @Transactional
    public UserResponse updateUserRole(Long id, String newRole, String token) {
        verifyAdminRole(token);
        
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar que el rol sea válido
        if (!"admin".equalsIgnoreCase(newRole) && !"user".equalsIgnoreCase(newRole)) {
            throw new RuntimeException("Rol inválido. Debe ser 'admin' o 'user'");
        }

        user.setRol(newRole.toLowerCase());
        User updated = userRepository.save(user);

        return new UserResponse(
            updated.getId(),
            updated.getNombre(),
            updated.getEmail(),
            updated.getRol(),
            updated.getCreatedAt()
        );
    }

    // Eliminar usuario (solo admins)
    @Transactional
    public void deleteUser(Long id, String token) {
        verifyAdminRole(token);
        
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }

    // Sincronizar usuario desde Auth (llamado cuando se registra un usuario)
    @Transactional
    public User syncUserFromAuth(Long authUserId, String nombre, String email, String rol) {
        // Verificar si ya existe
        if (userRepository.existsById(authUserId)) {
            return userRepository.findById(authUserId).get();
        }

        // Crear nuevo usuario
        User user = new User();
        user.setId(authUserId);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setRol(rol);

        return userRepository.save(user);
    }
}
