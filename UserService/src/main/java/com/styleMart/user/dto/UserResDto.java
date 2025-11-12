package com.styleMart.user.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String gender;
    private String email;

    private String password;

    private String phoneNumber;

    private LocalDate dateOfBirth;
}
