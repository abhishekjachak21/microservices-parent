package com.project.systemapi.adapter.in.web.dto;

import java.math.BigDecimal;
import java.util.UUID;
import com.project.systemapi.domain.model.ProductStatus;

// Java 21 Record DTO
public record ProductDTO(
        UUID id,
        String name,
        BigDecimal price,
        ProductStatus status
) {}