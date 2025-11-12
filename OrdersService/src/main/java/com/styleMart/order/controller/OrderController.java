
package com.styleMart.order.controller;

import com.styleMart.order.dto.OrderDTO;
import com.styleMart.order.model.Product;
import com.styleMart.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ Place new order (from cart)
    @PostMapping("/place/{userId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long userId, @RequestBody Map<String, Object> requestBody) throws Exception {
        Long addressId = Long.parseLong(String.valueOf(requestBody.get("addressId")));
        String paymentMethod = String.valueOf(requestBody.get("paymentMethod"));
        OrderDTO orderDTO = orderService.placeOrder(userId, addressId, paymentMethod);
        return ResponseEntity.ok(orderDTO);
    }



    // ✅ Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) throws Exception {
        OrderDTO orderDTO = orderService.getOrder(orderId);
        return ResponseEntity.ok(orderDTO);
    }

    // ✅ Get all orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // ✅ Get orders by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) {
        List<OrderDTO> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    // ✅ Update order
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId,
                                                @RequestBody OrderDTO updatedOrder) throws Exception {
        OrderDTO orderDTO = orderService.updateOrder(orderId, updatedOrder);
        return ResponseEntity.ok(orderDTO);
    }

    // ✅ Delete order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get product by ID (from Product Service)
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = orderService.getProductById(productId);
        return ResponseEntity.ok(product);
    }
}

