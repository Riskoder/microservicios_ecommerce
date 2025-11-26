package com.microservice.cart.microservice_cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.cart.microservice_cart.client.ProductClient;
import com.microservice.cart.microservice_cart.dto.CartItemRequestDto;
import com.microservice.cart.microservice_cart.dto.ProductExternalDto;
import com.microservice.cart.microservice_cart.models.Cart;
import com.microservice.cart.microservice_cart.repository.CartRepository;
import com.microservice.cart.microservice_cart.models.CartItem;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> createEmptyCart(userId));
    }

    private Cart createEmptyCart(String userId) {
        Cart cart = Cart.builder()
                .userId(userId)
                .items(new ArrayList<>())
                .build();
        return cartRepository.save(cart);
    }

    public Cart addItemToCart(String userId, CartItemRequestDto itemDto) {
        Cart cart = getCartByUserId(userId);
        
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(itemDto.productId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + itemDto.quantity());
        } else {
            
            try {
                ProductExternalDto productInfo = productClient.getProductById(itemDto.productId());
                
                CartItem newItem = CartItem.builder()
                        .productId(productInfo.id())
                        .title(productInfo.title())
                        .price(productInfo.price())
                        .thumbnail(productInfo.thumbnail())
                        .quantity(itemDto.quantity())
                        .discountPercentage(productInfo.discountPercentage())
                        .build();
                
                cart.getItems().add(newItem);
                
            } catch (Exception e) {
                throw new RuntimeException("Error al buscar el producto con ID: " + itemDto.productId() + ". Verifique que exista.");
            }
        }

        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(String userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }

    public Cart updateItemQuantity(String userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        
        cart.getItems().stream()
            .filter(item -> item.getProductId().equals(productId))
            .findFirst()
            .ifPresent(item -> {
                if(quantity > 0) {
                    item.setQuantity(quantity);
                }
            });
            
        cart.getItems().removeIf(item -> item.getQuantity() <= 0);
        
        return cartRepository.save(cart);
    }

    public void clearCart(String userId) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
