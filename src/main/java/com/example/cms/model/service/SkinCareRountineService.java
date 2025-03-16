package com.example.cms.model.service;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.model.entity.Concern;
import com.example.cms.model.entity.Ingredient;
import com.example.cms.model.entity.Product;
import com.example.cms.model.entity.TestResults;
import com.example.cms.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkinCareRountineService {

    // repo
    private final ProductRepository productRepository;

    /*
    *
    * 1. Avoid ingredients and skin type is a strict filter - returned products need to meet it (see product service for ideas on how to implement it)
    * 2. Return 5 products, 1 per category of skincare (Sunscreen, Cleanser, Toner, Moisturizer, Exfoliator)
    * 3. For skin concerns criteria, we just select the product (1 per category) that meets the most concerns (out of the products filtered for ingredients and skin type)
    * 4. Budget is for the total cost of the 5 products - for the budget, you're going to receive one value ("maxprice") - p1 + p2 + ... + p5 <= maxprice
    *
    * */

    // fetch all products
    List<Product> products = productRepository.findAll();

//    public List<ProductDto> matchProducts() {
//        // temp init Dto (to make system not report red err
//        // when modify later.
//        long id = 111;
//        String name = "";
//        String brand = "";
//        Double price = 0.0;
//        String imageURL = "";
//        ProductDto productDto = new ProductDto(id, name, brand, price, imageURL);
//
//        List<ProductDto> list = new ArrayList<>();
//        list.add(productDto);
//
//        return list;
//    }

    public void matchProducts(TestResults testResult) {
        // Get all products from the repository
        List<Product> allProducts = productRepository.findAll();

        // Extract user preferences from test results
        String skinTypeDescription = testResult.getSkinType().getDescription();
        List<Ingredient> avoidIngredients = testResult.getAvoidIngredients();
        List<Concern> userConcerns = testResult.getConcerns();
        Float maxBudget = testResult.getBudget();

        // List of product categories we need to match
        List<String> categories = Arrays.asList("Sunscreen", "Cleanser", "Toner", "Moisturizer", "Exfoliator");

        // Filter products based on user's skin type and ingredients to avoid
        List<Product> filteredProducts = allProducts.stream()
                .filter(product -> matchesSkinType(product, skinTypeDescription))
                .filter(product -> !containsAnyAvoidIngredient(product, avoidIngredients))
                .collect(Collectors.toList());

        // Group filtered products by category
        Map<String, List<Product>> productsByCategory = filteredProducts.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        // Selected products for the routine (one per category)
        List<Product> selectedProducts = new ArrayList<>();

        // For each category, select the product that matches the most concerns
        for (String category : categories) {
            List<Product> productsInCategory = productsByCategory.getOrDefault(category, Collections.emptyList());

            if (!productsInCategory.isEmpty()) {
                // Sort products by concern match count (descending) and then by price (ascending)
                productsInCategory.sort(
                        Comparator.comparing((Product p) -> countMatchingConcerns(p, userConcerns)).reversed()
                                .thenComparing(Product::getPrice)
                );

                // Add the best matching product for this category
                selectedProducts.add(productsInCategory.get(0));
            }
        }

        // Adjust selection to meet budget constraints
        adjustSelectionForBudget(selectedProducts, productsByCategory, maxBudget, userConcerns);

        // Set the recommended products in the test result
        testResult.setRecommendedProducts(selectedProducts);
    }

    // Check if product matches the user's skin type
    private boolean matchesSkinType(Product product, String skinTypeDescription) {
        // Match product type with skin type description (allowing "normal" type for all skin types)
        return product.getType().equalsIgnoreCase(skinTypeDescription) ||
                product.getType().equalsIgnoreCase("normal");
    }

    // Check if product contains any ingredient to avoid
    private boolean containsAnyAvoidIngredient(Product product, List<Ingredient> avoidIngredients) {
        if (avoidIngredients == null || avoidIngredients.isEmpty()) {
            return false;
        }

        // Extract IDs of ingredients to avoid for easier comparison
        Set<Long> avoidIngredientIds = avoidIngredients.stream()
                .map(Ingredient::getIngredientId)
                .collect(Collectors.toSet());

        // Check if product contains any ingredient to avoid
        return product.getIngredients().stream()
                .anyMatch(ingredient -> avoidIngredientIds.contains(ingredient.getIngredientId()));
    }

    // Count how many of the user's concerns are addressed by the product
    private int countMatchingConcerns(Product product, List<Concern> userConcerns) {
        if (userConcerns == null || userConcerns.isEmpty() || product.getConcerns() == null) {
            return 0;
        }

        // Extract IDs of user concerns for easier comparison
        Set<Integer> userConcernIds = userConcerns.stream()
                .map(Concern::getConcernId)
                .collect(Collectors.toSet());

        // Count matching concerns
        return (int) product.getConcerns().stream()
                .filter(concern -> userConcernIds.contains(concern.getConcernId()))
                .count();
    }

    // Adjust product selection to stay within budget
    private void adjustSelectionForBudget(List<Product> selectedProducts,
                                          Map<String, List<Product>> productsByCategory,
                                          Float maxBudget,
                                          List<Concern> userConcerns) {
        // Calculate total cost of selected products
        double totalCost = selectedProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        // If total cost exceeds budget, replace most expensive products with cheaper alternatives
        if (totalCost > maxBudget) {
            // Sort selected products by price (descending) to replace most expensive first
            selectedProducts.sort(Comparator.comparing(Product::getPrice).reversed());

            for (int i = 0; i < selectedProducts.size() && totalCost > maxBudget; i++) {
                Product currentProduct = selectedProducts.get(i);
                String category = currentProduct.getCategory();

                // Find cheaper alternatives in the same category
                List<Product> alternatives = productsByCategory.getOrDefault(category, Collections.emptyList()).stream()
                        .filter(p -> p.getPrice() < currentProduct.getPrice())
                        .sorted(Comparator.comparing((Product p) -> countMatchingConcerns(p, userConcerns)).reversed()
                                .thenComparing(Product::getPrice))
                        .collect(Collectors.toList());

                // If we found a cheaper alternative, replace the current product
                if (!alternatives.isEmpty()) {
                    // Subtract current product's price from total cost
                    totalCost -= currentProduct.getPrice();

                    // Find the best alternative that keeps us within budget
                    for (Product alternative : alternatives) {
                        if (totalCost + alternative.getPrice() <= maxBudget) {
                            selectedProducts.set(i, alternative);
                            totalCost += alternative.getPrice();
                            break;
                        }
                    }

                    // If no alternative fits within budget, use the cheapest one
                    if (totalCost == maxBudget - currentProduct.getPrice()) {
                        Product cheapestAlternative = alternatives.get(alternatives.size() - 1);
                        selectedProducts.set(i, cheapestAlternative);
                        totalCost += cheapestAlternative.getPrice();
                    }
                }
            }
        }
    }

    // Convert the entity list to DTO list for controller use
    public List<ProductDto> getProductDtos(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDto(
                        product.getProductId(),
                        product.getName(),
                        product.getBrand(),
                        product.getPrice(),
                        product.getImageURL()
                ))
                .collect(Collectors.toList());
    }

    // Public method called by controller
    public List<ProductDto> matchProducts() {
        // This is just a placeholder method to maintain compatibility
        // In practice, the controller should call matchProducts(TestResults)
        return new ArrayList<>();
    }


}
