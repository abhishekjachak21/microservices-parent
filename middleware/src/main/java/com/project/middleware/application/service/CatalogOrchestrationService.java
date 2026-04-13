package com.project.middleware.application.service;

import com.project.middleware.adapter.in.web.dto.ProductDetailResponse;
import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.*;
import com.project.middleware.adapter.in.web.dto.ProductDetailView;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogOrchestrationService {

    private final SystemApiClient systemApi;

    public ProductDetailResponse getProductDetail(Long id) {

        log.info("Fetching product details for id: {}", id);



        ProductDTO product = systemApi.getProductById(id);

        CategoryDTO category = null;

        if (product.categoryId() != null) {
            category = systemApi.getCategoryById(product.categoryId());
        }

        SupplierDTO supplier = null;

        if (product.supplierId() != null) {
            supplier = systemApi.getSupplierById(product.supplierId());
        }

        if (product.supplierId() != null) {
            supplier = systemApi.getSupplierById(product.supplierId());
        }

        return new ProductDetailResponse(
                product.id(),
                product.name(),
                product.price(),
                category.name(),
                supplier != null ? supplier.name() : null,
                calculateTax(product.price())
        );
    }


    private BigDecimal calculateTax(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.18)); // 18%
    }
}