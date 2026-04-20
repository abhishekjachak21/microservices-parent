package com.project.middleware.adapter.out.client.dto;

import io.micrometer.observation.annotation.Observed;

import java.math.BigDecimal;

@Observed(name = "product.get", contextualName = "get-product")
public record ProductDTO(
        Long id,
        String name,
        BigDecimal price,
        String status,
        Long categoryId,
        Long supplierId
) {}