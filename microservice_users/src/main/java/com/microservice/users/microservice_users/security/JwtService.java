package com.microservice.users.microservice_users.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Servicio para validar y extraer información de tokens JWT
 * Este servicio permite al microservicio de Users validar tokens sin depender de Auth
 */
@Service
public class JwtService {

    @Value("${jwt.secret:stylepoint-secret-key-muy-segura-para-produccion-2024}")
    private String secret;

    /**
     * Genera la clave de firma usando el secret
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Extrae todos los claims del token JWT
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido o expirado: " + e.getMessage());
        }
    }

    /**
     * Extrae el email (subject) del token
     */
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extrae el userId del token
     */
    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    /**
     * Extrae el rol del token
     */
    public String extractRol(String token) {
        return extractAllClaims(token).get("rol", String.class);
    }

    /**
     * Extrae la fecha de expiración del token
     */
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    /**
     * Verifica si el token ha expirado
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Valida el token JWT
     * Verifica que el token sea válido y no haya expirado
     */
    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida el token y verifica que el email coincida
     */
    public boolean validateToken(String token, String email) {
        try {
            final String tokenEmail = extractEmail(token);
            return (tokenEmail.equals(email) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrae el token del header Authorization
     * Remueve el prefijo "Bearer " si está presente
     */
    public String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("Header Authorization inválido");
    }
}
