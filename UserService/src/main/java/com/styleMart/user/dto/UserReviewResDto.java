package com.styleMart.user.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReviewResDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
}
