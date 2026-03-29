package com.project.systemapi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
public class ProductJpaEntity {

    @Id
    private UUID id;

    private String name;
    private BigDecimal price;

    // getters/setters
}