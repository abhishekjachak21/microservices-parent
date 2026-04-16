package com.project.middleware.application.service;

import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.ProductDTO;
import com.project.middleware.application.metrics.OrderMetrics;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrchestrationService {

    private final SystemApiClient systemApi;
    private final OrderMetrics metrics;

    public ProductDTO getProduct(Long id) {

        Timer.Sample sample = metrics.startTimer();

        try {
            ProductDTO product = systemApi.getProductById(id);

            metrics.recordSuccess();
            return product;

        } catch (Exception e) {
            metrics.recordFailure();
            throw e;

        } finally {
            metrics.stopTimer(sample);
        }
    }
}