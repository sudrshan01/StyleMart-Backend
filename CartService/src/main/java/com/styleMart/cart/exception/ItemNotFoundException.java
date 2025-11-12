package com.styleMart.cart.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long itemId) {
        super("Cart item not found with ID: " + itemId);
    }
}
