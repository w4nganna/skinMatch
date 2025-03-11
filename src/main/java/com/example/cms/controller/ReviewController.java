package com.example.cms.controller;
import com.example.cms.controller.Dto.ReviewDto;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.model.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;
import com.example.cms.model.entity.ReviewKey;
import com.example.cms.model.entity.Product;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.ProductRepository;
import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    //Read all reviews for a product
    @GetMapping("/reviews/{productId}/{userId}")
    public List<Review> getReviewsByProduct(@PathVariable Long productId, @PathVariable String userId) {
        List<Review> reviews = reviewRepository.findByProductId(productId, userId);

        if (reviews.isEmpty()) {
            throw new RuntimeException("No reviews found for productId: " + productId);
        }

        return reviews;

    }
    //Read average score of a product
    @GetMapping("/reviews/{productId}/average")
    public Double getAverageScore(@PathVariable Long productId) {
        return reviewRepository.findAverageScoreByProductId(productId);
    }

    //Create review
    // INSERT INTO reviews(userId, productId, reviewBody, score, date) VALUES (00001, 1, 'This product was perfect for my skin')
    @PostMapping("/reviews")
    Review createReview(@RequestBody ReviewDto reviewDto){
       Review review = new Review();
       review.setReviewId(new ReviewKey(reviewDto.getUserId(), reviewDto.getProductId()));
       User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(
               () -> new UserNotFoundException(reviewDto.getUserId())
       );
       Product product = productRepository.findById(reviewDto.getProductId()).orElseThrow(
                () -> new ProductNotFoundException(reviewDto.getProductId())
        );

       review.setUser(user);
       review.setProduct(product);
       review.setReviewBody(reviewDto.getReviewBody());
       review.setScore(reviewDto.getScore());
       review.setDate(reviewDto.getDate());
       return reviewRepository.save(review);
    }

    //update review
    @PutMapping("/reviews/{productId}/{userId}")
    Review updateReview(@RequestBody ReviewDto reviewDto, @PathVariable Long productId, @PathVariable String userId){
        return reviewRepository.findById(new ReviewKey(userId, productId))
                .map(review -> {
                    review.setReviewId(new ReviewKey(reviewDto.getUserId(), reviewDto.getProductId()));

                    review.setUser(userRepository.findById(reviewDto.getUserId()).orElseThrow(
                            () -> new UserNotFoundException(reviewDto.getUserId())
                    ));
                    review.setProduct(productRepository.findById(reviewDto.getProductId()).orElseThrow(
                            () -> new ProductNotFoundException(reviewDto.getProductId())
                    ));
                    review.setReviewBody(reviewDto.getReviewBody());
                    review.setScore(reviewDto.getScore());
                    review.setDate(reviewDto.getDate());
                    return reviewRepository.save(review);
                })
                .orElseGet(() -> {
                    Review review = new Review();
                    review.setReviewId(new ReviewKey(reviewDto.getUserId(), reviewDto.getProductId()));
                    User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(
                            () -> new UserNotFoundException(reviewDto.getUserId())
                    );
                    Product product = productRepository.findById(reviewDto.getProductId()).orElseThrow(
                            () -> new ProductNotFoundException(reviewDto.getProductId())
                    );
                    review.setUser(user);
                    review.setProduct(product);
                    review.setReviewBody(reviewDto.getReviewBody());
                    review.setScore(reviewDto.getScore());
                    review.setDate(reviewDto.getDate());
                    return reviewRepository.save(review);
                });
    }

    @DeleteMapping("/reviews/{productId}/{userId}")
    void deleteReview(@PathVariable ("productId") Long productId, @PathVariable("userId") String userId){
        reviewRepository.deleteById(new ReviewKey(userId, productId));
    }
}
