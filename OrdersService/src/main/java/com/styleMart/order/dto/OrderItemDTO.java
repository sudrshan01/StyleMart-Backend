package com.styleMart.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private Double price;
    private String size;
    private String color;
    private int quantity;
}
