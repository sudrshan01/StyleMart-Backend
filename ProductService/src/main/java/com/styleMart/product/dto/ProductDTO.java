package com.styleMart.product.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private String subcategory;
    private String sku;
    private String shortDescription;
    private String description;
    private String fabric;
    private Set<String> colors;
    private Set<String> sizes;
    private Double price;
    private Double discount;
    private Integer stock;
    private String mainImage;
    private Set<String> galleryImages;
    private String tags;
    private String occasion;
    private String gender;
}
