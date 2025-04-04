package com.example.cms.controller;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.controller.Dto.UserDto;
import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.ProductRepository;
import com.example.cms.model.repository.ReviewRepository;
import com.example.cms.model.repository.TestResultsRepository;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final TestResultsRepository testResultsRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;


    @Autowired
    public UserController(TestResultsRepository testResultsRepository, UserRepository userRepository, ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.testResultsRepository = testResultsRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }
    //-------------------Get------------------
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    // Check if userId is unique
    @GetMapping("/signup/userId/{userId}")
    public Boolean isUserIdUnique(@PathVariable String userId) {
        // Return false if the userId already exists
        return !userRepository.usernameExists(userId);
    }

    // Check if email is unique
    @GetMapping("/signup/email/{email}")
    public ResponseEntity<Boolean> isEmailUnique(@PathVariable String email) {
        return ResponseEntity.ok(!userRepository.emailExists(email));
    }

    @GetMapping("/users/{userId}/favs")
    public Set<ProductDto> getFavProds(@PathVariable String userId) {
        User user = userRepository.findByIdWithFavourites(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return user.getFavourites().stream()
                .map(product -> {
                    // 🔄 Calculate and set the average score
                    Double avgScore = reviewRepository.findAverageScoreByProductId(product.getProductId());
                    product.setAverageScore(avgScore);


                    return new ProductDto(
                            product.getProductId(),
                            product.getName(),
                            product.getBrand(),
                            product.getPrice(),
                            product.getImageURL(),
                            product.getIngredients(),
                            product.getAverageScore()
                    );
                })
                .collect(Collectors.toSet());
    }

    //-------------------Put------------------
    //Update password
    @PutMapping("/users/{userId}/password")
    public ResponseEntity<String> updateUserPassword(@PathVariable("userId") String userId, @RequestBody Map<String, String> requestBody) {
        //Get user
        return userRepository.findById(userId)
                .map(user -> {
                    //Set password
                    user.setPassword(requestBody.get("password"));
                    //Save user
                    userRepository.save(user);
                    //Return 200
                    return ResponseEntity.ok("Updated successfully");
                })
                .orElseGet(() -> ResponseEntity
                        //Return 404
                        .status(HttpStatus.NOT_FOUND)
                        .body("User with ID " + userId + " not found."));
    }

    @PutMapping("/users/{userId}/email")
    public ResponseEntity<String> updateUserEmail(@PathVariable("userId") String userId, @RequestBody Map<String, String> requestBody) {
        return userRepository.findById(userId)
                .map(user -> {
                    // Directly set the email from the request body
                    user.setEmail(requestBody.get("email"));
                    //Save the updated user
                    userRepository.save(user);
                    //Return 200 OK with success message
                    return ResponseEntity.ok("Email updated successfully");
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("User with ID " + userId + " not found."));
    }

    //Update user ID
    @PutMapping("/users/{userId}/id")
    @Transactional
    public ResponseEntity<String> updateUserId(@PathVariable("userId") String oldUserId, @RequestBody Map<String, String> requestBody) {
        String newUserId = requestBody.get("newUserId");
        if (newUserId == null || newUserId.isEmpty()) {
            return ResponseEntity.badRequest().body("New userId is required.");
        }

        if (userRepository.existsById(newUserId)) {
            return ResponseEntity.badRequest().body("New userId already exists.");
        }

        return userRepository.findById(oldUserId).map(oldUser -> {
            // Clone old user into new user
            User newUser = new User(newUserId, oldUser.getEmail(), oldUser.getPassword());

            // Save new user before linking test results
            userRepository.saveAndFlush(newUser);

            // Step 2: Reassign TestResults
            TestResults testResults = oldUser.getTestResults();
            if (testResults != null) {
                // Unlink from old user
                oldUser.setTestResults(null);
                userRepository.saveAndFlush(oldUser);

                // Re-link to new user
                testResults.setUser(newUser);
                newUser.setTestResults(testResults);
                testResultsRepository.saveAndFlush(testResults);
            }

            // Migrate reviews
            List<Review> oldReviews = reviewRepository.findByUser_UserId(oldUserId);
            for (Review review : oldReviews) {
                ReviewKey newKey = new ReviewKey(newUserId, review.getProduct().getProductId());

                Review newReview = new Review();
                newReview.setReviewId(newKey);
                newReview.setUser(newUser);
                newReview.setProduct(review.getProduct());
                newReview.setDate(review.getDate());
                newReview.setScore(review.getScore());
                newReview.setReviewBody(review.getReviewBody());

                reviewRepository.save(newReview);
                reviewRepository.delete(review);
            }

            //Migrate Favs
            Set<Product> oldFavourites = Set.copyOf(oldUser.getFavourites());

            for (Product product : oldFavourites) {
                product.getUsers().remove(oldUser);
                product.getUsers().add(newUser);
                productRepository.save(product);
            }
            newUser.setFavourites(oldFavourites);

            userRepository.saveAndFlush(newUser);

            // Step 6: Delete old user
            userRepository.delete(oldUser);

            return ResponseEntity.ok("UserId updated successfully.");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }



    //Add or delete favourite product
    @PutMapping("/users/{userId}/favs/{productId}")
    public ResponseEntity<String> updateFavProds(@PathVariable("userId") String userId, @PathVariable("productId") Long productId) {
        //Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        //Find product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        //Remove if already favourited
        if (user.getFavourites().contains(product)) {
            //update user
            user.getFavourites().remove(product);
            userRepository.save(user);
            //update product
            product.getUsers().remove(user);
            productRepository.save(product);
            return ResponseEntity.ok("Product removed from favourites.");
        //Add if not favourited
        } else {
            //update user
            user.getFavourites().add(product);
            userRepository.save(user);
            //update product
            product.getUsers().add(user);
            productRepository.save(product);
            return ResponseEntity.ok("Product added to favourites.");
        }
    }

    //Clear favourite list
    @PutMapping("users/{userId}/favs/clear")
    public ResponseEntity<String> clearFavProds(@PathVariable("userId") String userId) {
        //Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.getFavourites().clear();
        userRepository.save(user);
        return ResponseEntity.ok("Favourites cleared.");
    }

    //-------------------Post------------------
    @PostMapping("/users")
    public User createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUserId(userDto.getUserId());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());

        return userRepository.save(newUser);
    }

    //-------------------Delete------------------
    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found.");
        }

        reviewRepository.deleteByUser_UserId(userId);
        userRepository.deleteById(userId);
        return ResponseEntity.ok("User with ID " + userId + " deleted successfully.");
    }
}
