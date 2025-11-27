package com.microservice.products.microservice_products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.products.microservice_products.dto.ProductDto;
import com.microservice.products.microservice_products.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para la gestión de productos y catálogo")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista completa de productos, o filtrada por una lista de categorías si se especifica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductDto.class))),
        @ApiResponse(responseCode = "204", description = "No se encontraron productos")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false) List<String> categories
    ) {
        if (categories != null && !categories.isEmpty()) {
            return ResponseEntity.ok(productService.getProductsByCategoryList(categories));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Buscar producto por ID", description = "Recupera la información detallada de un producto específico mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductDto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Crear nuevo producto", description = "Registra un nuevo producto en el catálogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza la información (precio, stock, descripción, etc.) de un producto existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductDto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado para actualizar")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @Operation(summary = "Eliminar producto", description = "Elimina permanentemente un producto del sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}