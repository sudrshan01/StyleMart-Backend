package com.styleMart.order.service;

import com.styleMart.order.dto.OrderDTO;
import com.styleMart.order.dto.OrderItemDTO;
import com.styleMart.order.event.OrderEvent;
import com.styleMart.order.exception.CartEmptyException;
import com.styleMart.order.exception.OrderNotFoundException;
import com.styleMart.order.client.CartClient;
import com.styleMart.order.client.ProductClient;
import com.styleMart.order.kafka.OrderProducer;
import com.styleMart.order.model.Order;
import com.styleMart.order.model.OrderItem;
import com.styleMart.order.model.OrderStatus;
import com.styleMart.order.model.Product;
import com.styleMart.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "orders") // ✅ Defines Redis cache name
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartClient cartClient;
    private final ProductClient productClient;
    private final OrderProducer orderProducer;

    // ✅ Place order
    @Transactional
    @CachePut(key = "#result.id") // cache the new order
    public OrderDTO placeOrder(Long userId, Long addressId, String paymentMethod) throws Exception {
        CartClient.CartDto cart = cartClient.getCartByUserId(userId);
        if (cart == null || cart.items == null || cart.items.isEmpty()) {
            throw new CartEmptyException(userId);
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setAddressId(addressId);
        order.setPaymentMethod(paymentMethod);
        order.setCreatedAt(OffsetDateTime.now());
        order.setDeliveryDate(order.getCreatedAt().plusDays(8));
        order.setStatus(OrderStatus.CREATED);

        double total = 0.0;
        StringBuilder productNames = new StringBuilder();
        int totalQty = 0;

        for (CartClient.CartItemDto ci : cart.items) {
            Product product = productClient.getProductById(ci.productId);

            double discountedPrice = ci.price;
            if (product.getDiscount() != null && product.getDiscount() > 0) {
                discountedPrice = ci.price - (ci.price * product.getDiscount() / 100);
            }

            OrderItem item = OrderItem.builder()
                    .productId(ci.productId)
                    .productName(ci.productName)
                    .price(discountedPrice)
                    .size(ci.size)
                    .color(ci.color)
                    .quantity(ci.quantity)
                    .order(order)
                    .build();

            order.getItems().add(item);

            total += discountedPrice * ci.quantity;
            totalQty += ci.quantity;
            if (productNames.length() > 0) productNames.append(", ");
            productNames.append(ci.productName);

            productClient.decreaseStock(ci.productId, ci.quantity);
        }

        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);

        // ✅ Clear Cart
        cartClient.clearCart(userId);

        // ✅ Send Kafka event
        orderProducer.sendOrderEvent(new OrderEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                productNames.toString(),
                totalQty,
                savedOrder.getTotalAmount(),
                savedOrder.getStatus().name(),
                savedOrder.getDeliveryDate(),
                savedOrder.getPaymentMethod()
        ));

        return mapToDTO(savedOrder);
    }

    // ✅ Get order (cached)
    @Cacheable(key = "#orderId")
    public OrderDTO getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        return mapToDTO(order);
    }

    // ✅ Get all orders (no cache)
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ✅ Get orders by user (no cache)
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ✅ Update order (refresh cache)
    @Transactional
    @CachePut(key = "#orderId")
    public OrderDTO updateOrder(Long orderId, OrderDTO updatedOrder) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (updatedOrder.getStatus() != null)
            order.setStatus(updatedOrder.getStatus());

        if (updatedOrder.getDeliveryDate() != null)
            order.setDeliveryDate(updatedOrder.getDeliveryDate());
        else if (order.getDeliveryDate() == null)
            order.setDeliveryDate(OffsetDateTime.now().plusDays(8));

        if (updatedOrder.getPaymentMethod() != null)
            order.setPaymentMethod(updatedOrder.getPaymentMethod());
        else if (order.getPaymentMethod() == null)
            order.setPaymentMethod("COD");

        Order savedOrder = orderRepository.save(order);

        orderProducer.sendOrderEvent(new OrderEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getItems().stream()
                        .map(OrderItem::getProductName)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse(""),
                savedOrder.getItems().stream()
                        .mapToInt(OrderItem::getQuantity)
                        .sum(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus().name(),
                savedOrder.getDeliveryDate(),
                savedOrder.getPaymentMethod()
        ));

        return mapToDTO(savedOrder);
    }

    // ✅ Delete order (evict from cache)
    @Transactional
    @CacheEvict(key = "#orderId")
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.getItems().forEach(item ->
                productClient.increaseStock(item.getProductId(), item.getQuantity())
        );

        orderRepository.delete(order);
    }

    // ✅ Helper: Convert Entity → DTO
    private OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .createdAt(order.getCreatedAt())
                .deliveryDate(order.getDeliveryDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .addressId(order.getAddressId())
                .items(order.getItems().stream().map(i ->
                        OrderItemDTO.builder()
                                .id(i.getId())
                                .productId(i.getProductId())
                                .productName(i.getProductName())
                                .price(i.getPrice())
                                .size(i.getSize())
                                .color(i.getColor())
                                .quantity(i.getQuantity())
                                .build()
                ).toList())
                .build();
    }

    // ✅ Fetch product info via Feign
    public Product getProductById(Long productId) {
        return productClient.getProductById(productId);
    }
}
