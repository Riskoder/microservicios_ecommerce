package com.microservice.users.microservice_users.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private LocalDateTime createdAt;
}
