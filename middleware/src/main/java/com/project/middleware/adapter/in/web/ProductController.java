package com.project.middleware.adapter.in.web;

import com.project.middleware.application.service.ProductOrchestrationService;
import com.project.middleware.adapter.out.client.dto.ProductDTO;
import com.project.middleware.application.service.ResilientProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductOrchestrationService service;
    private final ResilientProductService resilientService;

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @GetMapping("/resilient-products")
    public CompletableFuture<List<ProductDTO>> getProducts() {
        return resilientService.getProducts();
    }

}