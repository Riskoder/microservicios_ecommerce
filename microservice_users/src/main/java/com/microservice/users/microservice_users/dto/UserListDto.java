package com.microservice.users.microservice_users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDto {
    private Long id;
    private String nombre;
    private String rol;
}
