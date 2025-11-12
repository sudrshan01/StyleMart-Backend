package com.styleMart.product.repository;

import com.styleMart.product.model.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.colors LEFT JOIN FETCH p.sizes LEFT JOIN FETCH p.galleryImages")
    List<Product> findAllSafe();

}
