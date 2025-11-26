package com.microservice.cart.microservice_cart.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para agregar items. Solo requiere ID y cantidad, el resto se busca internamente.")
public record CartItemRequestDto(
    @Schema(description = "ID del producto a agregar", example = "10")
    Long productId,
    
    @Schema(description = "Cantidad a agregar", example = "1")
    Integer quantity
) {}
