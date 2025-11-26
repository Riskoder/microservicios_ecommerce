package com.microservice.cart.microservice_cart.dto;

import java.math.BigDecimal;

public record ProductExternalDto(
    Long id,
    String title,
    BigDecimal price,
    String thumbnail,
    String category,
    Double discountPercentage,
    Integer stock
) {}