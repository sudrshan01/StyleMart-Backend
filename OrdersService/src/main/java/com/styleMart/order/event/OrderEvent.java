package com.styleMart.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private String productName;
    private int quantity;
    private Double totalAmount;
    private String status; // CREATED, CONFIRMED, etc.
    private OffsetDateTime deliveryDate; // ✅ NEW
    private String paymentMethod;
}
