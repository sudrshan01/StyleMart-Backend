package com.styleMart.product.config;
import com.styleMart.product.model.Product;
import com.styleMart.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ProductDataLoader {

    private final ProductRepository productRepo;

    public ProductDataLoader(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @PostConstruct
    public void loadData() {
        if (productRepo.count() == 0) {
        productRepo.saveAll(List.of(
                Product.builder()
                        .name("Classic Crew Neck T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("MEN-TSHIRT-001")
                        .shortDescription("Soft cotton crew neck tee")
                        .description("Premium cotton crew neck t-shirt, breathable and comfortable for daily wear.")
                        .fabric("Cotton")
                        .colors(Set.of("Black", "White", "Navy Blue"))
                        .sizes(Set.of("S","M","L","XL"))
                        .price(799.0)
                        .discount(10.0)
                        .stock(50)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/1758818560959_t1.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t2.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/1758818562135_t3.jpeg"
                        ))
                        .tags("casual,summer,basic")
                        .occasion("Casual")
                        .gender("Men")
                        .build(),

                Product.builder()
                        .name("Slim Fit Polo T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("MEN-TSHIRT-002")
                        .shortDescription("Slim fit polo with buttoned collar")
                        .description("Stylish polo with slim fit design, perfect for casual outings and semi-formal looks.")
                        .fabric("Cotton Blend")
                        .colors(Set.of("Grey", "Maroon", "Blue"))
                        .sizes(Set.of("M","L","XL"))
                        .price(999.0)
                        .discount(15.0)
                        .stock(40)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t4.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t5.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t6.jpeg"
                        ))
                        .tags("polo,casual,slimfit")
                        .occasion("Casual")
                        .gender("Men")
                        .build(),

                Product.builder()
                        .name("Graphic Print T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("MEN-TSHIRT-003")
                        .shortDescription("Trendy graphic printed t-shirt")
                        .description("Bold graphic print design for men, ideal for streetwear and casual wear.")
                        .fabric("Polyester")
                        .colors(Set.of("Black","White"))
                        .sizes(Set.of("S","M","L"))
                        .price(699.0)
                        .discount(5.0)
                        .stock(60)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t7.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t8.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t9.jpeg"
                        ))
                        .tags("streetwear,graphic,casual")
                        .occasion("Casual")
                        .gender("Men")
                        .build(),

                Product.builder()
                        .name("Henley Neck T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("MEN-TSHIRT-004")
                        .shortDescription("Henley neck with button placket")
                        .description("Soft cotton Henley t-shirt with stylish button placket, great for casual outings.")
                        .fabric("Cotton")
                        .colors(Set.of("Olive","Brown"))
                        .sizes(Set.of("M","L","XL"))
                        .price(899.0)
                        .discount(20.0)
                        .stock(30)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t10.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t11.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t12.jpeg"
                        ))
                        .tags("henley,casual,stylish")
                        .occasion("Casual")
                        .gender("Men")
                        .build(),

                Product.builder()
                        .name("Full Sleeve Solid T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("MEN-TSHIRT-005")
                        .shortDescription("Solid color full sleeve t-shirt")
                        .description("Classic full sleeve t-shirt in solid color, suitable for layering or standalone wear.")
                        .fabric("Cotton Lycra")
                        .colors(Set.of("Black","White","Grey"))
                        .sizes(Set.of("S","M","L","XL"))
                        .price(849.0)
                        .discount(0.0)
                        .stock(35)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t13.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t14.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t15.jpeg"
                        ))
                        .tags("solid,fullsleeve,casual")
                        .occasion("Casual")
                        .gender("Men")
                        .build(),

                Product.builder()
                        .name("Sports Dry-Fit T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("MEN-TSHIRT-006")
                        .shortDescription("Dry-fit sports t-shirt for men")
                        .description("Lightweight sports t-shirt with moisture wicking fabric for gym and outdoor activities.")
                        .fabric("Polyester Dry-Fit")
                        .colors(Set.of("Blue","Red","Neon Green"))
                        .sizes(Set.of("M","L","XL"))
                        .price(1099.0)
                        .discount(10.0)
                        .stock(45)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t16.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t17.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t18.jpeg"
                        ))
                        .tags("sports,activewear,gym")
                        .occasion("Sports")
                        .gender("Men")
                        .build(),

                Product.builder()
                        .name("Round Neck Printed T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("WOMEN-TSHIRT-001")
                        .shortDescription("Printed casual t-shirt for women")
                        .description("Trendy round neck t-shirt with floral print, perfect for casual wear.")
                        .fabric("Cotton")
                        .colors(Set.of("Pink","White"))
                        .sizes(Set.of("S","M","L"))
                        .price(749.0)
                        .discount(5.0)
                        .stock(40)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/w1.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w2.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w3.jpeg"
                        ))
                        .tags("printed,casual,women")
                        .occasion("Casual")
                        .gender("Women")
                        .build(),

                Product.builder()
                        .name("Crop Top T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("WOMEN-TSHIRT-002")
                        .shortDescription("Stylish crop top style t-shirt")
                        .description("Fashionable crop top style t-shirt, designed for trendy casual wear.")
                        .fabric("Cotton Lycra")
                        .colors(Set.of("Black","White","Lavender"))
                        .sizes(Set.of("S","M"))
                        .price(899.0)
                        .discount(10.0)
                        .stock(25)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/w4.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w5.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w6.jpeg"
                        ))
                        .tags("crop,cute,stylish")
                        .occasion("Casual")
                        .gender("Women")
                        .build(),

                Product.builder()
                        .name("Oversized Boyfriend T-Shirt")
                        .category("Clothing")
                        .subcategory("T-Shirts")
                        .sku("WOMEN-TSHIRT-003")
                        .shortDescription("Oversized comfy boyfriend tee")
                        .description("Oversized fit t-shirt for women, designed for ultimate comfort and trendy looks.")
                        .fabric("Cotton Blend")
                        .colors(Set.of("Beige","Light Blue"))
                        .sizes(Set.of("M","L","XL"))
                        .price(999.0)
                        .discount(12.0)
                        .stock(20)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/w7.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w8.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w9.jpeg"
                        ))
                        .tags("oversized,casual,streetwear")
                        .occasion("Casual")
                        .gender("Women")
                        .build(),

                Product.builder()
                        .name("Classic Leather Handbag")
                        .category("Accessories")
                        .subcategory("Bags")
                        .sku("UNISEX-BAG-001")
                        .shortDescription("Premium unisex leather handbag")
                        .description("Durable unisex leather handbag with spacious compartments, suitable for office or casual use.")
                        .fabric("Leather")
                        .colors(Set.of("Black","Brown"))
                        .sizes(Set.of("One Size"))
                        .price(2499.0)
                        .discount(18.0)
                        .stock(15)
                        .mainImage("https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/h1.jpeg")
                        .galleryImages(Set.of(
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/h2.jpeg",
                                "https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/h3.jpeg"
                        ))
                        .tags("handbag,leather,unisex")
                        .occasion("Formal")
                        .gender("Unisex")
                        .build()
        ));
    }
}}
