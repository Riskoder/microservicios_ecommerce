package com.microservice.auth.microservice_auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.auth.microservice_auth.dto.AuthResponse;
import com.microservice.auth.microservice_auth.dto.AuthUserInfo;
import com.microservice.auth.microservice_auth.dto.ErrorResponse;
import com.microservice.auth.microservice_auth.dto.LoginRequest;
import com.microservice.auth.microservice_auth.dto.RegisterRequest;
import com.microservice.auth.microservice_auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
        }
    }

    /**
     * ✅ ENDPOINT ACTUALIZADO: Ahora retorna AuthUserInfo
     * Este endpoint es llamado por el microservicio Users vía OpenFeign
     */
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            // Verificar que el header Authorization existe y tiene formato correcto
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Token no proporcionado", HttpStatus.BAD_REQUEST.value()));
            }

            // Extraer el token (quitar "Bearer ")
            String token = authHeader.substring(7);

            // Validar token y obtener información del usuario
            AuthUserInfo userInfo = authService.validateToken(token);

            // Retornar información del usuario autenticado
            return ResponseEntity.ok(userInfo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
        }
    }
}