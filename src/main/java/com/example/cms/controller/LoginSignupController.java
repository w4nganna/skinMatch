package com.example.cms.controller;

import com.example.cms.controller.Dto.UserDto;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.ProductRepository;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class LoginSignupController {

    @Autowired
    private UserRepository userRepository;
    private final UserController userController;

    public LoginSignupController(UserRepository userRepository, UserController userController) {
        this.userRepository = userRepository;
        this.userController = userController;
    }

    // check user data before passing the sign up
    @GetMapping("/signup/{userId}/{email}")
    public Boolean checkUser(@PathVariable String userId, @PathVariable String email) {
        // return true if found user, so frontend know to let user rename the userid or change email
        return userController.getUserIdAndEmail(userId, email);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody UserDto userDto) {
        boolean createSuccessful = userController.createUser(userDto) != null;

        if (createSuccessful) {
            return ResponseEntity.ok("createUser successful");
        } else {
            return ResponseEntity.badRequest().body("createUser failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        try {
            User user = userController.getUserById(userDto.getUserId());  // Attempt to fetch user
            if (user.getPassword().equals(userDto.getPassword())) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.badRequest().body("Login failed: Incorrect password");
            }
        } catch (UserNotFoundException e) {  // catch if no such user
            return ResponseEntity.badRequest().body("Login failed: User not found");
        }
    }








}
