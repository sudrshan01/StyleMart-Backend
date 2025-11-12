package com.styleMart.user.dto;

import com.styleMart.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReqDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String gender;
    private String email;
private Role role;
    private String password;

    private String phoneNumber;

    private LocalDate dateOfBirth;
}
