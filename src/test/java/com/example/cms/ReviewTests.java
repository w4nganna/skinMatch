package com.example.cms;

import com.example.cms.controller.Dto.ReviewDto;
import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.Review;
import com.example.cms.model.entity.ReviewKey;
import com.example.cms.model.entity.User;
import com.example.cms.model.entity.Product;
import com.example.cms.model.repository.ReviewRepository;
import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EnhancedReviewTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private User testUser;
    private Product testProduct;

    @BeforeEach
    void setup() {
        // Clear any existing review for our test case
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        reviewRepository.findById(reviewKey).ifPresent(review ->
                reviewRepository.deleteById(reviewKey)
        );
    }

    // TESTS
    @Test
    void testCreateReviewWithInvalidUser() throws Exception {
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "NON_EXISTENT_USER");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "Test review");
        reviewJson.put("score", 5);
        reviewJson.put("date", "2025-Jan-05");

        try {
            mockMvc.perform(
                    post("/reviews")
                            .contentType("application/json")
                            .content(reviewJson.toString()));
            fail("Expected an exception for invalid user");
        } catch (NestedServletException e) {
            assertTrue(e.getCause() instanceof UserNotFoundException);
        }
    }

    @Test
    void testCreateReviewWithInvalidProduct() throws Exception {
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 999L); // Non-existent product
        reviewJson.put("reviewBody", "Test review");
        reviewJson.put("score", 5);
        reviewJson.put("date", "2025-Jan-05");

        try {
            mockMvc.perform(
                    post("/reviews")
                            .contentType("application/json")
                            .content(reviewJson.toString()));
            fail("Expected an exception for invalid product");
        } catch (NestedServletException e) {
            assertTrue(e.getCause() instanceof ProductNotFoundException);
        }
    }

    @Test
    void testCreateReviewWithInvalidScore() throws Exception {
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "Test review");
        reviewJson.put("score", 6); // Invalid score (above 5)
        reviewJson.put("date", "2025-Jan-05");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void testCreateReviewWithEmptyReviewBody() throws Exception {
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "");
        reviewJson.put("score", 5);
        reviewJson.put("date", "2025-Jan-05");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void testCreateDuplicateReview() throws Exception {
        // First, create a review
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "Original review");
        reviewJson.put("score", 5);
        reviewJson.put("date", "2025-Jan-05");

        mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        // Now try to create another review with the same key
        reviewJson.put("reviewBody", "Duplicate review");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(409, response.getStatus()); // Expect Conflict status code
    }

    // UPDATE TESTS

    @Test
    void testUpdateNonExistentReview() throws Exception {
        // Make sure the review does not exist
        ReviewKey reviewKey = new ReviewKey("00099", 99L);
        reviewRepository.findById(reviewKey).ifPresent(review ->
                reviewRepository.deleteById(reviewKey)
        );

        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00099");
        reviewJson.put("productId", 99L);
        reviewJson.put("reviewBody", "Updated review");
        reviewJson.put("score", 4);
        reviewJson.put("date", "2025-Jan-10");

        try {
            mockMvc.perform(
                    put("/reviews/99/00099")
                            .contentType("application/json")
                            .content(reviewJson.toString()));
            fail("Expected an exception for non-existent user");
        } catch (NestedServletException e) {
            assertTrue(e.getCause() instanceof UserNotFoundException);
        }
    }

    @Test
    void testUpdateReviewWithInvalidScore() throws Exception {
        // Create a review to update
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        Review review = new Review();
        review.setReviewId(reviewKey);
        review.setUser(userRepository.findById("00001").orElseThrow());
        review.setProduct(productRepository.findById(1L).orElseThrow());
        review.setReviewBody("Original review");
        review.setScore(5);
        review.setDate("2025-Jan-05");
        reviewRepository.save(review);

        // Update with invalid score
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "Updated review");
        reviewJson.put("score", 0); // Invalid score (below 1)
        reviewJson.put("date", "2025-Jan-10");

        MockHttpServletResponse response = mockMvc.perform(
                        put("/reviews/1/00001")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void testUpdateReviewWithMismatchedIds() throws Exception {
        // Create a review
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        Review review = new Review();
        review.setReviewId(reviewKey);
        review.setUser(userRepository.findById("00001").orElseThrow());
        review.setProduct(productRepository.findById(1L).orElseThrow());
        review.setReviewBody("Original review");
        review.setScore(5);
        review.setDate("2025-Jan-05");
        reviewRepository.save(review);

        // Create update JSON with IDs that don't match the URL
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00002"); // Different from URL
        reviewJson.put("productId", 2L);    // Different from URL
        reviewJson.put("reviewBody", "Updated review");
        reviewJson.put("score", 4);
        reviewJson.put("date", "2025-Jan-10");

        MockHttpServletResponse response = mockMvc.perform(
                        put("/reviews/1/00001")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void testUpdateReviewWithNullBody() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        put("/reviews/1/00001")
                                .contentType("application/json")
                                .content(""))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    // DELETE TESTS

    @Test
    void testDeleteNonExistentReview() throws Exception {
        // Instead of trying to delete a non-existent review via repository directly,
        // which throws an exception, just create an ID that's unlikely to exist
        String userId = "NON_EXISTENT_USER_ID";
        Long productId = 999999L;

        // First verify the review doesn't exist
        ReviewKey reviewKey = new ReviewKey(userId, productId);
        assertFalse(reviewRepository.existsById(reviewKey), "Test precondition: Review should not exist before test");

        // Your controller might still throw an exception if it's not handling
        // the non-existent case, so we need to wrap this in a try-catch
        try {
            MockHttpServletResponse response = mockMvc.perform(
                            delete("/reviews/" + productId + "/" + userId))
                    .andReturn().getResponse();

            // If we get here, the controller didn't throw an exception
            // It should return 200 as deleting a non-existent resource is idempotent
            assertEquals(200, response.getStatus());
        } catch (Exception e) {
            // If your controller doesn't handle non-existent reviews gracefully,
            // you'll need to refactor the controller or adjust the expected behavior
            fail("Controller should handle deleting non-existent reviews without throwing exceptions: " + e.getMessage());
        }
    }


    @Test
    void testDeleteAndVerifyRemoved() throws Exception {
        // Create a review to delete
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        Review review = new Review();
        review.setReviewId(reviewKey);
        review.setUser(userRepository.findById("00001").orElseThrow());
        review.setProduct(productRepository.findById(1L).orElseThrow());
        review.setReviewBody("Review to delete");
        review.setScore(3);
        review.setDate("2025-Jan-05");
        reviewRepository.save(review);

        // Verify it exists
        assertTrue(reviewRepository.findById(reviewKey).isPresent());

        // Delete it
        MockHttpServletResponse response = mockMvc.perform(
                        delete("/reviews/1/00001"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        // Verify it's gone
        assertTrue(reviewRepository.findById(reviewKey).isEmpty());
    }

    @Test
    void testDeleteImpactsAverageScore() throws Exception {
        // Create two reviews for the same product
        Product product = productRepository.findById(1L).orElseThrow();
        User user1 = userRepository.findById("00001").orElseThrow();
        User user2 = userRepository.findById("00002").orElseThrow();

        // First review - score 5
        ReviewKey reviewKey1 = new ReviewKey("00001", 1L);
        Review review1 = new Review();
        review1.setReviewId(reviewKey1);
        review1.setUser(user1);
        review1.setProduct(product);
        review1.setReviewBody("First review");
        review1.setScore(5);
        review1.setDate("2025-Jan-05");
        reviewRepository.save(review1);

        // Second review - score 1
        ReviewKey reviewKey2 = new ReviewKey("00002", 1L);
        Review review2 = new Review();
        review2.setReviewId(reviewKey2);
        review2.setUser(user2);
        review2.setProduct(product);
        review2.setReviewBody("Second review");
        review2.setScore(1);
        review2.setDate("2025-Jan-05");
        reviewRepository.save(review2);

        // Check average score (should be 3)
        Double avgBefore = reviewRepository.findAverageScoreByProductId(1L);
        assertEquals(3.0, avgBefore, 0.001);

        // Delete the score 1 review
        mockMvc.perform(delete("/reviews/1/00002"));

        // Check average score again (should now be 5)
        Double avgAfter = reviewRepository.findAverageScoreByProductId(1L);
        assertEquals(5.0, avgAfter, 0.001);
    }

    // RETRIEVAL TESTS

    @Test
    void testGetReviewsForProductWithNoReviews() throws Exception {
        // Make sure product 999 has no reviews
        List<Review> existingReviews = reviewRepository.findByProductId(999L);
        existingReviews.forEach(review -> reviewRepository.delete(review));

        try {
            mockMvc.perform(get("/reviews/999"));
            fail("Expected exception for product with no reviews");
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("No reviews found"));
        }
    }

    @Test
    void testGetAverageScoreNoReviews() throws Exception {
        // Make sure product 999 has no reviews
        List<Review> existingReviews = reviewRepository.findByProductId(999L);
        existingReviews.forEach(review -> reviewRepository.delete(review));

        MockHttpServletResponse response = mockMvc.perform(
                        get("/reviews/999/average"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("0.0", response.getContentAsString());
    }

    @Test
    void testGetAverageScoreWithMultipleReviews() throws Exception {
        // Create several reviews with different scores for product 1
        Product product = productRepository.findById(1L).orElseThrow();

        // Clean up any existing reviews for this product
        List<Review> existingReviews = reviewRepository.findByProductId(1L);
        existingReviews.forEach(review -> reviewRepository.delete(review));

        // Add 3 reviews with scores 3, 4, and 5
        ReviewKey key1 = new ReviewKey("00001", 1L);
        Review review1 = new Review();
        review1.setReviewId(key1);
        review1.setUser(userRepository.findById("00001").orElseThrow());
        review1.setProduct(product);
        review1.setReviewBody("Review 1");
        review1.setScore(3);
        review1.setDate("2025-Jan-05");
        reviewRepository.save(review1);

        ReviewKey key2 = new ReviewKey("00002", 1L);
        Review review2 = new Review();
        review2.setReviewId(key2);
        review2.setUser(userRepository.findById("00002").orElseThrow());
        review2.setProduct(product);
        review2.setReviewBody("Review 2");
        review2.setScore(4);
        review2.setDate("2025-Jan-05");
        reviewRepository.save(review2);

        ReviewKey key3 = new ReviewKey("00003", 1L);
        Review review3 = new Review();
        review3.setReviewId(key3);
        review3.setUser(userRepository.findById("00003").orElseThrow());
        review3.setProduct(product);
        review3.setReviewBody("Review 3");
        review3.setScore(5);
        review3.setDate("2025-Jan-05");
        reviewRepository.save(review3);

        // Check the average score
        MockHttpServletResponse response = mockMvc.perform(
                        get("/reviews/1/average"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("4.0", response.getContentAsString());
    }

    // BOUNDARY VALUE TESTS

    @Test
    void testCreateReviewWithMinimumValidScore() throws Exception {
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "Minimum score review");
        reviewJson.put("score", 1); // Minimum valid score
        reviewJson.put("date", "2025-Jan-05");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        assertTrue(reviewRepository.findById(reviewKey).isPresent());
        assertEquals(1, reviewRepository.findById(reviewKey).get().getScore());
    }

    @Test
    void testCreateReviewWithMaximumValidScore() throws Exception {
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "Maximum score review");
        reviewJson.put("score", 5); // Maximum valid score
        reviewJson.put("date", "2025-Jan-05");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        assertTrue(reviewRepository.findById(reviewKey).isPresent());
        assertEquals(5, reviewRepository.findById(reviewKey).get().getScore());
    }

    @Test
    void testLongReviewBody() throws Exception {
        // Create a very long review body (255 characters)
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            longText.append("a");
        }

        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", longText.toString());
        reviewJson.put("score", 4);
        reviewJson.put("date", "2025-Jan-05");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        // This could pass or fail depending on if there's a size limit on reviewBody
        // If it fails with a 400, you might want to document the limit
        if (response.getStatus() == 400) {
            System.out.println("Note: The system rejects review bodies over a certain length");
        } else {
            assertEquals(200, response.getStatus());
            ReviewKey reviewKey = new ReviewKey("00001", 1L);
            assertTrue(reviewRepository.findById(reviewKey).isPresent());
            assertEquals(longText.toString(), reviewRepository.findById(reviewKey).get().getReviewBody());
        }
    }

    @Test
    void endptDeleteUser() throws Exception {

        //create and save user
        User user = new User("12345", "testing@mail.com", "testing123");
        userRepository.save(user);

        //create and save review
        Review review = new Review();
        ReviewKey reviewKey = new ReviewKey("12345", 1L);
        review.setReviewId(reviewKey);
        review.setUser(userRepository.findById("12345").orElseThrow());
        review.setProduct(productRepository.findById(1L).orElseThrow());
        review.setReviewBody("Fits my skin!");
        review.setScore(5);
        review.setDate("2025-Apr-04");
        reviewRepository.save(review);

        //make sure user and review saved
        assertTrue(userRepository.findById("12345").isPresent(), "User saved");
        assertTrue(reviewRepository.findById(reviewKey).isPresent(), "Review saved");

        //delete reviews
        mockMvc.perform(delete("/users/12345/reviews/delete-all"))
                .andReturn().getResponse();

        //delete user
        MockHttpServletResponse response = mockMvc.perform(
                        delete("/users/12345")
                                .contentType("application/json"))
                .andReturn().getResponse();

        //final check
        assertEquals(200, response.getStatus(), "User deleted");
        assertTrue(userRepository.findById("12345").isEmpty(), "User not found");
        assertTrue(reviewRepository.findById(reviewKey).isEmpty(), "Review not found");
    }

    @Test
    void endptUpdateUser() throws Exception {

        //create and save user
        User user = new User("12345", "testing@mail.com", "update123");
        userRepository.save(user);

        //create and save review
        Review review = new Review();
        ReviewKey reviewKey = new ReviewKey("12345", 1L);
        review.setReviewId(reviewKey);
        review.setUser(userRepository.findById("12345").orElseThrow());
        review.setProduct(productRepository.findById(1L).orElseThrow());
        review.setReviewBody("Fits my skin!");
        review.setScore(5);
        review.setDate("2025-Apr-04");
        reviewRepository.save(review);

        //make sure user and review saved
        assertTrue(userRepository.findById("12345").isPresent(), "User saved");
        assertTrue(reviewRepository.findById(reviewKey).isPresent(), "Review saved");

        //update user
        ObjectNode userJson = objectMapper.createObjectNode();
        userJson.put("newUserId", "54321");

        //update user
        MockHttpServletResponse response = mockMvc.perform(
                        put("/users/12345/id")
                                .contentType("application/json")
                                .content(userJson.toString()))
                .andReturn().getResponse();

        //final check
        assertEquals(200, response.getStatus(), "Done");
        assertTrue(userRepository.findById("54321").isPresent(), "User not found");
        assertTrue(userRepository.findById("12345").isEmpty(), "User not found");

        ReviewKey newreviewKey = new ReviewKey("54321", 1L);
        Review updatedReview = reviewRepository.findById(newreviewKey).orElseThrow();
        assertEquals("54321", updatedReview.getUser().getUserId(),"User updated");
    }

}