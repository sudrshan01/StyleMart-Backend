package com.styleMart.product.client;

import com.styleMart.product.model.ProductReview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "review-service", url = "http://localhost:8082/reviews")
@FeignClient(name = "review-service", url = "http://review-service:8082")
public interface ReviewClient {

    @GetMapping("/reviews/product/{productId}")
    List<ProductReview> getReviewsByProduct(@PathVariable("productId") Long productId);

    @PostMapping("/reviews")
    ProductReview addReview(@RequestBody ProductReview review);

    @PutMapping("/reviews/{id}")
    ProductReview updateReview(@PathVariable("id") Long id, @RequestBody ProductReview review);

    @DeleteMapping("/reviews/{id}")
    void deleteReview(@PathVariable("id") Long id);
}
