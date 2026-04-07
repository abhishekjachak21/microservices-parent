package com.project.middleware.adapter.out.client.dto;


import java.math.BigDecimal;

public record CreateProductDTO(
        String name,
        BigDecimal price
) {}