package com.project.middleware.application.service;

import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.*;
import com.project.middleware.adapter.in.web.dto.ProductDetailView;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CatalogOrchestrationService {

    private final SystemApiClient systemApi;

    public ProductDetailView getProductDetail(Long id) {

        CompletableFuture<ProductDTO> productF =
                CompletableFuture.supplyAsync(() ->
                        systemApi.getProductById(id));

        // dependent calls (after product fetched)
        CompletableFuture<CategoryDTO> categoryF =
                productF.thenApply(p ->
                        systemApi.getCategoryById(p.categoryId()));

        CompletableFuture<SupplierDTO> supplierF =
                productF.thenApply(p -> {
                    if (p.supplierId() == null) return null;
                    return systemApi.getSupplierById(p.supplierId());
                });

        CompletableFuture.allOf(productF, categoryF, supplierF).join();

        ProductDTO product = productF.join();
        CategoryDTO category = categoryF.join();
        SupplierDTO supplier = supplierF.join();

        return new ProductDetailView(
                product.id(),
                product.name(),
                product.price(),
                category != null ? category.name() : null,
                supplier != null ? supplier.name() : null,
                calculateTax(product.price())
        );
    }

    private BigDecimal calculateTax(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.18)); // 18%
    }
}