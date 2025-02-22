package com.example.cms.controller;

import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.TestResults;
import com.example.cms.model.repository.TestResultsRepository;
import com.example.cms.controller.Dto.TestResultsDto;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import com.example.cms.model.entity.Product;
import java.util.List;
import java.util.ArrayList;


@CrossOrigin
@RestController
@RequestMapping("/test-results") // Base path for consistency
public class TestResultsController {

    private final TestResultsRepository testResultsRepository;
    private final UserRepository userRepository;

    @Autowired
    public TestResultsController(TestResultsRepository testResultsRepository, UserRepository userRepository) {
        this.testResultsRepository = testResultsRepository;
        this.userRepository = userRepository;
    }

    //-------------------Get Mapping---------------
    @GetMapping("/{id}") //Maybe not needed?
    public TestResults getTestResultById(@PathVariable Long id) {
        return testResultsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TestResult not found with id: " + id));
    }

    @GetMapping("/user/{userId}")
    public TestResults getTestResultsForUser(@PathVariable String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return user.getTestResults();
    }

    @GetMapping("/")
    public List<TestResults> getTestResults() {
        List<TestResults> testResults = testResultsRepository.findAll();
        if (testResults.isEmpty()) {
            throw new RuntimeException("No test results found.");
        }
        return testResults;
    }

    //-------------------Post Mapping---------------
    @PostMapping("/")
    @Transactional
    public TestResults createTestResult(@RequestBody TestResultsDto testResultsDTO) {
        //Fetch user from the database
        User user = userRepository.findById(testResultsDTO.getUser())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + testResultsDTO.getUser()));

        // Check if the user already has a TestResults object
        if (user.getTestResults() != null) {
            // Delete the old test result before saving the new one
            testResultsRepository.delete(user.getTestResults());
        }

        //-----Set the attributes & relationships-----
        TestResults testResult = new TestResults();

        //Set the user in test results
        testResult.setUser(user);
        //Set budget
        testResult.setBudget(testResultsDTO.getBudget());
        //Set skin type
        testResult.setSkinType(testResultsDTO.getSkinType());
        //Set avoid ingredients
        testResult.setAvoidIngredients(testResultsDTO.getAvoidIngredients());

        //-----------Call matching algorithm before saving--------
        matchingAlgorithm(testResult);//-------Placeholder for actual service!

        //Save the test result
        TestResults savedResult = testResultsRepository.save(testResult);

        //Set the test results in user
        user.setTestResults(testResult);
        userRepository.save(user);

        //Return TestResults
        return savedResult;
    }

    private void matchingAlgorithm(TestResults testResult) {
        //Logic to assign recommended products
        List<Product> recommendedProducts = new ArrayList<>();

        //------...---------

        //Set products list
        testResult.setRecommendedProducts(recommendedProducts);
    }

    //-------------------Delete Mapping---------------
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTestResult(@PathVariable Long id) {
        TestResults testResult = testResultsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TestResult not found with id: " + id));

        // Remove reference from user to avoid foreign key issues
        User user = testResult.getUser();
        if (user != null) {
            user.setTestResults(null);
            userRepository.save(user);
        }

        testResultsRepository.delete(testResult);
    }

    @DeleteMapping("/user/{userId}")
    @Transactional
    public void deleteTestResult(@PathVariable String userId) {
        //Fetch the user from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        //Get the TestResults
        TestResults testResult = user.getTestResults();
        if (testResult == null) {
            throw new RuntimeException("No TestResults found for user with id: " + userId);
        }

        //Remove the reference from User
        user.setTestResults(null);
        userRepository.save(user);

        //Delete the TestResults record
        testResultsRepository.delete(testResult);
    }
}
