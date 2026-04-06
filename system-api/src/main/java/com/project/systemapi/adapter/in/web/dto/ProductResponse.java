package com.project.systemapi.adapter.in.web.dto;

import com.project.systemapi.domain.model.Product;
import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        String status
) {
    public static ProductResponse from(Product p) {
        return new ProductResponse(
                p.id(),
                p.name(),
                p.price(),
                p.status().name()
        );
    }
}