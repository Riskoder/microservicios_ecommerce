package com.microservice.cart.microservice_cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.cart.microservice_cart.dto.CartItemRequestDto;
import com.microservice.cart.microservice_cart.models.Cart;
import com.microservice.cart.microservice_cart.models.CartItem;
import com.microservice.cart.microservice_cart.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
class CartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartService cartService;

    private final String USER_ID = "user-test-123";

    @Test
    @DisplayName("Debe obtener el carrito retornado por el servicio")
    void testGetCart_CreatesNew() throws Exception {
        Cart emptyCart = Cart.builder()
                .userId(USER_ID)
                .items(Collections.emptyList())
                .build();

        given(cartService.getCartByUserId(USER_ID)).willReturn(emptyCart);

        mockMvc.perform(get("/v1/cart/{userId}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(USER_ID)))
                .andExpect(jsonPath("$.items", hasSize(0)));
    }

    @Test
    @DisplayName("Debe devolver el carrito con el nuevo item agregado")
    void testAddToCart_NewProduct() throws Exception {
        Long productId = 10L;
        CartItemRequestDto requestDto = new CartItemRequestDto(productId, 2);

        CartItem item = CartItem.builder()
                .productId(productId)
                .title("iPhone 15")
                .price(new BigDecimal("999.99"))
                .quantity(2)
                .build();

        Cart cartWithItem = Cart.builder()
                .userId(USER_ID)
                .items(List.of(item))
                .build();

        given(cartService.addItemToCart(eq(USER_ID), any(CartItemRequestDto.class)))
                .willReturn(cartWithItem);

        mockMvc.perform(post("/v1/cart/{userId}/add", USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items", hasSize(1)))
                .andExpect(jsonPath("$.items[0].productId", is(productId.intValue())))
                .andExpect(jsonPath("$.items[0].title", is("iPhone 15")))
                .andExpect(jsonPath("$.items[0].quantity", is(2)));
    }

    @Test
    @DisplayName("Debe devolver el carrito con la cantidad incrementada")
    void testAddToCart_ExistingProduct() throws Exception {
        Long productId = 10L;
        CartItemRequestDto requestDto = new CartItemRequestDto(productId, 1);

        CartItem item = CartItem.builder()
                .productId(productId)
                .title("iPhone 15")
                .quantity(3)
                .price(new BigDecimal("999.99"))
                .build();

        Cart cartUpdated = Cart.builder()
                .userId(USER_ID)
                .items(List.of(item))
                .build();

        given(cartService.addItemToCart(eq(USER_ID), any(CartItemRequestDto.class)))
                .willReturn(cartUpdated);

        mockMvc.perform(post("/v1/cart/{userId}/add", USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].quantity", is(3)));
    }

    @Test
    @DisplayName("Debe eliminar un producto del carrito usando el servicio")
    void testRemoveFromCart() throws Exception {
        Long productId = 10L;
        Cart emptyCart = Cart.builder()
                .userId(USER_ID)
                .items(Collections.emptyList())
                .build();

        given(cartService.removeItemFromCart(USER_ID, productId)).willReturn(emptyCart);

        mockMvc.perform(delete("/v1/cart/{userId}/remove/{productId}", USER_ID, productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items", hasSize(0)));
    }

    @Test
    @DisplayName("Debe llamar al servicio para vaciar el carrito")
    void testClearCart() throws Exception {
        doNothing().when(cartService).clearCart(USER_ID);

        mockMvc.perform(delete("/v1/cart/{userId}/clear", USER_ID))
                .andExpect(status().isNoContent());
    }
}