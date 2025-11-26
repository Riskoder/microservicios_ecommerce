package com.microservice.cart.microservice_cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.cart.microservice_cart.dto.CartItemRequestDto;
import com.microservice.cart.microservice_cart.models.Cart;
import com.microservice.cart.microservice_cart.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Carrito de Compras", description = "API para gestionar el carrito de compras de los usuarios")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Obtener carrito", description = "Obtiene el carrito actual de un usuario. Si no existe, crea uno vacío.")
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @Operation(summary = "Agregar producto", description = "Agrega un producto al carrito o incrementa su cantidad si ya existe.")
    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addToCart(
            @PathVariable String userId,
            @RequestBody CartItemRequestDto itemDto) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, itemDto));
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto específico del carrito.")
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Cart> removeFromCart(
            @PathVariable String userId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }

    @Operation(summary = "Actualizar cantidad", description = "Actualiza la cantidad de un producto específico.")
    @PutMapping("/{userId}/update/{productId}")
    public ResponseEntity<Cart> updateQuantity(
            @PathVariable String userId,
            @PathVariable Long productId,
            @Parameter(description = "Nueva cantidad") @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, productId, quantity));
    }

    @Operation(summary = "Vaciar carrito", description = "Elimina todos los productos del carrito.")
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
