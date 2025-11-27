package com.microservice.users.microservice_users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource; // <-- Importación necesaria

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Spring inyectará automáticamente el bean 'corsConfigurationSource' de CorsConfig.
    // Lo recibirá como argumento en este método.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource)) // <-- Aquí se usa el bean inyectado
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().permitAll()
            );

        return http.build();
    }
    
    // -------------------------------------------------------------------
    // ¡ELIMINE ESTE MÉTODO COMPLETO! ¡ES EL DUPLICADO!
    /*
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        // ... (resto de la lógica) ...
        return source;
    }
    */
    // -------------------------------------------------------------------
}