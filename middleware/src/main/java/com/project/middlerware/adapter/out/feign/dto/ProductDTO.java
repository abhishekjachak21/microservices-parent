package com.project.middlerware.adapter.out.feign.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        BigDecimal price,
        String status    // System API se JSON aa raha hota hai → String
) {}
