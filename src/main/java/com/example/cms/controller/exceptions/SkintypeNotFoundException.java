package com.example.cms.controller.exceptions;

public class SkintypeNotFoundException extends RuntimeException {

    public SkintypeNotFoundException(int code) {
        super("Could not find skintype " + code);
    }
}
