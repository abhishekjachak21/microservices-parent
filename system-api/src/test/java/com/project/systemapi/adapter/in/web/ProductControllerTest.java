package com.project.systemapi.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProduct_shouldReturn200() throws Exception {

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void create_shouldFailValidation() throws Exception {

        String body = """
        {
          "name": "",
          "price": -10
        }
    """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest());
    }
}