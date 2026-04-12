package com.project.middleware.application.service;

import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.ProductDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ResilientProductService {

    private final SystemApiClient systemApi;

    @CircuitBreaker(name = "systemApi", fallbackMethod = "fallbackProducts")
    @Retry(name = "systemApi")
    @Bulkhead(name = "systemApi")
    @TimeLimiter(name = "systemApi")
    public CompletableFuture<List<ProductDTO>> getProducts() {

        return CompletableFuture.supplyAsync(() ->
                systemApi.getProducts(0, 10).getContent()
        );
    }

    private CompletableFuture<List<ProductDTO>> fallbackProducts(Throwable t) {
        return CompletableFuture.completedFuture(List.of());
    }
}