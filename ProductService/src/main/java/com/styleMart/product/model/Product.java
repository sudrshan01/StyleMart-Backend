package com.styleMart.product.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Multiple Colors
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "product_colors",
            joinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_product_colors"))
    )
    @Column(name = "color")
    @Fetch(FetchMode.SUBSELECT)
    private Set<String> colors = new HashSet<>();

    // Multiple Sizes
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "product_sizes",
            joinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_product_sizes"))
    )
    @Column(name = "size")
    @Fetch(FetchMode.SUBSELECT)
    private Set<String> sizes = new HashSet<>();

    // Pricing & Stock
    private Double price;
    private Double discount; // percentage
    private Integer stock;

    // Images
    private String mainImage; // path or URL

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "product_gallery",
            joinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_product_gallery"))
    )
    @Column(name = "image_path")
    @Fetch(FetchMode.SUBSELECT)
    private Set<String> galleryImages = new HashSet<>();

    // SEO / Tags
    private String tags;

    private String occasion; // Casual, Formal

    @Column(nullable = false)
    private String gender;
}
