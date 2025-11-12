package com.styleMart.review.service;

import com.styleMart.review.client.UserClient;
import com.styleMart.review.dto.UserDto;
import com.styleMart.review.dto.ProductReviewResponse;
import com.styleMart.review.exception.ReviewNotFoundException;
import com.styleMart.review.mapper.ProductReviewMapper;
import com.styleMart.review.model.ProductReview;
import com.styleMart.review.repository.ProductReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductReviewService {

    private final ProductReviewRepository repo;
    private final UserClient userClient;
  private final   ProductReviewMapper productReviewMapper;
    public ProductReviewService(ProductReviewRepository repo, UserClient userClient ,ProductReviewMapper productReviewMapper) {
        this.repo = repo;
        this.userClient = userClient;
        this.productReviewMapper=productReviewMapper;
    }

    // Get all reviews with user info
    public List<ProductReviewResponse> getAllReviews() {
        List<ProductReview> reviews = repo.findAll();
        List<ProductReviewResponse> responseList = new ArrayList<>();

        for (ProductReview review : reviews) {
            UserDto user = userClient.getUserById(review.getUserId());
            responseList.add(productReviewMapper.toResponse(review, user));
        }

        return responseList;
    }


    // Other methods remain the same
    public List<ProductReview> getReviewsByProduct(Long productId) {



        return repo.findByProductId(productId);
    }

    public List<ProductReview> getReviewsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public ProductReview addReview(ProductReview review) {
        return repo.save(review);
    }

    public ProductReview updateReview(Long id, ProductReview review) {
        ProductReview existing = repo.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        existing.setUserId(review.getUserId());
        existing.setComment(review.getComment());
        existing.setRating(review.getRating());
        existing.setProductId(review.getProductId());
        return repo.save(existing);
    }

    public void deleteReview(Long id) {
        if (!repo.existsById(id)) {
            throw new ReviewNotFoundException(id);
        }
        repo.deleteById(id);
    }
    public Page<ProductReviewResponse> getReviewsPage(Pageable pageable) {
        Page<ProductReview> reviewsPage = repo.findAll(pageable);

        return reviewsPage.map(review -> {
            UserDto user = userClient.getUserById(review.getUserId());
            return productReviewMapper.toResponse(review, user);
        });
    }
}
