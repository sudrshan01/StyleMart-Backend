package com.styleMart.user.controller;

import com.styleMart.user.dto.*;
import com.styleMart.user.model.User;
import com.styleMart.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody LoginRequestDto loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }
    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by id
    @GetMapping("/user/{id}")
    public UserReviewResDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/{id}")
    public UserResDto getUserByIdd(@PathVariable Long id) {
        return userService.getUserByIdd(id);
    }

    // Update user
    @PutMapping("/{id}")
    public UserResDto updateUser(@PathVariable Long id, @RequestBody UserResDto user) {
        return userService.updateUser(id, user);
    }

    @PutMapping("/admin/{id}")
    public UserResDto updateUserAdmin(@PathVariable Long id, @RequestBody UserReqDto user) {
        return userService.updateUserAdmin(id, user);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
