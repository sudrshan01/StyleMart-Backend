package com.styleMart.user.config;

import com.styleMart.user.model.Role;
import com.styleMart.user.model.User;
import com.styleMart.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader {

    private final UserRepository repo;

    public DataLoader(UserRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void loadData() {
        if (repo.count() == 0) {
            repo.saveAll(List.of(
                    User.builder()
                            .firstName("Alice")
                            .lastName("Doe")
                            .username("Alice")
                            .gender("Women")
                            .email("admin@example.com")
                            .password("admin123")
                            .phoneNumber("9876543210")
                            .dateOfBirth(LocalDate.of(2002, 5, 14))
                            .role(Role.ADMIN)
                            .build(),

                    User.builder()
                            .firstName("Eve")
                            .lastName("GG")
                            .username("Eve")
                            .gender("Women")
                            .email("eve@example.com")
                            .password("eve123")
                            .phoneNumber("9876543214")
                            .dateOfBirth(null)
                            .role(Role.ADMIN)
                            .build(),

                    User.builder()
                            .firstName("John")
                            .lastName("Doe")
                            .username("johndoe")
                            .gender("MALE")
                            .email("johndoe@example.com")
                            .password("davidpass")
                            .phoneNumber("9876543213")
                            .dateOfBirth(LocalDate.of(1990, 1, 1))
                            .role(Role.USER)
                            .build()
            ));

            System.out.println("✅ Default users inserted successfully.");
        } else {
            System.out.println("ℹ️ Users already exist — skipping data load.");
        }
    }
}
