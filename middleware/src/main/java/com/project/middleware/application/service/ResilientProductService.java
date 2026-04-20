package com.project.middleware.application.service;

import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.ProductDTO;

import com.project.middleware.application.metrics.OrderMetrics;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import io.micrometer.core.instrument.Timer;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ResilientProductService {

    private final SystemApiClient systemApi;
    private final OrderMetrics metrics;

    @Observed(name = "product.get-all", contextualName = "get-products")
    @CircuitBreaker(name = "systemApi", fallbackMethod = "fallbackProducts")
    @Retry(name = "systemApi")
    @Bulkhead(name = "systemApi")
    @TimeLimiter(name = "systemApi")
    public CompletableFuture<List<ProductDTO>> getProducts() {

        Timer.Sample sample = metrics.startTimer();

        return CompletableFuture.supplyAsync(() -> {
            try {
                List<ProductDTO> result =
                        systemApi.getProducts(0, 10).getContent();

                metrics.recordSuccess();
                return result;

            } catch (Exception e) {
                metrics.recordFailure();
                throw e;
            }
        }).whenComplete((res, ex) -> metrics.stopTimer(sample));
    }

    private CompletableFuture<List<ProductDTO>> fallbackProducts(Throwable t) {
        metrics.recordFailure();
        return CompletableFuture.completedFuture(List.of());
    }
}