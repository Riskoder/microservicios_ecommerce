package com.microservice.users.microservice_users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.users.microservice_users.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Buscar usuario por email
    Optional<User> findByEmail(String email);
    
    // Buscar usuarios por nombre o email (para búsqueda)
    List<User> findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(
        String nombre, 
        String email
    );
    
    // Filtrar usuarios por rol
    List<User> findByRolIgnoreCase(String rol);
    
    // Verificar si existe un email
    boolean existsByEmail(String email);
}