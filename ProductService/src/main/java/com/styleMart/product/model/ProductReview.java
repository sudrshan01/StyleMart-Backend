package com.styleMart.product.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductReview {
    private Long id;
    private Long userId;
    private String comment;
    private Double rating;
    private Long productId;
}
