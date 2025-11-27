package com.microservice.users.microservice_users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.users.microservice_users.dto.UpdateRoleRequest;
import com.microservice.users.microservice_users.dto.UserDetailDto;
import com.microservice.users.microservice_users.dto.UserListDto;
import com.microservice.users.microservice_users.dto.UserResponse;
import com.microservice.users.microservice_users.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Obtener lista simple de usuarios (solo admins)
    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String token) {
        try {
            List<UserListDto> users = userService.getAllUsers(token);
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(java.util.Map.of("mensaje", e.getMessage()));
        }
    }

    // Obtener información detallada de usuarios (solo admins)
    @GetMapping("/detailed")
    public ResponseEntity<?> getAllUsersDetailed(@RequestHeader("Authorization") String token) {
        try {
            List<UserDetailDto> users = userService.getAllUsersDetailed(token);
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(java.util.Map.of("mensaje", e.getMessage()));
        }
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
        @PathVariable Long id,
        @RequestHeader("Authorization") String token
    ) {
        try {
            UserResponse user = userService.getUserById(id, token);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(java.util.Map.of("mensaje", e.getMessage()));
        }
    }

    // Buscar usuarios (solo admins)
    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(
        @RequestParam String query,
        @RequestHeader("Authorization") String token
    ) {
        try {
            List<UserDetailDto> users = userService.searchUsers(query, token);
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(java.util.Map.of("mensaje", e.getMessage()));
        }
    }

    // Filtrar por rol (solo admins)
    @GetMapping("/by-role")
    public ResponseEntity<?> getUsersByRole(
        @RequestParam String role,
        @RequestHeader("Authorization") String token
    ) {
        try {
            List<UserDetailDto> users = userService.getUsersByRole(role, token);
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(java.util.Map.of("mensaje", e.getMessage()));
        }
    }

    // Actualizar rol (solo admins)
    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateUserRole(
        @PathVariable Long id,
        @RequestBody UpdateRoleRequest request,
        @RequestHeader("Authorization") String token
    ) {
        try {
            UserResponse updated = userService.updateUserRole(id, request.getRol(), token);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(java.util.Map.of("mensaje", e.getMessage()));
        }
    }

    // Eliminar usuario (solo admins)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
        @PathVariable Long id,
        @RequestHeader("Authorization") String token
    ) {
        try {
            userService.deleteUser(id, token);
            return ResponseEntity.ok(java.util.Map.of("mensaje", "Usuario eliminado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(java.util.Map.of("mensaje", e.getMessage()));
        }
    }
}