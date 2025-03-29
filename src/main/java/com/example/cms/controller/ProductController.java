package com.example.cms.controller;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.controller.exceptions.ProductNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.example.cms.model.entity.User;
import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.entity.Product;
import com.example.cms.model.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.cms.model.repository.ProductRepository;
import com.example.cms.model.repository.ReviewRepository;

import java.util.Set;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping()
public class ProductController {

    //Declare product search service object
    private final ProductSearchService productSearchService;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    //Set constructor
    public ProductController(ProductSearchService productSearchService, ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productSearchService = productSearchService;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    //-----------------GET------------------
    // Get all products
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> {
                    // Fetch the average score from the ReviewRepository
                    Double avgScore = reviewRepository.findAverageScoreByProductId(product.getProductId());
                    product.setAverageScore(avgScore);

                    return new ProductDto(
                            product.getProductId(),
                            product.getName(),
                            product.getBrand(),
                            product.getPrice(),
                            product.getImageURL(),
                            product.getIngredients(),
                            product.getAverageScore()

                    );
                })
                .collect(Collectors.toList());
    }


    // Get product by ID
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        // Fetch the average score from ReviewRepository
        Double avgScore = reviewRepository.findAverageScoreByProductId(productId);
        product.setAverageScore(avgScore);

        return ResponseEntity.ok(ProductDto.fromEntity(product));
    }


    @GetMapping("/products/{productId}/alt")
    public ResponseEntity<List<ProductDto>> getProductAltById(@PathVariable Long productId) {
        List<Product> alternatives = productRepository.findAlternativeProducts(productId);
        List<ProductDto> alternativeDtos = alternatives.stream()
                .map(product -> {
                    product.setAverageScore(reviewRepository.findAverageScoreByProductId(product.getProductId()));
                    return ProductDto.fromEntity(product);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(alternativeDtos);
    }

    //Get all unique brands
    @GetMapping("/products/brands")
    public List<String> findAllBrands() {
        return productRepository.findAllBrands();
    }

    //------------Product search method I: Java filter-----------
    @GetMapping("/products/filterI")
    public List<ProductDto> getFilteredProductsI(
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) List<Integer> categories,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<Integer> types,
            @RequestParam(required = false) List<Long> avoidIngredients,
            @RequestParam(required = false) List<Integer> concerns) {

        return productSearchService.getFilteredProductsI(maxPrice, sortBy, categories, brands, types, avoidIngredients, concerns);
    }

    //------------Product search method II: SQL filter-----------
    @GetMapping("/products/filterII")
    public List<ProductDto> getFilteredProductsII(
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false)
            List<String> brands,
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false) List<Long> avoidIngredients,
            @RequestParam(required = false) List<Integer> concerns) {
        return productSearchService.getFilteredProductsII(maxPrice, sortBy, category, brands, types, avoidIngredients, concerns);
    }



}

