package com.microservice.users.microservice_users.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.microservice.users.microservice_users.dto.UserDetailDto;
import com.microservice.users.microservice_users.dto.UserListDto;
import com.microservice.users.microservice_users.dto.UserResponse;
import com.microservice.users.microservice_users.model.User;
import com.microservice.users.microservice_users.repository.UserRepository;
import com.microservice.users.microservice_users.security.JwtService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Verifica si el usuario es admin usando el token JWT local
     */
    private void verifyAdminRole(String authHeader) {
        try {
            // Extraer token del header
            String token = jwtService.extractTokenFromHeader(authHeader);
            
            // Validar token
            if (!jwtService.validateToken(token)) {
                throw new RuntimeException("Token inválido o expirado");
            }
            
            // Extraer rol del token
            String rol = jwtService.extractRol(token);
            
            if (!"admin".equalsIgnoreCase(rol)) {
                throw new RuntimeException("Acceso denegado: Solo administradores");
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el token: " + e.getMessage());
        }
    }

    /**
     * Verifica que el usuario esté autenticado y retorna su información
     */
    private UserAuthInfo verifyAuthentication(String authHeader) {
        try {
            // Extraer token del header
            String token = jwtService.extractTokenFromHeader(authHeader);
            
            // Validar token
            if (!jwtService.validateToken(token)) {
                throw new RuntimeException("Token inválido o expirado");
            }
            
            // Extraer información del usuario
            Long userId = jwtService.extractUserId(token);
            String email = jwtService.extractEmail(token);
            String rol = jwtService.extractRol(token);
            
            return new UserAuthInfo(userId, email, rol);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el token: " + e.getMessage());
        }
    }

    // Clase interna para información de autenticación
    private static class UserAuthInfo {
        Long userId;
        String email;
        String rol;

        UserAuthInfo(Long userId, String email, String rol) {
            this.userId = userId;
            this.email = email;
            this.rol = rol;
        }
    }

    /**
     * Obtener lista simple de usuarios (solo para admins)
     */
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

    /**
     * Obtener información detallada de usuarios (solo para admins)
     */
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

    /**
     * Obtener usuario por ID (cualquier usuario autenticado puede ver su propio perfil)
     */
    public UserResponse getUserById(Long id, String token) {
        // Validar token y obtener información del usuario
        UserAuthInfo authInfo = verifyAuthentication(token);
        
        // Verificar que el usuario está accediendo a su propio perfil o es admin
        if (!authInfo.userId.equals(id) && !"admin".equalsIgnoreCase(authInfo.rol)) {
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

    /**
     * Buscar usuarios por nombre o email (solo admins)
     */
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

    /**
     * Filtrar usuarios por rol (solo admins)
     */
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

    /**
     * Actualizar rol de usuario (solo admins)
     */
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

    /**
     * Eliminar usuario (solo admins)
     */
    @Transactional
    public void deleteUser(Long id, String token) {
        verifyAdminRole(token);
        
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }

    /**
     * Sincronizar usuario desde Auth (llamado cuando se registra un usuario)
     */
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