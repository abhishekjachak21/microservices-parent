package com.project.systemapi.adapter.in.web.dto;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "Name must not be blank")
        @Size(max = 200, message = "Name max length is 200")
        String name,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be >= 0.01")
        BigDecimal price
) {}