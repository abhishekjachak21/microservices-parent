package com.project.systemapi.application.port.out;

import com.project.systemapi.domain.model.Product;

public interface LoadProductPort {
    Product getById(Long id);
    void delete(Long id);
}