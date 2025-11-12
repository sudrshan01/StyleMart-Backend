package com.styleMart.cart.controller;

import com.styleMart.cart.dto.CartDTO;
import com.styleMart.cart.dto.CartItemDTO;
import com.styleMart.cart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add item to cart
    @PostMapping("/{userId}/items")
    public CartDTO addItem(@PathVariable Long userId,
                           @RequestBody CartItemDTO item) throws Exception {
        return cartService.addItem(userId, item);
    }

    // Get cart for a user
    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable Long userId) throws Exception {
        return cartService.getCartByUserId(userId);
    }

    // Update cart item quantity
    @PutMapping("/{userId}/items/{itemId}")
    public CartDTO updateItem(@PathVariable Long userId,
                              @PathVariable Long itemId,
                              @RequestParam int quantity) throws Exception {
        return cartService.updateItem(userId, itemId, quantity);
    }

    // Remove item from cart
    @DeleteMapping("/{userId}/items/{itemId}")
    public CartDTO removeItem(@PathVariable Long userId,
                              @PathVariable Long itemId) throws Exception {
        return cartService.removeItem(userId, itemId);
    }

    // Clear entire cart
    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }

    // Get all carts
    @GetMapping
    public List<CartDTO> getAllCarts() throws Exception {
        return cartService.getAllCarts();
    }
}
