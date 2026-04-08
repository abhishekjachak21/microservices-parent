package com.project.middleware.adapter.in.web.dto;


import java.math.BigDecimal;

public record ProductDetailView(
        Long id,
        String name,
        BigDecimal price,
        String categoryName,
        String supplierName,
        BigDecimal tax
) {}
