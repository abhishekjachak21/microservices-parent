package com.project.systemapi.application.service;


import com.project.systemapi.application.port.in.CreateProductUseCase;
import com.project.systemapi.application.port.out.SaveProductPort;
import com.project.systemapi.domain.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService implements CreateProductUseCase {

    private final SaveProductPort saveProductPort;

    public ProductService(SaveProductPort saveProductPort) {
        this.saveProductPort = saveProductPort;
    }

    @Override
    public Product create(String name, BigDecimal price) {
        Product product = Product.create(name, price);
        return saveProductPort.save(product);
    }
}
