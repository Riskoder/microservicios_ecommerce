package com.microservice.auth.microservice_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.auth.microservice_auth.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Buscar usuario por email
    Optional<User> findByEmail(String email);
    
    // Verificar si existe un email
    boolean existsByEmail(String email);
}