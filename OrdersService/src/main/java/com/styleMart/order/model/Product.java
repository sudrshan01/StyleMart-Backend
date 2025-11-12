package com.styleMart.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    @Column(nullable = false)
    private String name;

    private String category;
    private String subcategory;

    @Column(unique = true, nullable = false)
    private String sku; // Product Code

    // Descriptions
    @Column(length = 500)
    private String shortDescription;

    @Lob
    private String description;

    // Attributes
    private String fabric;
    private List<String> colors;

    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size")
    private List<String> sizes; // XS, S, M, L, XL, etc.

    // Pricing & Stock
    private Double price;
    private Double discount; // percentage
    private Integer stock;

    // Images
    private String mainImage; // path or URL

    @ElementCollection
    @CollectionTable(name = "product_gallery", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_path")
    private List<String> galleryImages; // multiple images

    // SEO / Tags
    private String tags;

    private String occasion; // Casual, Formal

}
