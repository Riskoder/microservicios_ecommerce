package com.microservice.cart.microservice_cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.cart.microservice_cart.dto.ProductExternalDto;

@FeignClient(name = "microservice-products", url = "https://xd6w381w-8083.use2.devtunnels.ms")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductExternalDto getProductById(@PathVariable("id") Long id);
}