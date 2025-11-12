package com.styleMart.review.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReviewResponse {
    private Long id;
    private Long productId;
    private String title;       // Added title
    private String comment;
    private Double rating;

    // User info
    private Long userId;
    private String username;
    private String email;
    private String phoneNumber;

    private LocalDateTime createdAt;   // Added createdAt
}
