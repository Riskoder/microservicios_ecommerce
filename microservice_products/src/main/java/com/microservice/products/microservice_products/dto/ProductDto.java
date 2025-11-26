package com.microservice.products.microservice_products.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Modelo de transferencia de datos para la información de Productos")
public record ProductDto(
    @Schema(description = "Identificador único del producto en la base de datos", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    Long id,

    @Schema(description = "Título o nombre comercial del producto", example = "Laptop Gamer X200")
    String title,

    @Schema(description = "Precio unitario del producto", example = "1299.99")
    BigDecimal price,

    @Schema(description = "Descripción detallada de las características del producto", example = "Laptop de alto rendimiento con tarjeta gráfica dedicada...")
    String description,

    @Schema(description = "Categoría a la que pertenece el producto", example = "laptops")
    String category,

    @Schema(description = "URL de la imagen principal (miniatura)", example = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg")
    String thumbnail,

    @Schema(description = "Lista de URLs de imágenes adicionales del producto")
    List<String> images,

    @Schema(description = "Calificación promedio del producto (0-5)", example = "4.69")
    Double rating,

    @Schema(description = "Porcentaje de descuento aplicable", example = "12.5")
    Double discountPercentage,

    @Schema(description = "Cantidad de unidades disponibles en inventario", example = "50")
    Integer stock
) {}