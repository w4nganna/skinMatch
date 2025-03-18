package com.example.cms;

import com.example.cms.model.entity.Product;
import com.example.cms.model.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void addProduct() throws Exception {
        ObjectNode productJson = objectMapper.createObjectNode();
        productJson.put("productId", 101);
        productJson.put("name", "Test Moisturizer");
        productJson.put("brand", "Test Brand");
        productJson.put("price", 19.99);
        productJson.put("category", "Moisturizer");
        productJson.put("type", "normal");
        productJson.put("imageURL", "https://example.com/image.jpg");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/products").
                                contentType("application/json").
                                content(productJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(productRepository.findById(101L).isPresent());
        Product addedProduct = productRepository.findById(101L).get();

        assertEquals(101L, addedProduct.getProductId());
        assertEquals("Test Moisturizer", addedProduct.getName());
        assertEquals("Test Brand", addedProduct.getBrand());
        assertEquals(19.99, addedProduct.getPrice());
        assertEquals("Moisturizer", addedProduct.getCategory());
        assertEquals("normal", addedProduct.getType());
        assertEquals("https://example.com/image.jpg", addedProduct.getImageURL());
    }

    @Test
    void deleteProduct() throws Exception {
        Product product = new Product(102L, "Delete Serum", "Delete Brand", 29.99,
                "Serum", "oily", "https://example.com/delete.jpg", null, null, null, null);
        productRepository.save(product);

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/products/102").
                                contentType("application/json"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(productRepository.findById(102L).isEmpty());
    }
}
