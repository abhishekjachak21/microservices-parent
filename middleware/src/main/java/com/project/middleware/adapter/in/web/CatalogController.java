package com.project.middleware.adapter.in.web;

import com.project.middleware.application.service.CatalogOrchestrationService;
import com.project.middleware.adapter.in.web.dto.ProductDetailResponse;
import com.project.middleware.adapter.in.web.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogOrchestrationService service;


    @GetMapping("/products/{id}")
    public ApiResponse<ProductDetailResponse> getProduct(@PathVariable Long id) {

        log.info("Incoming request for product id: {}", id);

        ProductDetailResponse response = service.getProductDetail(id);

        return ApiResponse.of(response);
    }
}