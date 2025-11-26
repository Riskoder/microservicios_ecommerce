package com.microservice.products.microservice_products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.products.microservice_products.dto.ProductDto;
import com.microservice.products.microservice_products.models.Product;
import com.microservice.products.microservice_products.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategoryList(List<String> categories) {
        return productRepository.findByCategoryIn(categories).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return mapToDto(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existingProduct.setTitle(productDto.title());
        existingProduct.setPrice(productDto.price());
        existingProduct.setDescription(productDto.description());
        existingProduct.setCategory(productDto.category());
        existingProduct.setThumbnail(productDto.thumbnail());
        existingProduct.setImages(productDto.images());
        existingProduct.setStock(productDto.stock());
        
        if (productDto.rating() != null) existingProduct.setRating(productDto.rating());
        if (productDto.discountPercentage() != null) existingProduct.setDiscountPercentage(productDto.discountPercentage());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDto(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id);
    }


    private ProductDto mapToDto(Product product) {
        return new ProductDto(
            product.getId(),
            product.getTitle(),
            product.getPrice(),
            product.getDescription(),
            product.getCategory(),
            product.getThumbnail(),
            product.getImages(),
            product.getRating(),
            product.getDiscountPercentage(),
            product.getStock()
        );
    }

    private Product mapToEntity(ProductDto dto) {
        return Product.builder()
            .id(dto.id())
            .title(dto.title())
            .price(dto.price())
            .description(dto.description())
            .category(dto.category())
            .thumbnail(dto.thumbnail())
            .images(dto.images())
            .rating(dto.rating())
            .discountPercentage(dto.discountPercentage())
            .stock(dto.stock())
            .build();
    }
}