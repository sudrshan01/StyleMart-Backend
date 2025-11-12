package com.styleMart.product.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(Long id, int quantity) {
        super("Not enough stock for product id " + id + ". Requested: " + quantity);
    }
}
