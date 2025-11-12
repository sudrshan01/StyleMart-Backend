package com.styleMart.order.client;

import com.styleMart.order.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "http://product-service:8081")
public interface ProductClient {

    @GetMapping("/products/{productId}/product")
    Product getProductById(@PathVariable("productId") Long productId);

    @PutMapping("/products/{productId}/decrease-stock")
    void decreaseStock(@PathVariable Long productId, @RequestParam int quantity);

    @PutMapping("/products/{productId}/increase-stock")
    void increaseStock(@PathVariable Long productId, @RequestParam int quantity);

}
