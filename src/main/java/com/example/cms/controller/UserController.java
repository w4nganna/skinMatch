package com.example.cms.controller;

import com.example.cms.controller.Dto.UserDto;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @GetMapping("/user/{userId}/{email}")
    public Boolean getUserIdAndEmail(@PathVariable String userId, @PathVariable String email) {
        return userRepository.checkUniqueUser(userId, email) != null;
    }

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

    @PostMapping("/users")
    public User createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUserId(userDto.getUserId());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());

        return userRepository.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String userId) {
        userRepository.deleteById(userId);
    }

}
