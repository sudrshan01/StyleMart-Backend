package com.styleMart.review.mapper;
import com.styleMart.review.dto.ProductReviewResponse;
import com.styleMart.review.dto.UserDto;
import com.styleMart.review.model.ProductReview;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewMapper {

    // Map entity + user info to response DTO
    public  ProductReviewResponse toResponse(ProductReview review, UserDto user) {
        if (review == null || user == null) return null;

        return ProductReviewResponse.builder()
                .id(review.getId())
                .productId(review.getProductId())
                .title(review.getTitle())
                .comment(review.getComment())
                .rating(review.getRating())
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // Optional: Map DTO back to entity (useful if you create a create/update DTO)
    public  ProductReview toEntity(ProductReview review) {
        if (review == null) return null;

        return ProductReview.builder()
                .id(review.getId())
                .productId(review.getProductId())
                .title(review.getTitle())
                .comment(review.getComment())
                .rating(review.getRating())
                .userId(review.getUserId())
                .build();
    }
}
