package com.styleMart.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.styleMart.product.dto.ProductWithReviewsDTO;
import com.styleMart.product.model.Product;
import com.styleMart.product.model.ProductReview;
import com.styleMart.product.service.ProductService;
import com.styleMart.product.service.S3Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final S3Service s3Service;

    public ProductController(ProductService productService, S3Service s3Service) {
        this.productService = productService;
        this.s3Service = s3Service;
    }

    // -------------------- GET ALL PRODUCTS --------------------
    @GetMapping
    public List<ProductWithReviewsDTO> getAllProductsWithReviews() {
        return productService.getAllProductsWithReviews();
    }

    // -------------------- GET SINGLE PRODUCT --------------------
    @GetMapping("/{id}")
    public ProductWithReviewsDTO getProduct(@PathVariable Long id) {
        return productService.getProductWithReviews(id);
    }

    // -------------------- GET ONLY PRODUCT ENTITY --------------------
    @GetMapping("/{id}/product")
    public Product get(@PathVariable Long id) {
        return productService.getById(id);
    }

    // -------------------- ADD PRODUCT --------------------
    @PostMapping(consumes = "multipart/form-data")
    public Product addProduct(
            @RequestPart("product") Product product,
            @RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
            @RequestPart(value = "galleryImages", required = false) MultipartFile[] galleryImages
    ) throws IOException {

        if (mainImage != null && !mainImage.isEmpty()) {
            product.setMainImage(s3Service.uploadFile(mainImage, "products"));
        }

        if (galleryImages != null && galleryImages.length > 0) {
            Set<String> urls = new HashSet<>();
            for (MultipartFile file : galleryImages) {
                if (!file.isEmpty()) {
                    urls.add(s3Service.uploadFile(file, "products/gallery"));
                }
            }
            product.setGalleryImages(urls);
        }

        return productService.addProduct(product);
    }

    // -------------------- UPDATE PRODUCT --------------------
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestPart("product") String productJson,
            @RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
            @RequestPart(value = "galleryImages", required = false) MultipartFile[] newGalleryFiles
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productJson, Product.class);
        return productService.updateProductWithImages(id, product, mainImage, newGalleryFiles);
    }

    // -------------------- DELETE PRODUCT --------------------
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // -------------------- STOCK MANAGEMENT --------------------
    @PutMapping("/{productId}/decrease-stock")
    public void decreaseStock(@PathVariable Long productId, @RequestParam int quantity) {
        productService.decreaseStock(productId, quantity);
    }

    @PutMapping("/{productId}/increase-stock")
    public void increaseStock(@PathVariable Long productId, @RequestParam int quantity) {
        productService.increaseStock(productId, quantity);
    }

    // -------------------- REVIEWS --------------------
    @GetMapping("/{id}/reviews")
    public List<ProductReview> getProductReviews(@PathVariable Long id) {
        return productService.getReviewsForProduct(id);
    }

    // -------------------- RATING --------------------
    @GetMapping("/{id}/rating")
    public double getAverageRating(@PathVariable Long id) {
        return productService.getAverageRating(id);
    }

    // -------------------- PAGINATION --------------------
    @GetMapping("/admin")
    public Page<Product> getAllProductsForAdmin(Pageable pageable) {
        return productService.getAllProductsForAdmin(pageable);
    }
}
