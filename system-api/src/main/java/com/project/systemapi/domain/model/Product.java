package com.project.systemapi.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;

    public static Product create(String name, BigDecimal price) {
        return new Product(UUID.randomUUID(), name, price);
    }
}