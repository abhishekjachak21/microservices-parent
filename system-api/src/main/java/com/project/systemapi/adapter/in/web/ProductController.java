package com.project.systemapi.adapter.in.web;

import com.project.systemapi.adapter.in.web.dto.ProductResponse;
import com.project.systemapi.application.port.in.CreateProductUseCase;
import com.project.systemapi.application.service.ProductService;
import com.project.systemapi.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import com.project.systemapi.adapter.in.web.dto.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCase createUC;
    private final ProductService service;

    public ProductController(CreateProductUseCase createUC,
                             ProductService service) {
        this.createUC = createUC;
        this.service = service;
    }

    @Operation(summary = "Create a product")
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest req) {

        Product p = createUC.create(req.name(), req.price());

        return ResponseEntity
                .created(URI.create("/api/v1/products/" + p.id()))
                .body(ProductResponse.from(p));
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return ProductResponse.from(service.getById(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}