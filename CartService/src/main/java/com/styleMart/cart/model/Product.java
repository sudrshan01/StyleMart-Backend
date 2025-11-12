package com.styleMart.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id;

    // Basic Info
    private String name;
    private String category;
    private String subcategory;
    private String sku; // Product Code

    // Descriptions
    private String shortDescription;
    private String description;

    // Attributes
    private String fabric;
    private List<String> colors;
    private List<String> sizes; // XS, S, M, L, XL, etc.

    // Pricing & Stock
    private Double price;
    private Double discount; // percentage
    private Integer stock;

    // Images
    private String mainImage; // path or URL
    private List<String> galleryImages; // multiple images

    // SEO / Tags
    private String tags;
    private String occasion; // Casual, Formal
}
