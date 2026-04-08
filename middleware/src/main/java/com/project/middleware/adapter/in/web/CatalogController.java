package com.project.middleware.adapter.in.web;

import com.project.middleware.application.service.CatalogOrchestrationService;
import com.project.middleware.adapter.in.web.dto.ProductDetailView;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogOrchestrationService service;

    @GetMapping("/products/{id}")
    public ProductDetailView getProduct(@PathVariable Long id) {
        return service.getProductDetail(id);
    }
}