package com.styleMart.user.mapper;

import com.styleMart.user.dto.UserResDto;
import com.styleMart.user.dto.UserReviewResDto;
import com.styleMart.user.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserMapper {

    // Map single User entity to UserDto
    public static UserReviewResDto toDto(User user) {
        if (user == null) return null;

        UserReviewResDto dto = new UserReviewResDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername()); // map 'name' in entity to 'username' in DTO
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    // Map list of User entities to list of UserDto
    public static List<UserReviewResDto> toDtoList(List<User> users) {
        if (users == null) return null;

        List<UserReviewResDto> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(toDto(user));
        }
        return dtoList;
    }

    // Map UserDto to User entity
    public static User toEntity(UserReviewResDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername()); // map 'username' to 'name'
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }

    // Map list of UserDto to list of User entities
    public static List<User> toEntityList(List<UserReviewResDto> dtos) {
        if (dtos == null) return null;

        List<User> users = new ArrayList<>();
        for (UserReviewResDto dto : dtos) {
            users.add(toEntity(dto));
        }
        return users;
    }
    public static UserResDto toUserDto(User user) {
        if (user == null) return null;

        UserResDto dto = new UserResDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setGender(user.getGender());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setDateOfBirth(user.getDateOfBirth());

        return dto;
    }

    // Convert UserResDto to User entity
    public static User toUserEntity(UserResDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDateOfBirth(dto.getDateOfBirth());

        return user;
    }
}
