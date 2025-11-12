package com.styleMart.product.service;

import com.styleMart.product.client.ReviewClient;
import com.styleMart.product.dto.ProductWithReviewsDTO;
import com.styleMart.product.mapper.ProductMapper;
import com.styleMart.product.model.Product;
import com.styleMart.product.model.ProductReview;
import com.styleMart.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "products")
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ReviewClient reviewClient;
    private final S3Service s3Service;

    // -------------------- FETCH --------------------

    /**
     * Safely fetch all products + reviews.
     * Avoids MultipleBagFetchException and LazyInitializationException.
     */
    @Transactional(readOnly = true)
    @Cacheable(key = "'all'")
    public List<ProductWithReviewsDTO> getAllProductsWithReviews() {
        List<Product> products = productRepository.findAllSafe();

        products.forEach(product -> {
            Hibernate.initialize(product.getColors());
            Hibernate.initialize(product.getGalleryImages());
            Hibernate.initialize(product.getSizes());
        });

        return products.parallelStream().map(product -> {
            List<ProductReview> reviews = getReviewsForProduct(product.getId());
            return productMapper.toWithReviewsDTO(product, reviews);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    public ProductWithReviewsDTO getProductWithReviews(Long id) {
        Product product = getById(id);

        Hibernate.initialize(product.getColors());
        Hibernate.initialize(product.getGalleryImages());
        Hibernate.initialize(product.getSizes());

        List<ProductReview> reviews = getReviewsForProduct(id);
        return productMapper.toWithReviewsDTO(product, reviews);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "'p:' + #id")
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    // -------------------- CREATE / UPDATE / DELETE --------------------

    @Caching(
            put = @CachePut(key = "'p:' + #result.id"),
            evict = @CacheEvict(key = "'all'")
    )
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Caching(
            put = @CachePut(key = "'p:' + #id"),
            evict = @CacheEvict(key = "'all'")
    )
    @Transactional
    public Product updateProductWithImages(Long id, Product updated, MultipartFile mainImage, MultipartFile[] galleryFiles) throws IOException {
        Product existing = getById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setShortDescription(updated.getShortDescription());
        existing.setPrice(updated.getPrice());
        existing.setStock(updated.getStock());
        existing.setDiscount(updated.getDiscount());
        existing.setCategory(updated.getCategory());
        existing.setSubcategory(updated.getSubcategory());
        existing.setSku(updated.getSku());
        existing.setFabric(updated.getFabric());
        existing.setGender(updated.getGender());
        existing.setTags(updated.getTags());
        existing.setOccasion(updated.getOccasion());

        // ✅ Use Sets (not Lists)
        existing.setColors(Optional.ofNullable(updated.getColors()).orElseGet(HashSet::new));
        existing.setSizes(Optional.ofNullable(updated.getSizes()).orElseGet(HashSet::new));

        // ✅ Handle main image upload
        if (mainImage != null && !mainImage.isEmpty()) {
            existing.setMainImage(s3Service.uploadFile(mainImage, "products"));
        }

        // ✅ Handle gallery images upload safely with Set
        if (galleryFiles != null && galleryFiles.length > 0) {
            Set<String> gallery = new HashSet<>(Optional.ofNullable(existing.getGalleryImages()).orElseGet(HashSet::new));
            for (MultipartFile file : galleryFiles) {
                if (!file.isEmpty()) {
                    gallery.add(s3Service.uploadFile(file, "products/gallery"));
                }
            }
            existing.setGalleryImages(gallery);
        } else {
            existing.setGalleryImages(Optional.ofNullable(updated.getGalleryImages()).orElseGet(HashSet::new));
        }

        return productRepository.save(existing);
    }

    @Caching(evict = {
            @CacheEvict(key = "'p:' + #id"),
            @CacheEvict(key = "'all'")
    })
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // -------------------- STOCK --------------------
    @Transactional
    public void decreaseStock(Long productId, int quantity) {
        Product p = getById(productId);
        if (p.getStock() < quantity) throw new RuntimeException("Not enough stock");
        p.setStock(p.getStock() - quantity);
        productRepository.save(p);
    }

    @Transactional
    public void increaseStock(Long productId, int quantity) {
        Product p = getById(productId);
        p.setStock(p.getStock() + quantity);
        productRepository.save(p);
    }

    // -------------------- REVIEWS --------------------
    public List<ProductReview> getReviewsForProduct(Long productId) {
        try {
            return reviewClient.getReviewsByProduct(productId);
        } catch (Exception e) {
            System.err.println("⚠️ Error fetching reviews for product " + productId + ": " + e.getMessage());
            return List.of();
        }
    }

    public double getAverageRating(Long productId) {
        List<ProductReview> reviews = getReviewsForProduct(productId);
        return reviews.isEmpty() ? 0.0 :
                reviews.stream().mapToDouble(ProductReview::getRating).average().orElse(0.0);
    }

    // -------------------- ADMIN PAGINATION --------------------
    @Transactional(readOnly = true)
    public Page<Product> getAllProductsForAdmin(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);

        page.forEach(p -> {
            Hibernate.initialize(p.getColors());
            Hibernate.initialize(p.getSizes());
            Hibernate.initialize(p.getGalleryImages());
        });

        return page;
    }
}
