package com.project.middleware.adapter.out.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(
        name = "system-api",
        url = "${system-api.url:http://localhost:8081}"
)
public interface SystemApiClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDTO getProductById(@PathVariable Long id);

    @PostMapping("/api/v1/products")
    ProductDTO createProduct(@RequestBody CreateProductDTO dto);
}