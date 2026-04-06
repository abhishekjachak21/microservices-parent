package com.project.systemapi.application.service;


import com.project.systemapi.application.port.out.LoadProductPort;
import com.project.systemapi.application.port.out.SaveProductPort;
import com.project.systemapi.domain.model.Product;
import com.project.systemapi.domain.model.ProductStatus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    SaveProductPort savePort;

    @Mock
    LoadProductPort loadPort;

    @InjectMocks
    ProductService service;

    @Test
    void create_shouldSaveProduct() {

        // given
        Product mockProduct = new Product(
                1L,
                "Test",
                new BigDecimal("10.00"),
                ProductStatus.ACTIVE,
                null,
                null,
                null
        );

        when(savePort.save(any())).thenReturn(mockProduct);

        // when
        Product result = service.create("Test", new BigDecimal("10.00"));

        // then
        assertThat(result.name()).isEqualTo("Test");
        verify(savePort).save(any());
    }
}