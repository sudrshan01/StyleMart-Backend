package com.styleMart.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cart-service", url = "http://cart-service:8084")
public interface CartClient {

    @GetMapping("/carts/{userId}")
    CartDto getCartByUserId(@PathVariable("userId") Long userId);
    @DeleteMapping("/carts/{userId}/clear")
    void clearCart(@PathVariable("userId") Long userId);

    class CartDto {
        public Long id;
        public Long userId;
        public List<CartItemDto> items;
    }

    class CartItemDto {
        public Long id;
        public Long productId;
        public String productName;
        public Double price;
        public String size;
        public String color;
        public int quantity;
    }
}
