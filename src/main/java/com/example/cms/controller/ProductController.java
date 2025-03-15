package com.example.cms.controller;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.entity.Product;
import com.example.cms.model.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.cms.model.repository.ProductRepository;

import java.util.Set;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping()
public class ProductController {

    //Declare product search service object
    private final ProductSearchService productSearchService;
    private final ProductRepository productRepository;

    //Set constructor
    public ProductController(ProductSearchService productSearchService, ProductRepository productRepository) {
        this.productSearchService = productSearchService;
        this.productRepository = productRepository;
    }

    //-----------------GET------------------
    // Get all products
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDto(
                        product.getProductId(),
                        product.getName(),
                        product.getBrand(),
                        product.getPrice(),
                        product.getImageURL()
                ))
                .collect(Collectors.toList());
    }

    // Get product by ID
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }

    //Get product alternatives
    @GetMapping("/products/{productId}/alt")
    public Set<ProductDto> getProdAlts(@PathVariable Long productId) {
        Product prod = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Set<ProductDto> prodAlts = prod.getAlternatives().stream()
                .map(product -> new ProductDto(
                        product.getProductId(),
                        product.getName(),
                        product.getBrand(),
                        product.getPrice(),
                        product.getImageURL())).collect(Collectors.toSet());
        return prodAlts;
    }

    //------------Product search method I: Java filter-----------
    @GetMapping("/products/filterI")
    public List<ProductDto> getFilteredProductsI(
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false) List<Long> avoidIngredients,
            @RequestParam(required = false) List<Integer> concerns) {
        return productSearchService.getFilteredProductsI(maxPrice, sortBy, category, brands, types, avoidIngredients, concerns);
    }

    //------------Product search method II: SQL filter-----------
    @GetMapping("/products/filterII")
    public List<ProductDto> getFilteredProductsII(
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false) List<Long> avoidIngredients,
            @RequestParam(required = false) List<Integer> concerns) {
        return productSearchService.getFilteredProductsII(maxPrice, sortBy, category, brands, types, avoidIngredients, concerns);
    }

}
