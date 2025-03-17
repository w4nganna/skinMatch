package com.example.cms.controller;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.controller.Dto.UserDto;
import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.Product;
import com.example.cms.model.entity.TestResults;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.ProductRepository;
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

    @Autowired
    public UserController(TestResultsRepository testResultsRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.testResultsRepository = testResultsRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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

    //Get favourite products
    @GetMapping("/users/{userId}/favs")
    public Set<ProductDto> getFavProds(@PathVariable String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return user.getFavourites().stream()
                .map(product -> new ProductDto(
                        product.getProductId(),
                        product.getName(),
                        product.getBrand(),
                        product.getPrice(),
                        product.getImageURL(),
                        product.getAlternatives().stream()
                                .map(Product::getProductId) // Extract alternative product IDs
                                .collect(Collectors.toList())
                ))
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
                        //Return 404 with message
                        .body("User with ID " + userId + " not found."));
    }

    //Update user ID
    @PutMapping("/users/{userId}/id")
    @Transactional
    public ResponseEntity<String> updateUserId(@PathVariable("userId") String oldUserId, @RequestBody Map<String, String> requestBody) {
        // Get new userId from request body
        String newUserId = requestBody.get("newUserId");
        if (newUserId == null || newUserId.isEmpty()) {
            return ResponseEntity.badRequest().body("New userId is required.");
        }

        // Check if the new user ID already exists in the repository
        if (userRepository.existsById(newUserId)) {
            return ResponseEntity.badRequest().body("New userId already exists.");
        }

        // Create new user with old user information
        return userRepository.findById(oldUserId).map(oldUser -> {
            // Get the test results linked to the old user
            TestResults testResults = oldUser.getTestResults();

            // Create a new user with the same details
            User newUser = new User(newUserId, oldUser.getEmail(), oldUser.getPassword());

            // Save the new user first
            userRepository.save(newUser);

            // If test results exist, update their user reference
            if (testResults != null) {
                // Set the test results to reference the new user and vice versa
                testResults.setUser(newUser);
                newUser.setTestResults(testResults);

                // Set old user repository as null
                oldUser.setTestResults(null);

                // Save the updated test results and user
                testResultsRepository.save(testResults);
                userRepository.save(newUser);
            }

            // Delete the old user after saving the new user and updating the test results
            userRepository.delete(oldUser);

            return ResponseEntity.ok("UserId updated successfully.");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Add or Remove favourite product
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
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found.");
        }

        userRepository.deleteById(userId);
        return ResponseEntity.ok("User with ID " + userId + " deleted successfully.");
    }
}
