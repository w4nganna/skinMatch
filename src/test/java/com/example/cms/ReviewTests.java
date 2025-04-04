package com.example.cms;

import com.example.cms.model.entity.Review;
import com.example.cms.model.entity.ReviewKey;
import com.example.cms.model.entity.User;
import com.example.cms.model.entity.Product;
import com.example.cms.model.repository.ReviewRepository;
import com.example.cms.model.repository.UserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReviewTests {

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

    @Test
    void addReview() throws Exception {
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        ReviewKey reviewKey2 = new ReviewKey("00002", 2L);

        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "This product was perfect for my skin");
        reviewJson.put("score", 5);
        reviewJson.put("date", "2025-Jan-05");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/reviews")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(reviewRepository.findById(reviewKey).isPresent());
    }

    @Test
    void deleteReview() throws Exception {
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        Review review = new Review();
        review.setReviewId(reviewKey);
        review.setUser(userRepository.findById("00001").orElseThrow());
        review.setProduct(productRepository.findById(1L).orElseThrow());
        review.setReviewBody("This product was perfect for my skin");
        review.setScore(5);
        review.setDate("2025-Jan-05");
        reviewRepository.save(review);

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/reviews/1/00001"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(reviewRepository.findById(reviewKey).isEmpty());
    }

    @Test
    void updateReview() throws Exception {
        // First, create a review to update
        ReviewKey reviewKey = new ReviewKey("00001", 1L);
        Review review = new Review();
        review.setReviewId(reviewKey);
        review.setUser(userRepository.findById("00001").orElseThrow());
        review.setProduct(productRepository.findById(1L).orElseThrow());
        review.setReviewBody("This product was perfect for my skin");
        review.setScore(5);
        review.setDate("2025-Jan-05");
        reviewRepository.save(review);

        // Now, create the updated review data
        ObjectNode reviewJson = objectMapper.createObjectNode();
        reviewJson.put("userId", "00001");
        reviewJson.put("productId", 1L);
        reviewJson.put("reviewBody", "Updated review: This product works wonders!");
        reviewJson.put("score", 4);
        reviewJson.put("date", "2025-Jan-10");

        // Perform the update request
        MockHttpServletResponse response = mockMvc.perform(
                        put("/reviews/1/00001")
                                .contentType("application/json")
                                .content(reviewJson.toString()))
                .andReturn().getResponse();

        // Check that the response status is OK
        assertEquals(200, response.getStatus());

        // Retrieve the updated review from the repository
        Review updatedReview = reviewRepository.findById(reviewKey).orElseThrow();

        // Verify that the review has been updated
        assertEquals("Updated review: This product works wonders!", updatedReview.getReviewBody());
        assertEquals(4, updatedReview.getScore());
        assertEquals("2025-Jan-10", updatedReview.getDate());
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