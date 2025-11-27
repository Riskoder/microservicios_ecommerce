package com.microservice.users.microservice_users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// ===== USER LIST DTO (para lista de usuarios - solo id, nombre, rol) =====
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDto {
    private Long id;
    private String nombre;
    private String rol;
}
