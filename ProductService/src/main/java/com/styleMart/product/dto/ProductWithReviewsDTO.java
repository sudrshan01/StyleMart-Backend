package com.styleMart.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductWithReviewsDTO {
    private ProductDTO product;             // Product details
    private List<ProductReviewDTO> reviews; // List of reviews
    private double averageRating;           // Average rating
}
