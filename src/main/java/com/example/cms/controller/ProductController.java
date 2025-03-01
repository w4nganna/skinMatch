package com.example.cms.controller;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.cms.model.repository.ProductRepository;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //Declare product search service object
    private final ProductSearchService productSearchService;

    //Set constructor
    public ProductController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    //-----------------GET------------------
    //------------Product search method I: Java filter-----------
    @GetMapping("/filterI")
    public List<ProductDto> getFilteredProductsI(
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false) List<Long> avoidIngredients,
            @RequestParam(required = false) List<Integer> concerns) {
        return productSearchService.getFilteredProductsI(maxPrice, sortBy, brands, types, avoidIngredients, concerns);
    }

    //------------Product search method II: SQL filter-----------
    @GetMapping("/filterII")
    public List<ProductDto> getFilteredProductsII(
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false) List<Long> avoidIngredients,
            @RequestParam(required = false) List<Integer> concerns) {
        return productSearchService.getFilteredProductsII(maxPrice, sortBy, brands, types, avoidIngredients, concerns);
    }

}
