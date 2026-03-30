package com.project.systemapi.application.port.out;

import com.project.systemapi.domain.model.Product;

public interface SaveProductPort {
    Product save(Product product);
}