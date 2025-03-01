package com.example.cms.controller;

import com.example.cms.controller.Dto.UserDto;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
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

    //Not needed? Can directly call in login controller
//    @GetMapping("/user/{userId}/{email}")
//    public Boolean isUniqueUserEmail(@PathVariable String userId, @PathVariable String email) {
//        // Check if no user with the same ID or email exists
//        return userRepository.existsByUserIdOrEmail(userId, email);
//    }

    //-------------------Put------------------
    @PutMapping("/users/{password}")
    public User updateUserPassword(@RequestBody UserDto userDto, @PathVariable("userId") String userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setPassword(userDto.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUserId(userId);
                    newUser.setPassword(userDto.getPassword());
                    newUser.setEmail(userDto.getEmail());
                    return userRepository.save(newUser);
                });
    }

    @PutMapping("/users/{email}")
    public User updateUserEmail(@RequestBody UserDto userDto, @PathVariable("userId") String userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setEmail(userDto.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUserId(userId);
                    newUser.setPassword(userDto.getPassword());
                    newUser.setEmail(userDto.getEmail());
                    return userRepository.save(newUser);
                });
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
    public void deleteUser(@PathVariable String userId) {
        userRepository.deleteById(userId);
    }

}
