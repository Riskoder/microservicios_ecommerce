package com.microservice.auth.microservice_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que recibe la información del usuario desde Auth Service
 * Debe coincidir exactamente con el DTO del microservicio Auth
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserInfo {
    private Long userId;
    private String email;
    private String rol;
}
