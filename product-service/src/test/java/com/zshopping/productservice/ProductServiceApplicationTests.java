package com.zshopping.productservice;

import com.zshopping.productservice.dto.ProductRequest;
import com.zshopping.productservice.service.IProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductService productService;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ProductRequest productRequest = getProductRequest();
        String productRequestAsString = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestAsString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertTrue(productService.getAllProducts().size() > 0);
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("IPhone 13")
                .description("Great Phone")
                .price(BigDecimal.valueOf(1200))
                .build();
    }

}
