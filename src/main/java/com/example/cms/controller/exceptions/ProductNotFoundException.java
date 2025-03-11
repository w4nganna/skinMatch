package com.example.cms.controller.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {super("Could not find product" + productId);
    }
}
