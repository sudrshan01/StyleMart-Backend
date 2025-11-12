package com.styleMart.user.repository;

import com.styleMart.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Optional: find user by email
   // User findByEmail(String email);
    Optional<User> findByEmail(String email);
}
