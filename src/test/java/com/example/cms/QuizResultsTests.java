package com.example.cms;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.controller.Dto.TestResultsDto;
import com.example.cms.controller.Dto.TestResultsResponseDto;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.*;
import com.example.cms.model.service.SkinCareRountineService;
import com.example.cms.model.service.SkinCareRoutineService2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class QuizResultsTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Autowired
    private SkintypeRepository skintypeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ConcernRepository concernRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
//    private SkinCareRountineService skinCareRountineService;
    private SkinCareRoutineService2 skinCareRoutineService2;

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> createdUserIds = new ArrayList<>();

    /**
     * Creates a test user and prepares test data for it
     * @param userId Unique user ID to create
     * @param budget Budget value for the test
     * @return TestResultsDto prepared for the test
     */
    private TestResultsDto createTestUserAndData(String userId, float budget) {
        // Create a fresh test user
        User testUser = new User();
        testUser.setUserId(userId);
        testUser.setEmail(userId + "@example.com");
        testUser.setPassword("password123");
        userRepository.save(testUser);

        // Track the created user ID for cleanup
        createdUserIds.add(userId);

        // Get a skintype - assuming ID 1 exists in DB
        Skintype skintype = skintypeRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Test requires at least one skintype in DB with ID 1"));

        // Get some ingredients - assuming IDs 1 and 2 exist in DB
        List<Ingredient> avoidIngredients = new ArrayList<>();
        Optional<Ingredient> ingredient1 = ingredientRepository.findById(1L);
        Optional<Ingredient> ingredient2 = ingredientRepository.findById(2L);

        if (ingredient1.isPresent()) {
            avoidIngredients.add(ingredient1.get());
        }
        if (ingredient2.isPresent()) {
            avoidIngredients.add(ingredient2.get());
        }

        if (avoidIngredients.isEmpty()) {
            throw new RuntimeException("Test requires at least one ingredient in DB");
        }

        // Get some concerns - assuming IDs 1 and 2 exist in DB
        List<Concern> concerns = new ArrayList<>();
        Optional<Concern> concern1 = concernRepository.findById(1);
        Optional<Concern> concern2 = concernRepository.findById(2);

        if (concern1.isPresent()) {
            concerns.add(concern1.get());
        }
        if (concern2.isPresent()) {
            concerns.add(concern2.get());
        }

        if (concerns.isEmpty()) {
            throw new RuntimeException("Test requires at least one concern in DB");
        }

        // Prepare the TestResultsDto for tests
        TestResultsDto testResultsDto = new TestResultsDto();
        testResultsDto.setSkinType(skintype.getSkintypeId());

        List<Long> ingredientIds = new ArrayList<>();
        for (Ingredient ingredient : avoidIngredients) {
            ingredientIds.add(ingredient.getIngredientId());
        }
        testResultsDto.setAvoidIngredients(ingredientIds);

        List<Integer> concernIds = new ArrayList<>();
        for (Concern concern : concerns) {
            concernIds.add(concern.getConcernId());
        }
        testResultsDto.setConcerns(concernIds);

        testResultsDto.setBudget(budget);
        testResultsDto.setUser(userId);

        return testResultsDto;
    }

    @Test
    @Transactional
    void testCreateTestResult() throws Exception {
        // Create a unique user and test data
        TestResultsDto testResultsDto = createTestUserAndData("user-create-test", 150.0f);

        // Send POST request to create test result
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Parse response to DTO
        TestResultsResponseDto responseDto = objectMapper.readValue(
                response.getContentAsString(),
                TestResultsResponseDto.class
        );

        // Basic assertions on response
        assertEquals(testResultsDto.getBudget(), responseDto.getBudget());
        assertEquals(skintypeRepository.findById(testResultsDto.getSkinType()).get().getDescription(),
                responseDto.getSkinTypeName());
        assertTrue(responseDto.getAvoidIngredientsNames().size() > 0);
        assertTrue(responseDto.getConcernNames().size() > 0);

        // Verify test result was created and associated with user
        User updatedUser = userRepository.findById(testResultsDto.getUser()).orElseThrow();
        assertNotNull(updatedUser.getTestResults());
        assertEquals(testResultsDto.getBudget(), updatedUser.getTestResults().getBudget());
    }

    @Test
    @Transactional
    void testGetTestResultForUser() throws Exception {
        // Create a unique user and test data
        String userId = "user-get-test";
        TestResultsDto testResultsDto = createTestUserAndData(userId, 50.0f);

        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Now get the test result
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/test-results/users/" + userId)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Don't try to deserialize directly to TestResults - use Jackson's JsonNode instead
        // which doesn't have the circular reference problem
        com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(response.getContentAsString());

        // Basic assertions using JsonNode
        assertEquals(testResultsDto.getBudget(), jsonNode.get("budget").floatValue());
        assertEquals(testResultsDto.getSkinType(), jsonNode.get("skinType").get("skintypeId").intValue());

        // Additional validations
        assertNotNull(jsonNode.get("testResultId"));
        assertTrue(jsonNode.has("concerns"));
        assertTrue(jsonNode.has("avoidIngredients"));
    }

    @Test
    @Transactional
    void testGetRecommendedProductsComprehensive() throws Exception {
        // 1. First check what products exist in the database
        List<Product> allProducts = this.productRepository.findAll();
        System.out.println("Total products in database: " + allProducts.size());

        // Products by category for debugging
        Map<String, Long> productsByCategory = allProducts.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCategory().getCategoryName(),
                        Collectors.counting()));

        System.out.println("Products by category:");
        productsByCategory.forEach((category, count) -> {
            System.out.println("  " + category + ": " + count);
        });

        // 2. Create a unique user with minimal constraints to maximize recommendations
        String userId = "user-recommend-detailed-test";
        TestResultsDto testResultsDto = createTestUserAndData(userId, 500.0f); // High budget

        // Print test data for debugging
        System.out.println("Test Data:");
        System.out.println("  User ID: " + userId);
        System.out.println("  Skin Type: " + testResultsDto.getSkinType());
        System.out.println("  Budget: " + testResultsDto.getBudget());
        System.out.println("  Avoid Ingredients: " + testResultsDto.getAvoidIngredients());
        System.out.println("  Concerns: " + testResultsDto.getConcerns());

        // 3. Create test result
        MockHttpServletResponse createResponse = mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // 4. Parse response to see what was created
        TestResultsResponseDto createdResults = objectMapper.readValue(
                createResponse.getContentAsString(),
                TestResultsResponseDto.class
        );

        System.out.println("Created Test Results:");
        System.out.println("  ID: " + createdResults.getTestResultId());
        System.out.println("  Skin Type: " + createdResults.getSkinTypeName());
        System.out.println("  Initial Recommendations: " +
                (createdResults.getRecommendedProducts() != null ?
                        createdResults.getRecommendedProducts().size() : "null"));

        // 5. Verify in the database
        User user = userRepository.findById(userId).orElseThrow();
        TestResults testResults = user.getTestResults();

        System.out.println("Database Check:");
        System.out.println("  Test Results ID: " + testResults.getTestResultId());
        System.out.println("  Recommended Products Count: " +
                (testResults.getRecommendedProducts() != null ?
                        testResults.getRecommendedProducts().size() : "null"));

        if (testResults.getRecommendedProducts() != null && !testResults.getRecommendedProducts().isEmpty()) {
            System.out.println("  Product details:");
            for (Product product : testResults.getRecommendedProducts()) {
                System.out.println("    - " + product.getName() + " (" + product.getBrand() + ") - $" + product.getPrice());
                System.out.println("      Category: " + product.getCategory().getCategoryName());
            }
        } else {
            System.out.println("  No products found in database!");
        }

        // 6. Get recommended products through the API
        MockHttpServletResponse getResponse = mockMvc.perform(
                        MockMvcRequestBuilders.get("/test-results/users/" + userId + "/recommendations")
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // 7. Parse API response
        List<ProductDto> apiRecommendations = objectMapper.readValue(
                getResponse.getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class)
        );

        System.out.println("API Response:");
        System.out.println("  Recommended Products Count: " + apiRecommendations.size());

        if (!apiRecommendations.isEmpty()) {
            System.out.println("  Product details:");
            for (ProductDto product : apiRecommendations) {
                System.out.println("    - " + product.getName() + " (" + product.getBrand() + ") - $" + product.getPrice());
            }
        } else {
            System.out.println("  No products returned from API!");
        }

        // 8. Assertions
        assertNotNull(testResults.getRecommendedProducts(), "Test results should have recommended products");
        assertFalse(testResults.getRecommendedProducts().isEmpty(),
                "Recommended products in the database should not be empty");
        assertFalse(apiRecommendations.isEmpty(),
                "API should return non-empty list of recommended products");
    }

    @Test
    @Transactional
    void testUpdateConcerns() throws Exception {
        // Create a unique user and test data
        String userId = "user-update-concerns-test";
        TestResultsDto testResultsDto = createTestUserAndData(userId, 60.0f);

        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Create update DTO with just the first concern
        List<Integer> concernIds = testResultsDto.getConcerns();
        TestResultsDto updateDto = new TestResultsDto();
        List<Integer> singleConcern = new ArrayList<>();
        singleConcern.add(concernIds.get(0));
        updateDto.setConcerns(singleConcern);

        // Update concerns
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results/users/" + userId + "/concerns")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateDto))
                )
                .andExpect(status().isOk());

        // Verify the update in the database
        User updatedUser = userRepository.findById(userId).orElseThrow();
        assertEquals(1, updatedUser.getTestResults().getConcerns().size());
        assertEquals(concernIds.get(0),
                updatedUser.getTestResults().getConcerns().get(0).getConcernId());
    }

    @Test
    @Transactional
    void testUpdateAvoidIngredients() throws Exception {
        // Create a unique user and test data
        String userId = "user-update-ingredients-test";
        TestResultsDto testResultsDto = createTestUserAndData(userId, 85.0f);

        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Create update DTO with just the first ingredient
        List<Long> ingredientIds = testResultsDto.getAvoidIngredients();
        TestResultsDto updateDto = new TestResultsDto();
        List<Long> singleIngredient = new ArrayList<>();
        singleIngredient.add(ingredientIds.get(0));
        updateDto.setAvoidIngredients(singleIngredient);

        // Update avoid ingredients
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results/users/" + userId + "/avoid")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateDto))
                )
                .andExpect(status().isOk());

        // Verify the update in the database
        User updatedUser = userRepository.findById(userId).orElseThrow();
        assertEquals(1, updatedUser.getTestResults().getAvoidIngredients().size());
        assertEquals(ingredientIds.get(0),
                updatedUser.getTestResults().getAvoidIngredients().get(0).getIngredientId());
    }

    @Test
    @Transactional
    void testDeleteTestResult() throws Exception {
        // Create a unique user and test data
        String userId = "user-delete-test";
        TestResultsDto testResultsDto = createTestUserAndData(userId, 45.0f);

        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Verify it exists
        User userWithResult = userRepository.findById(userId).orElseThrow();
        assertNotNull(userWithResult.getTestResults());

        // Delete the test result
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/test-results/users/" + userId)
                )
                .andExpect(status().isOk());

        // Verify it's gone
        User userAfterDelete = userRepository.findById(userId).orElseThrow();
        assertNull(userAfterDelete.getTestResults());
    }

    @Test
    @Transactional
    void testGetAllTestResults() throws Exception {
        // Create a unique user and test data
        String userId = "user-getall-test";
        TestResultsDto testResultsDto = createTestUserAndData(userId, 40.0f);

        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Get all test results and verify status code
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/test-results")
                )
                .andExpect(status().isOk());
    }
}