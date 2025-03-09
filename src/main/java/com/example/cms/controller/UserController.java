package com.example.cms.controller;

import com.example.cms.controller.Dto.UserDto;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.TestResults;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.TestResultsRepository;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final TestResultsRepository testResultsRepository;

    @Autowired
    public UserController(TestResultsRepository testResultsRepository, UserRepository userRepository) {
        this.testResultsRepository = testResultsRepository;
        this.userRepository = userRepository;
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
