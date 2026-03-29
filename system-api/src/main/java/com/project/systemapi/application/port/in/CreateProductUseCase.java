package com.project.systemapi.application.port.in;

import com.project.systemapi.model.Product;

import java.math.BigDecimal;

public interface CreateProductUseCase {
    Product create(String name, BigDecimal price);
}