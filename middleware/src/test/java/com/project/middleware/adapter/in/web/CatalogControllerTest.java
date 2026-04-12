package com.project.middleware.adapter.in.web;

import com.project.middleware.adapter.out.client.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.project.middleware.adapter.out.client.dto.CategoryDTO;
import com.project.middleware.adapter.out.client.dto.SupplierDTO;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200_whenProductExists() throws Exception {

        mockMvc.perform(get("/api/v1/catalog/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404_whenProductNotFound() throws Exception {

        mockMvc.perform(get("/api/v1/catalog/products/999"))
                .andExpect(status().is4xxClientError());
    }

}