package com.example.cms;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.model.entity.Ingredient;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductsTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testProductFilteringByCategory() throws Exception {
        List<String> types = Arrays.asList("Moisturizer", "Cleanser");

        MvcResult result = mockMvc.perform(get("/products/filterII")
                        .param("types", String.join(",", types)))
                .andExpect(status().isOk())
                .andReturn();

        ProductDto[] filteredProducts = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDto[].class);

        assertTrue(filteredProducts.length > 0, "Should return at least one product");
        for (ProductDto product : filteredProducts) {
            assertTrue(types.contains(product.getBrand()), "Product type should be one of the specified types");
        }
    }

    @Test
    void testProductFilteringByPrice() throws Exception {
        double maxPrice = 30.00;

        MvcResult result = mockMvc.perform(get("/products/filterII")
                        .param("maxPrice", String.valueOf(maxPrice))
                        .param("sortBy", "price_low_to_high"))
                .andExpect(status().isOk())
                .andReturn();

        ProductDto[] filteredProducts = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDto[].class);

        assertTrue(filteredProducts.length > 0, "Should return at least one product");
        for (ProductDto product : filteredProducts) {
            assertTrue(product.getPrice() <= maxPrice, "Product price should not exceed max price");
        }

        // Check if products are sorted by price in ascending order
        for (int i = 1; i < filteredProducts.length; i++) {
            assertTrue(filteredProducts[i].getPrice() >= filteredProducts[i-1].getPrice(),
                    "Products should be sorted by price in ascending order");
        }
    }

    @Test
    void testProductFilteringByIngredients() throws Exception {
        List<Long> avoidIngredients = Arrays.asList(1L, 2L); // Assuming 1 and 2 are valid ingredient IDs to avoid

        MvcResult result = mockMvc.perform(get("/products/filterII")
                        .param("avoidIngredients", avoidIngredients.stream().map(String::valueOf).toArray(String[]::new)))
                .andExpect(status().isOk())
                .andReturn();

        ProductDto[] filteredProducts = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDto[].class);

        assertTrue(filteredProducts.length > 0, "Should return at least one product");
        for (ProductDto product : filteredProducts) {
            List<Long> productIngredientIds = product.getIngredients().stream()
                    .map(Ingredient::getIngredientId)
                    .collect(Collectors.toList());
            assertTrue(Collections.disjoint(productIngredientIds, avoidIngredients),
                    "Product should not contain any of the avoided ingredients");
        }
    }

    @Test
    void testProductFilteringByConcerns() throws Exception {
        List<Integer> concerns = Arrays.asList(1, 2); // Assuming 1 and 2 are valid concern IDs

        MvcResult result = mockMvc.perform(get("/products/filterII")
                        .param("concerns", concerns.stream().map(String::valueOf).toArray(String[]::new)))
                .andExpect(status().isOk())
                .andReturn();

        ProductDto[] filteredProducts = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDto[].class);

        assertTrue(filteredProducts.length > 0, "Should return at least one product");
        // Note: We can't directly test for concerns as they're not included in the ProductDto
        // You might want to add a field for concerns in ProductDto if you need to test this thoroughly
    }

    @Test
    void testProductFilteringWithMultipleCriteria() throws Exception {
        double maxPrice = 50.00;
        List<String> brands = Arrays.asList("CeraVe", "La Roche-Posay");
        List<String> types = Arrays.asList("Moisturizer", "Sunscreen");
        List<Long> avoidIngredients = Arrays.asList(1L, 2L);
        List<Integer> concerns = Arrays.asList(1, 2);

        MvcResult result = mockMvc.perform(get("/products/filterII")
                        .param("maxPrice", String.valueOf(maxPrice))
                        .param("brands", String.join(",", brands))
                        .param("types", String.join(",", types))
                        .param("avoidIngredients", avoidIngredients.stream().map(String::valueOf).toArray(String[]::new))
                        .param("concerns", concerns.stream().map(String::valueOf).toArray(String[]::new))
                        .param("sortBy", "price_high_to_low"))
                .andExpect(status().isOk())
                .andReturn();

        ProductDto[] filteredProducts = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDto[].class);

        assertTrue(filteredProducts.length > 0, "Should return at least one product");
        for (ProductDto product : filteredProducts) {
            assertTrue(product.getPrice() <= maxPrice, "Product price should not exceed max price");
            assertTrue(brands.contains(product.getBrand()), "Product brand should be one of the specified brands");
            // Note: We can't directly test for types as they're not included in the ProductDto
            // You might want to add a field for type in ProductDto if you need to test this thoroughly

            List<Long> productIngredientIds = product.getIngredients().stream()
                    .map(Ingredient::getIngredientId)
                    .collect(Collectors.toList());
            assertTrue(Collections.disjoint(productIngredientIds, avoidIngredients),
                    "Product should not contain any of the avoided ingredients");
        }

        // Check if products are sorted by price in descending order
        for (int i = 1; i < filteredProducts.length; i++) {
            assertTrue(filteredProducts[i].getPrice() <= filteredProducts[i-1].getPrice(),
                    "Products should be sorted by price in descending order");
        }
    }



    @Test
    void getProductById() throws Exception {
        // First, we prepare a valid product ID to test with
        Long productId = 1L;  // Replace this with a product ID that exists in the database

        // Perform a GET request for the product by its ID
        MvcResult result = mockMvc.perform(get("/products/{productId}", productId))
                .andExpect(status().isOk())
                .andReturn();

        // Get the response as a JSON string
        String responseJson = result.getResponse().getContentAsString();

        // Use ObjectMapper to convert the JSON string into a ProductDto
        ProductDto productDto = objectMapper.readValue(responseJson, ProductDto.class);

        // Retrieve the product from the repository to verify it's been saved correctly
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Assert that the retrieved product matches the expected product details
        assertEquals(product.getProductId(), productDto.getId());
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getBrand(), productDto.getBrand());
        assertEquals(product.getPrice(), productDto.getPrice(), 0.01); // Using a tolerance for price comparison
        assertEquals(product.getImageURL(), productDto.getImageURL());
        assertEquals(product.getAverageScore(), productDto.getAverageScore(), 0.01); // Using a tolerance for score comparison

        // Check that the ingredients list is not null and has the same size
        assertNotNull(productDto.getIngredients());
        assertEquals(product.getIngredients().size(), productDto.getIngredients().size());

        // Check that each ingredient in the DTO matches the corresponding ingredient in the entity
        for (int i = 0; i < product.getIngredients().size(); i++) {
            Ingredient entityIngredient = product.getIngredients().get(i);
            Ingredient dtoIngredient = productDto.getIngredients().get(i);
            assertEquals(entityIngredient.getIngredientId(), dtoIngredient.getIngredientId());
            assertEquals(entityIngredient.getIngredientName(), dtoIngredient.getIngredientName());
            // Add more assertions for other ingredient properties if needed
        }

        // If you want to check for a specific product, you can uncomment and modify these lines
        // assertEquals("ultra fluid", productDto.getName());
        // assertEquals("La Roche-Posay", productDto.getBrand());
        // assertEquals(30.50, productDto.getPrice(), 0.01);
        // assertEquals("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s", productDto.getImageURL());
    }

}

