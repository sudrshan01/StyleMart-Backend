package com.styleMart.review.config;

import com.styleMart.review.model.ProductReview;
import com.styleMart.review.repository.ProductReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    private final ProductReviewRepository repo;

    public DataLoader(ProductReviewRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void loadData() {
        if(repo.count() == 0) {
        repo.saveAll(List.of(
                // Product 1
                ProductReview.builder().userId(1L).title("Excellent Product").comment("Really liked it!").rating(4.5).productId(1L).build(),
                ProductReview.builder().userId(2L).title("Good Value").comment("Worth the price.").rating(4.0).productId(1L).build(),
                ProductReview.builder().userId(3L).title("Not Bad").comment("Could be better packaging.").rating(3.5).productId(1L).build(),

                // Product 2
                ProductReview.builder().userId(1L).title("Highly Recommend").comment("Loved it!").rating(5.0).productId(2L).build(),
                ProductReview.builder().userId(2L).title("Satisfactory").comment("Does the job.").rating(4.0).productId(2L).build(),

                // Product 3
                ProductReview.builder().userId(3L).title("Average").comment("Nothing special.").rating(3.0).productId(3L).build(),

                // Product 4
                ProductReview.builder().userId(1L).title("Fantastic").comment("Exceeded expectations!").rating(5.0).productId(4L).build(),
                ProductReview.builder().userId(2L).title("Nice Product").comment("Pretty decent.").rating(4.0).productId(4L).build(),
                ProductReview.builder().userId(3L).title("Okayish").comment("It is fine for the price.").rating(3.5).productId(4L).build(),

                // Product 5
                ProductReview.builder().userId(1L).title("Good Quality").comment("Happy with purchase.").rating(4.5).productId(5L).build(),

                // Product 6
                ProductReview.builder().userId(2L).title("Could Improve").comment("Expected better.").rating(3.0).productId(6L).build(),
                ProductReview.builder().userId(3L).title("Decent").comment("Acceptable for price.").rating(3.5).productId(6L).build(),

                // Product 7
                ProductReview.builder().userId(1L).title("Love it").comment("Very nice!").rating(5.0).productId(7L).build(),
                ProductReview.builder().userId(2L).title("Good").comment("Satisfied with it.").rating(4.0).productId(7L).build(),

                // Product 8
                ProductReview.builder().userId(3L).title("Mediocre").comment("Average product.").rating(3.0).productId(8L).build(),

                // Product 9
                ProductReview.builder().userId(1L).title("Excellent").comment("Highly satisfied.").rating(5.0).productId(9L).build(),
                ProductReview.builder().userId(2L).title("Good Enough").comment("Meets expectations.").rating(4.0).productId(9L).build(),

                // Product 10
                ProductReview.builder().userId(3L).title("Not Great").comment("Could be improved.").rating(2.5).productId(10L).build(),
                ProductReview.builder().userId(1L).title("Pretty Good").comment("Liked it.").rating(4.0).productId(10L).build(),
                ProductReview.builder().userId(2L).title("Satisfactory").comment("Does what it says.").rating(3.5).productId(10L).build()
        ));
    }
}}
