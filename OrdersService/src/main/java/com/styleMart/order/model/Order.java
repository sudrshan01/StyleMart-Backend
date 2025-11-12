package com.styleMart.order.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private OffsetDateTime createdAt;

    // ✅ Automatically set delivery date = createdAt + 8 days
    private OffsetDateTime deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Double totalAmount;

    // ✅ Add payment method (example: "CASH", "CARD", "UPI")
    private String paymentMethod;

    // ✅ Add address ID (foreign key to address table)
    private Long addressId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    // ✅ Automatically set createdAt & deliveryDate before saving
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        }
        if (deliveryDate == null) {
            deliveryDate = createdAt.plusDays(8);
        }
    }
}
