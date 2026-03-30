package com.project.systemapi.adapter.in.web;

import com.project.systemapi.application.port.in.CreateProductUseCase;
import com.project.systemapi.domain.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase useCase;

    public ProductController(CreateProductUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/name")
    public String getName(){
        return "Hello Microservice!";
    }

    @PostMapping
    public Product create(@RequestBody ProductRequest req) {
        return useCase.create(req.getName(), req.getPrice());
    }
}