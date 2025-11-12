package com.styleMart.order.dto;

import com.styleMart.order.model.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private Long userId;
    private OffsetDateTime createdAt;
    private OrderStatus status;
    private Double totalAmount;
    private List<OrderItemDTO> items;
    private OffsetDateTime deliveryDate;
    private String paymentMethod;
    private Long addressId;

}
