package com.project.middleware.adapter.in.web;

import com.project.middleware.application.service.ProductOrchestrationService;
import com.project.middleware.adapter.out.client.dto.ProductDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/middleware/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductOrchestrationService service;

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }
}