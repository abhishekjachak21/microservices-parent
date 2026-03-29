package com.project.systemapi.adapter.in.web;

import com.project.systemapi.application.port.in.CreateProductUseCase;
import com.project.systemapi.model.Product;
import com.project.systemapi.dto.ProductRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase useCase;

    public ProductController(CreateProductUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Product create(@RequestBody ProductRequest req) {
        return useCase.create(req.getName(), req.getPrice());
    }
}