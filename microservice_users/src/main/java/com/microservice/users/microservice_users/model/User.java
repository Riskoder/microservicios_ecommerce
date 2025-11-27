package com.microservice.users.microservice_users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String rol = "user"; // "admin" o "user"

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // NO incluimos password aquí porque es responsabilidad del microservicio de Auth
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}