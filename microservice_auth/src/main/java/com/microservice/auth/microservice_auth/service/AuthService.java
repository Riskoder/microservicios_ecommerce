package com.microservice.auth.microservice_auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.auth.microservice_auth.dto.AuthResponse;
import com.microservice.auth.microservice_auth.dto.AuthUserInfo;
import com.microservice.auth.microservice_auth.dto.LoginRequest;
import com.microservice.auth.microservice_auth.dto.RegisterRequest;
import com.microservice.auth.microservice_auth.model.User;
import com.microservice.auth.microservice_auth.repository.UserRepository;
import com.microservice.auth.securirty.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Validar que las contraseñas coincidan
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }

        // Validar que el email no exista
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Este correo electrónico ya está registrado");
        }

        // Validar formato de email
        if (!isValidEmail(request.getEmail())) {
            throw new RuntimeException("Formato de email inválido");
        }

        // Validar longitud de nombre
        if (request.getNombre() == null || request.getNombre().length() < 3) {
            throw new RuntimeException("El nombre debe tener al menos 3 caracteres");
        }

        // Validar contraseña (mínimo 6 caracteres, con letras y números)
        if (!isValidPassword(request.getPassword())) {
            throw new RuntimeException("La contraseña debe tener al menos 6 caracteres, incluyendo letras y números");
        }

        // Crear usuario
        User user = new User();
        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRol("user"); // Por defecto es usuario normal

        User savedUser = userRepository.save(user);

        // Generar token JWT
        String token = jwtUtil.generateToken(
            savedUser.getEmail(),
            savedUser.getId(),
            savedUser.getRol()
        );

        return new AuthResponse(
            token,
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getNombre(),
            savedUser.getRol()
        );
    }

    public AuthResponse login(LoginRequest request) {
        // Buscar usuario por email
        User user = userRepository.findByEmail(request.getEmail().toLowerCase())
            .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

        // Verificar contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        // Generar token JWT
        String token = jwtUtil.generateToken(
            user.getEmail(),
            user.getId(),
            user.getRol()
        );

        return new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getNombre(),
            user.getRol()
        );
    }

    public AuthUserInfo validateToken(String token) {
        try {
            // Extraer información del token
            String email = jwtUtil.extractEmail(token);
            Long userId = jwtUtil.extractUserId(token);
            String rol = jwtUtil.extractRol(token);

            // Verificar que el usuario existe
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Validar el token
            if (!jwtUtil.validateToken(token, email)) {
                throw new RuntimeException("Token inválido o expirado");
            }

            // Retornar información del usuario
            return new AuthUserInfo(userId, email, rol);
        } catch (Exception e) {
            throw new RuntimeException("Token inválido: " + e.getMessage());
        }
    }

    // Validaciones auxiliares
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }

    private boolean isValidPassword(String password) {
        return password != null && 
               password.length() >= 6 && 
               password.matches(".*[a-zA-Z].*") && 
               password.matches(".*\\d.*");
    }
}