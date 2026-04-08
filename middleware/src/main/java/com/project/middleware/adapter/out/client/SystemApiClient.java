package com.project.middleware.adapter.out.client;

import com.project.middleware.adapter.out.client.dto.CategoryDTO;
import com.project.middleware.adapter.out.client.dto.CreateProductDTO;
import com.project.middleware.adapter.out.client.dto.ProductDTO;
import com.project.middleware.adapter.out.client.dto.SupplierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "system-api",
        url = "${system-api.url:http://localhost:8081}"
)
public interface SystemApiClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDTO getProductById(@PathVariable Long id);

    @PostMapping("/api/v1/products")
    ProductDTO createProduct(@RequestBody CreateProductDTO dto);

    @GetMapping("/api/v1/categories/{id}")
    CategoryDTO getCategoryById(@PathVariable Long id);

    @GetMapping("/api/v1/suppliers/{id}")
    SupplierDTO getSupplierById(@PathVariable Long id);
}