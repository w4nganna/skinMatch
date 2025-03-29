package com.example.cms;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.model.entity.Category;
import com.example.cms.model.entity.Product;
import com.example.cms.model.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    void getProductById() throws Exception {
        // First, we prepare a valid product ID to test with
        Long productId = 1L;  // Replace this with a product ID that exists in the database

        // Perform a GET request for the product by its ID
        MvcResult result = mockMvc.perform(get("/products/{productId}", productId))
                .andReturn();

        // Get the response as a JSON string
        String responseJson = result.getResponse().getContentAsString();

        // Use ObjectMapper to convert the JSON string into a ProductDto
        ProductDto productDto = objectMapper.readValue(responseJson, ProductDto.class);

        // Assert the HTTP status code of the response is 200 OK
        assertEquals(200, result.getResponse().getStatus());

        // Retrieve the product from the repository to verify it's been saved correctly
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));

        // Assert that the retrieved product matches the expected product details
        assertEquals("ultra fluid", productDto.getName());
        assertEquals("La Roche-Posay", productDto.getBrand());
        assertEquals(30.50, productDto.getPrice(), 0.01); // Using a tolerance for price comparison
        assertEquals("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s", productDto.getImageURL());
        //assertEquals(1, productDto.getCategoryId());  // Check the category ID
    }
}

