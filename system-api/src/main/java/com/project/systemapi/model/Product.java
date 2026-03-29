package com.project.systemapi.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;

    public Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product create(String name, BigDecimal price) {
        return new Product(UUID.randomUUID(), name, price);
    }

}
