package com.project.systemapi.application.service;


import com.project.systemapi.application.port.in.CreateProductUseCase;
import com.project.systemapi.application.port.out.LoadProductPort;
import com.project.systemapi.application.port.out.SaveProductPort;
import com.project.systemapi.domain.model.Product;
import com.project.systemapi.domain.model.ProductStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService implements CreateProductUseCase {

    private final SaveProductPort saveProductPort;
    private final LoadProductPort loadProductPort;

    public ProductService(SaveProductPort saveProductPort,
                          LoadProductPort loadProductPort) {
        this.saveProductPort = saveProductPort;
        this.loadProductPort = loadProductPort;
    }

    @Override
    public Product create(String name, BigDecimal price) {
        Product product = new Product(
                null,
                name,
                price,
                ProductStatus.ACTIVE,
                null,
                null,
                null
        );
        return saveProductPort.save(product);
    }

    public Product getById(Long id) {
        return loadProductPort.getById(id);
    }

    public void delete(Long id) {
        loadProductPort.delete(id);
    }
}