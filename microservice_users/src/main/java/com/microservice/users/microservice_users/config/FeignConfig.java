package com.microservice.users.microservice_users.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                if (response.status() == HttpStatus.UNAUTHORIZED.value()) {
                    return new RuntimeException("Token inválido o expirado. Por favor, inicia sesión nuevamente.");
                }
                return new RuntimeException("Error al validar el token: " + response.status());
            }
        };
    }
}