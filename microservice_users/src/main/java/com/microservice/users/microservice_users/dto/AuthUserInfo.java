package com.microservice.users.microservice_users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserInfo {
    private Long userId;
    private String email;
    private String rol;
}
