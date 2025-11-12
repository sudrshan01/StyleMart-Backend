package com.styleMart.cart.mapper;

import com.styleMart.cart.dto.CartDTO;
import com.styleMart.cart.dto.CartItemDTO;
import com.styleMart.cart.model.Cart;
import com.styleMart.cart.model.CartItem;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(cart.getItems().stream()
                        .map(CartMapper::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static CartItemDTO toDTO(CartItem item) {
        return CartItemDTO.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .price(item.getPrice())
                .size(item.getSize())
                .color(item.getColor())
                .quantity(item.getQuantity())
                .discount(item.getDiscount())
                .build();
    }
}
