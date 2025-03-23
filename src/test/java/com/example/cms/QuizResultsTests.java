package com.example.cms;

import com.example.cms.controller.Dto.TestResultsDto;
import com.example.cms.controller.Dto.TestResultsResponseDto;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.*;
import com.example.cms.model.service.SkinCareRountineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import java.util.List;
import java.util.Optional;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static sun.jvm.hotspot.utilities.AddressOps.greaterThan;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private SkinCareRountineService skinCareRountineService;

    private User testUser;
    private Skintype skintype;
    private List<Ingredient> avoidIngredients;
    private List<Concern> concerns;
    private TestResultsDto testResultsDto;

    @BeforeEach
    void setUp() {
        // Create test user if it doesn't exist
        Optional<User> existingUser = userRepository.findById("test-user");
        if (existingUser.isPresent()) {
            testUser = existingUser.get();

            // Clear any existing test results
            if (testUser.getTestResults() != null) {
                TestResults oldResults = testUser.getTestResults();
                testUser.setTestResults(null);
                userRepository.save(testUser);
                testResultsRepository.delete(oldResults);
            }
        } else {
            testUser = new User();
            testUser.setUserId("test-user");
            testUser.setEmail("test@example.com");
            testUser.setPassword("password123");
            userRepository.save(testUser);
        }

        // Get a skintype - assuming ID 1 exists in your DB
        Optional<Skintype> skintypeOpt = skintypeRepository.findById(1);
        if (skintypeOpt.isPresent()) {
            skintype = skintypeOpt.get();
        } else {
            throw new RuntimeException("Test requires at least one skintype in DB with ID 1");
        }

        // Get some ingredients - assuming IDs 1 and 2 exist in your DB
        avoidIngredients = new ArrayList<>();
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

        // Get some concerns - assuming IDs 1 and 2 exist in your DB
        concerns = new ArrayList<>();
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
        testResultsDto = new TestResultsDto();
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

        testResultsDto.setBudget(50.0f);
        testResultsDto.setUser(testUser.getUserId());
    }

    @Test
    void testCreateTestResult() throws Exception {
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
        assertEquals(150.0f, responseDto.getBudget());
        assertEquals(skintype.getDescription(), responseDto.getSkinTypeName());
        assertTrue(responseDto.getAvoidIngredientsNames().size() > 0);
        assertTrue(responseDto.getConcernNames().size() > 0);

        // Verify test result was created and associated with user
        User updatedUser = userRepository.findById(testUser.getUserId()).orElseThrow();
        assertNotNull(updatedUser.getTestResults());
        assertEquals(150.0f, updatedUser.getTestResults().getBudget());
    }

    @Test
    void testGetTestResultForUser() throws Exception {
        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Now get the test result
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/test-results/users/" + testUser.getUserId())
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Parse response to TestResults
        TestResults testResults = objectMapper.readValue(
                response.getContentAsString(),
                TestResults.class
        );

        // Basic assertions
        assertEquals(150.0f, testResults.getBudget());
        assertEquals(skintype.getSkintypeId(), testResults.getSkinType().getSkintypeId());
    }

    @Test
    void testGetRecommendedProducts() throws Exception {
        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Get recommended products
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/test-results/users/" + testUser.getUserId() + "/recommendations")
                )
                .andExpect(status().isOk());
        // We only check the status since recommendations depend on your matching algorithm
    }

    @Test
    void testUpdateConcerns() throws Exception {
        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Create update DTO with just the first concern
        TestResultsDto updateDto = new TestResultsDto();
        List<Integer> singleConcern = new ArrayList<>();
        singleConcern.add(concerns.get(0).getConcernId());
        updateDto.setConcerns(singleConcern);

        // Update concerns
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results/users/" + testUser.getUserId() + "/concerns")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateDto))
                )
                .andExpect(status().isOk());

        // Verify the update in the database
        User updatedUser = userRepository.findById(testUser.getUserId()).orElseThrow();
        assertEquals(1, updatedUser.getTestResults().getConcerns().size());
        assertEquals(concerns.get(0).getConcernId(),
                updatedUser.getTestResults().getConcerns().get(0).getConcernId());
    }

    @Test
    void testUpdateAvoidIngredients() throws Exception {
        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Create update DTO with just the first ingredient
        TestResultsDto updateDto = new TestResultsDto();
        List<Long> singleIngredient = new ArrayList<>();
        singleIngredient.add(avoidIngredients.get(0).getIngredientId());
        updateDto.setAvoidIngredients(singleIngredient);

        // Update avoid ingredients
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results/users/" + testUser.getUserId() + "/avoid")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateDto))
                )
                .andExpect(status().isOk());

        // Verify the update in the database
        User updatedUser = userRepository.findById(testUser.getUserId()).orElseThrow();
        assertEquals(1, updatedUser.getTestResults().getAvoidIngredients().size());
        assertEquals(avoidIngredients.get(0).getIngredientId(),
                updatedUser.getTestResults().getAvoidIngredients().get(0).getIngredientId());
    }

    @Test
    void testDeleteTestResult() throws Exception {
        // First create a test result
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testResultsDto))
                )
                .andExpect(status().isOk());

        // Verify it exists
        User userWithResult = userRepository.findById(testUser.getUserId()).orElseThrow();
        assertNotNull(userWithResult.getTestResults());

        // Delete the test result
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/test-results/users/" + testUser.getUserId())
                )
                .andExpect(status().isOk());

        // Verify it's gone
        User userAfterDelete = userRepository.findById(testUser.getUserId()).orElseThrow();
        assertNull(userAfterDelete.getTestResults());
    }

    @Test
    void testGetAllTestResults() throws Exception {
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