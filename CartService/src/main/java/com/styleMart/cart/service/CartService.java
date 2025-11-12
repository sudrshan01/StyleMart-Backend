package com.styleMart.cart.service;

import com.styleMart.cart.client.ProductClient;
import com.styleMart.cart.client.UserClient;
import com.styleMart.cart.dto.CartDTO;
import com.styleMart.cart.dto.CartItemDTO;
import com.styleMart.cart.exception.ItemNotFoundException;
import com.styleMart.cart.exception.ProductNotFoundException;
import com.styleMart.cart.exception.UserNotFoundException;
import com.styleMart.cart.mapper.CartMapper;
import com.styleMart.cart.model.Cart;
import com.styleMart.cart.model.CartItem;
import com.styleMart.cart.model.Product;
import com.styleMart.cart.model.User;
import com.styleMart.cart.repository.CartRepository;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "carts")
public class CartService {

    private final CartRepository cartRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    public CartService(CartRepository cartRepository, UserClient userClient, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    // Add item to cart
    @Caching(
            put = @CachePut(key = "#userId"),
            evict = @CacheEvict(key = "'all'")
    )
    public CartDTO addItem(Long userId, CartItemDTO itemDTO) throws Exception {
        User user = userClient.getUserById(userId);
        if (user == null) throw new UserNotFoundException(userId);

        Product product = productClient.getProductById(itemDTO.getProductId());
        if (product == null) throw new ProductNotFoundException(itemDTO.getProductId());

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(Cart.builder().userId(userId).items(new ArrayList<>()).build());

        CartItem item = CartItem.builder()
                .productId(itemDTO.getProductId())
                .productName(product.getName())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .size(itemDTO.getSize() != null ? itemDTO.getSize() : "Default")
                .color(itemDTO.getColor() != null ? itemDTO.getColor() :
                        (product.getColors() != null && !product.getColors().isEmpty() ? product.getColors().get(0) : "Default"))
                .quantity(itemDTO.getQuantity() > 0 ? itemDTO.getQuantity() : 1)
                .cart(cart)
                .build();

        Optional<CartItem> existing = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(item.getProductId())
                        && i.getSize().equals(item.getSize())
                        && i.getColor().equals(item.getColor()))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + item.getQuantity());
        } else {
            cart.getItems().add(item);
        }

        cart = cartRepository.save(cart);

        return CartMapper.toDTO(cart);
    }

    // Get cart by userId
    @Cacheable(key = "#userId")
    public CartDTO getCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByUserId(userId)
                .orElse(Cart.builder().userId(userId).items(new ArrayList<>()).build());
        return CartMapper.toDTO(cart);
    }

    // Update item quantity
    @Caching(
            put = @CachePut(key = "#userId"),
            evict = @CacheEvict(key = "'all'")
    )
    public CartDTO updateItem(Long userId, Long itemId, int quantity) throws Exception {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        item.setQuantity(quantity);
        cart = cartRepository.save(cart);

        return CartMapper.toDTO(cart);
    }

    // Remove item
    @Caching(
            put = @CachePut(key = "#userId"),
            evict = @CacheEvict(key = "'all'")
    )
    public CartDTO removeItem(Long userId, Long itemId) throws Exception {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        boolean removed = cart.getItems().removeIf(i -> i.getId().equals(itemId));
        if (!removed) throw new ItemNotFoundException(itemId);

        cart = cartRepository.save(cart);

        return CartMapper.toDTO(cart);
    }

    // Clear cart
    @Caching(
            evict = {
                    @CacheEvict(key = "#userId"),
                    @CacheEvict(key = "'all'")
            }
    )
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    // Get all carts
    @Cacheable(key = "'all'")
    public List<CartDTO> getAllCarts() throws Exception {
        List<CartDTO> allCarts = cartRepository.findAll().stream()
                .map(CartMapper::toDTO)
                .toList();
        return allCarts;
    }
}
