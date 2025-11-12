package com.styleMart.cart.client;

import com.styleMart.cart.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://user-service:8083")
public interface UserClient {

    @GetMapping("/users/{userId}")
    User getUserById(@PathVariable("userId") Long userId);
}
