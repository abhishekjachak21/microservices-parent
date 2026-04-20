package com.project.middleware.adapter.in.web.dto;

import java.math.BigDecimal;

import io.micrometer.observation.annotation.Observed;

@Observed(name = "catalog.get-product-detail", contextualName = "get-product-detail")
public record ProductDetailResponse(
        Long id,
        String name,
        BigDecimal price,
        String categoryName,
        String supplierName,
        BigDecimal tax
) {}