package com.styleMart.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
// Enum declaration inside or outside User class
public enum Role {
    ADMIN,
    USER,
    SELLER,
    MANAGER
}
