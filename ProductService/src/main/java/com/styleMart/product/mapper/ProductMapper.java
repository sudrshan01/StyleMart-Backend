package com.styleMart.product.mapper;

import com.styleMart.product.dto.*;
import com.styleMart.product.model.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .subcategory(product.getSubcategory())
                .sku(product.getSku())
                .shortDescription(product.getShortDescription())
                .description(product.getDescription())
                .fabric(product.getFabric())
                .colors(product.getColors() == null ? Set.of() : Set.copyOf(product.getColors()))
                .sizes(product.getSizes() == null ? Set.of() : Set.copyOf(product.getSizes()))
                .price(product.getPrice())
                .discount(product.getDiscount())
                .stock(product.getStock())
                .mainImage(product.getMainImage())
                .galleryImages(product.getGalleryImages() == null ? Set.of() : Set.copyOf(product.getGalleryImages()))
                .tags(product.getTags())
                .occasion(product.getOccasion())
                .gender(product.getGender())
                .build();
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .subcategory(dto.getSubcategory())
                .sku(dto.getSku())
                .shortDescription(dto.getShortDescription())
                .description(dto.getDescription())
                .fabric(dto.getFabric())
                .colors(dto.getColors())
                .sizes(dto.getSizes())
                .price(dto.getPrice())
                .discount(dto.getDiscount())
                .stock(dto.getStock())
                .mainImage(dto.getMainImage())
                .galleryImages(dto.getGalleryImages())
                .tags(dto.getTags())
                .occasion(dto.getOccasion())
                .gender(dto.getGender())
                .build();
    }

    public ProductWithReviewsDTO toWithReviewsDTO(Product product, List<ProductReview> reviews) {
        List<ProductReviewDTO> reviewDTOs = (reviews == null) ? Collections.emptyList() :
                reviews.stream()
                        .map(r -> ProductReviewDTO.builder()
                                .id(r.getId())
                                .productId(r.getProductId())
                                .userId(r.getUserId())
                                .rating(r.getRating())
                                .comment(r.getComment())
                                .build())
                        .collect(Collectors.toList());

        double avgRating = (reviewDTOs.isEmpty()) ? 0.0 :
                reviewDTOs.stream().mapToDouble(ProductReviewDTO::getRating).average().orElse(0.0);

        return ProductWithReviewsDTO.builder()
                .product(toDTO(product))
                .reviews(reviewDTOs)
                .averageRating(avgRating)
                .build();
    }

    public static void updateEntity(Product entity, Product updated) {
        entity.setName(updated.getName());
        entity.setCategory(updated.getCategory());
        entity.setSubcategory(updated.getSubcategory());
        entity.setSku(updated.getSku());
        entity.setShortDescription(updated.getShortDescription());
        entity.setDescription(updated.getDescription());
        entity.setFabric(updated.getFabric());
        entity.setColors(updated.getColors());
        entity.setSizes(updated.getSizes());
        entity.setPrice(updated.getPrice());
        entity.setDiscount(updated.getDiscount());
        entity.setStock(updated.getStock());
        entity.setMainImage(updated.getMainImage());
        entity.setGalleryImages(updated.getGalleryImages());
        entity.setTags(updated.getTags());
        entity.setOccasion(updated.getOccasion());
        entity.setGender(updated.getGender());
    }
}
