package com.styleMart.order.exception;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException(Long userId) {
        super("Cart is empty for user with id: " + userId);
    }
}
