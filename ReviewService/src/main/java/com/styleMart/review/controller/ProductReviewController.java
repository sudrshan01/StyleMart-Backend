package com.styleMart.review.controller;

import com.styleMart.review.dto.ProductReviewResponse;
import com.styleMart.review.model.ProductReview;
import com.styleMart.review.service.ProductReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/reviews")
public class ProductReviewController {

    private final ProductReviewService service;

    public ProductReviewController(ProductReviewService service) {
        this.service = service;
    }
    // Get all reviews
    @GetMapping
    public List<ProductReviewResponse> getAllReviews() {
        return service.getAllReviews();
    }
    @GetMapping("/page")
    public ResponseEntity<Page<ProductReviewResponse>> getReviewsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductReviewResponse> reviewsPage = service.getReviewsPage(pageable);
        return ResponseEntity.ok(reviewsPage);
    }

    // Get all reviews for a product
    @GetMapping("/product/{productId}")
    public List<ProductReview> getReviewsByProduct(@PathVariable Long productId) {
        return service.getReviewsByProduct(productId);
    }

    // Optional: Get all reviews by a user
    @GetMapping("/user/{userId}")
    public List<ProductReview> getReviewsByUser(@PathVariable Long userId) {
        return service.getReviewsByUser(userId);
    }

    // Add a new review
    @PostMapping
    public ProductReview addReview(@RequestBody ProductReview review) {
        return service.addReview(review);
    }

    // Update an existing review
    @PutMapping("/{id}")
    public ProductReview updateReview(@PathVariable Long id, @RequestBody ProductReview review) {
        return service.updateReview(id, review);
    }

    // Delete a review
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        service.deleteReview(id);
    }
}
