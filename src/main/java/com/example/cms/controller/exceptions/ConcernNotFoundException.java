package com.example.cms.controller.exceptions;

public class ConcernNotFoundException extends RuntimeException {

    public ConcernNotFoundException(int code) {
        super("Could not find concern " + code);
    }
}
