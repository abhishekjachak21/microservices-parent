package com.project.middleware.adapter.out.client.dto;


import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        BigDecimal price,
        String status
) {}