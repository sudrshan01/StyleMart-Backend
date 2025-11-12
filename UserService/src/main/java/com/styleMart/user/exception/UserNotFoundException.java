package com.styleMart.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }

    // Add this constructor
    public UserNotFoundException(String message) {
        super(message);
    }
}
