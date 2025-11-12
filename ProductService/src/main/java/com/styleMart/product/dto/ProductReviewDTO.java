package com.styleMart.product.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReviewDTO {
    private Long id;
    private Long productId;
    private Long userId;
      // Optional, if you fetch user info
    private Double rating;

    private String comment;
}
