package com.project.middleware.application.service;

import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrchestrationService {

    private final SystemApiClient systemApi;

    public ProductDTO getProduct(Long id) {
        return systemApi.getProductById(id);
    }
}