package com.example.cms.controller.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("Could not find users " + userId);
    }
}
