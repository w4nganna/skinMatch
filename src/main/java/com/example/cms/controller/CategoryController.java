package com.example.cms.controller;

import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.Category;
import com.example.cms.model.entity.Product;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.ProductRepository;
import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public CategoryController(CategoryRepository categoryRepository, UserRepository userRepository)
    {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    List<Category> retrieveAll()
    {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category retrieveCategoryById (@PathVariable("id") Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return product.getCategory();
    }

}
