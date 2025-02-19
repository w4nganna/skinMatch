package com.example.cms.controller;

import com.example.cms.model.repository.ProductRepository;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginSignupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public LoginSignupController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
