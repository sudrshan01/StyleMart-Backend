package com.styleMart.user.repository;
import com.styleMart.user.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Get all addresses for a specific user
    List<Address> findByUserId(Long userId);

    // Optional: Get default address for a user
    Address findByUserIdAndIsDefaultTrue(Long userId);
}
