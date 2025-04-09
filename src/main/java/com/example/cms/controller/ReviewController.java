package com.example.cms.controller;
import com.example.cms.controller.Dto.ReviewDto;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.model.repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
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
import java.util.Objects;

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
    @GetMapping("/reviews/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable Long productId) {

        List<Review> reviews = reviewRepository.findByProductId(productId);

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
       Review savedReview = reviewRepository.save(review);

       Double avgScore = reviewRepository.findAverageScoreByProductId(product.getProductId());
       product.setAverageScore(avgScore);
       productRepository.save(product);

       return savedReview;
    }

    //update review
    @PutMapping("/reviews/{productId}/{userId}")
    public ResponseEntity<Review> updateReview(@RequestBody ReviewDto reviewDto,
                                               @PathVariable Long productId,
                                               @PathVariable String userId) {
        // Validate request body
        if (reviewDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if body is missin
        }

//        if (!Objects.equals(reviewDto.getUserId(), userId)) {
//            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if userId mismatch
//        } else if (!Objects.equals(reviewDto.getProductId(), productId)) {
//            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if productId mismatch
//        }

        ReviewKey reviewKey = new ReviewKey(userId, productId);

        Review updatedReview = reviewRepository.findById(reviewKey)
                .map(review -> {
                    review.setReviewBody(reviewDto.getReviewBody());
                    review.setScore(reviewDto.getScore());
                    review.setDate(reviewDto.getDate());
                    return reviewRepository.save(review);
                })
                .orElseGet(() -> {
                    Review review = new Review();
                    review.setReviewId(reviewKey);

                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFoundException(userId));
                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new ProductNotFoundException(productId));

                    review.setUser(user);
                    review.setProduct(product);
                    review.setReviewBody(reviewDto.getReviewBody());
                    review.setScore(reviewDto.getScore());
                    review.setDate(reviewDto.getDate());

                    return reviewRepository.save(review);
                });
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Double avgScore = reviewRepository.findAverageScoreByProductId(productId);
        product.setAverageScore(avgScore);
        productRepository.save(product);

        return ResponseEntity.ok(updatedReview);


    }

    @DeleteMapping("/reviews/{productId}/{userId}")
    void deleteReview(@PathVariable ("productId") Long productId, @PathVariable("userId") String userId){
        reviewRepository.deleteById(new ReviewKey(userId, productId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Double avgScore = reviewRepository.findAverageScoreByProductId(productId);
        product.setAverageScore(avgScore);
        productRepository.save(product);
    }

}
