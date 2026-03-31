package com.project.systemapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Product(
        Long id,
        String name,
        BigDecimal price,
        ProductStatus status,
        Long categoryId,
        Long supplierId,
        LocalDateTime createdAt
) {
    public Product {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}