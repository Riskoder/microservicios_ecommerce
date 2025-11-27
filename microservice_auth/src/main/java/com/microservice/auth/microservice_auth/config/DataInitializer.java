package com.microservice.auth.microservice_auth.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservice.auth.microservice_auth.model.User;
import com.microservice.auth.microservice_auth.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAuthData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                // Usuario Admin predeterminado
                User admin = new User();
                admin.setNombre("Administrador");
                admin.setEmail("admin@stylepoint.com");
                admin.setPassword(passwordEncoder.encode("Admin123!"));
                admin.setRol("admin");
                userRepository.save(admin);

                // Usuario normal de prueba
                User user = new User();
                user.setNombre("Usuario Demo");
                user.setEmail("user@stylepoint.com");
                user.setPassword(passwordEncoder.encode("User123!"));
                user.setRol("user");
                userRepository.save(user);

                System.out.println("   Usuarios iniciales creados:");
                System.out.println("   Admin: admin@stylepoint.com / Admin123!");
                System.out.println("   User:  user@stylepoint.com / User123!");
            }
        };
    }
}