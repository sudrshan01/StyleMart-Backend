package com.styleMart.review.client;

import com.styleMart.review.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
//@FeignClient(name = "user-service", url = "http://localhost:8083")
@FeignClient(name = "user-service", url = "http://user-service:8083") // replace with actual URL
public interface UserClient {

    @GetMapping("/users/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}
