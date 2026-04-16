package com.project.middleware.application.service;

import com.project.middleware.adapter.in.web.dto.ProductDetailResponse;
import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.*;
import com.project.middleware.adapter.in.web.dto.ProductDetailView;
import com.project.middleware.application.metrics.OrderMetrics;
import io.micrometer.core.instrument.Timer;
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
    private final OrderMetrics metrics;

    public ProductDetailResponse getProductDetail(Long id) {

        log.info("Fetching product details for id: {}", id);

        Timer.Sample sample = metrics.startTimer();

        try {
            ProductDTO product = systemApi.getProductById(id);

            CategoryDTO category = null;
            if (product.categoryId() != null) {
                category = systemApi.getCategoryById(product.categoryId());
            }

            SupplierDTO supplier = null;
            if (product.supplierId() != null) {
                supplier = systemApi.getSupplierById(product.supplierId());
            }

            metrics.recordSuccess();

            return new ProductDetailResponse(
                    product.id(),
                    product.name(),
                    product.price(),
                    category != null ? category.name() : null,
                    supplier != null ? supplier.name() : null,
                    calculateTax(product.price())
            );

        } catch (Exception e) {

            metrics.recordFailure();
            log.error("Error fetching product detail", e);
            throw e;

        } finally {
            metrics.stopTimer(sample);
        }
    }

    private BigDecimal calculateTax(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.18));
    }
}