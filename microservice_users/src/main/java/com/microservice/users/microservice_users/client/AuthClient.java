package com.microservice.users.microservice_users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.microservice.users.microservice_users.dto.AuthUserInfo;

@FeignClient(
    name = "auth-service",
    url = "${auth.service.url:http://localhost:8081}"
)
public interface AuthClient {

    @PostMapping("/v1/auth/validate")
    AuthUserInfo validateToken(@RequestHeader("Authorization") String token);
}
