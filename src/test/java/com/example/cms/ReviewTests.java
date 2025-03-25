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
                        delete("/reviews/00001/1"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(reviewRepository.findById(reviewKey).isEmpty());
    }
}