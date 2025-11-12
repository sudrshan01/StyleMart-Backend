package com.styleMart.user.service;

import com.styleMart.user.dto.LoginResponseDto;
import com.styleMart.user.dto.UserReqDto;
import com.styleMart.user.dto.UserResDto;
import com.styleMart.user.dto.UserReviewResDto;
import com.styleMart.user.exception.UserNotFoundException;
import com.styleMart.user.mapper.UserMapper;
import com.styleMart.user.model.User;
import com.styleMart.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
private  final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper=userMapper;
    }

    // Create user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    // Get user by id
    public UserReviewResDto getUserById(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        UserReviewResDto dto=userMapper.toDto(user);
        return dto;
    }
    public UserResDto getUserByIdd(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        UserResDto dto  =userMapper.toUserDto(user);
        return dto;
    }
    public UserResDto updateUser(Long id, UserResDto updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(existingUser.getPassword());
        existingUser.setRole(existingUser.getRole());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());

        User savedUser = userRepository.save(existingUser);

        return UserMapper.toUserDto(savedUser); // map to DTO
    }
    public UserResDto updateUserAdmin(Long id, UserReqDto updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (updatedUser.getUsername() != null)
            existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getEmail() != null)
            existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getGender() != null)
            existingUser.setGender(updatedUser.getGender());
        if (updatedUser.getPhoneNumber() != null)
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getFirstName() != null)
            existingUser.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null)
            existingUser.setLastName(updatedUser.getLastName());
        if (updatedUser.getDateOfBirth() != null)
            existingUser.setDateOfBirth(updatedUser.getDateOfBirth());

        // ✅ Fix: update role correctly
        if (updatedUser.getRole() != null)
            existingUser.setRole(updatedUser.getRole());

        User savedUser = userRepository.save(existingUser);
        return userMapper.toUserDto(savedUser);
    }


    // Delete user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
    // Login method
    public LoginResponseDto loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

        if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid password");
        }

        return new LoginResponseDto(user.getId(), "Login successful");
    }

}
