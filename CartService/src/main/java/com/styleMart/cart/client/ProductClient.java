package com.styleMart.cart.client;

import com.styleMart.cart.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "product-service", url = "http://localhost:8081")

@FeignClient(name = "product-service", url = "http://product-service:8081")
public interface ProductClient {

    @GetMapping("/products/{productId}/product")
    Product getProductById(@PathVariable("productId") Long productId);
}
