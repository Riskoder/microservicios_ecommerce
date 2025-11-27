package com.microservice.users.microservice_users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.microservice.users.microservice_users.dto.AuthUserInfo;

/**
 * Cliente Feign para comunicarse con el Microservicio de Autenticación
 * URL configurable desde application.properties con: auth.service.url
 */
@FeignClient(
    name = "auth-service",
    url = "${auth.service.url:http://localhost:8081}"
)
public interface AuthClient {

    /**
     * Valida un token JWT en el servicio de autenticación
     * 
     * @param token Token JWT en formato "Bearer {token}"
     * @return Información del usuario autenticado (userId, email, rol)
     */
    @PostMapping("/v1/auth/validate")
    AuthUserInfo validateToken(@RequestHeader("Authorization") String token);
}
