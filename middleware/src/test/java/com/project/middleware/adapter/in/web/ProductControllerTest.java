package com.project.middleware.adapter.in.web;

import com.project.middleware.adapter.out.client.SystemApiClient;
import com.project.middleware.adapter.out.client.dto.ProductDTO;
import com.project.middleware.application.service.CatalogOrchestrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogOrchestrationServiceTest {

    @Mock
    SystemApiClient systemApi;

    @InjectMocks
    CatalogOrchestrationService service;

    @Test
    void shouldReturnProductDetail() {

        ProductDTO product = new ProductDTO(1L, "Test",
                new BigDecimal("10"), "ACTIVE", 1L, 1L);

        when(systemApi.getProductById(1L))
                .thenReturn(new ProductDTO(1L, "Test",
                        new BigDecimal("10"), "ACTIVE", 1L, 1L));

        var result = service.getProductDetail(1L);

        assertThat(result.name()).isEqualTo("Test");
    }
}