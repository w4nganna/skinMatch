package com.example.cms.model.service;

import com.example.cms.controller.Dto.ProductDto;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkinCareRoutineService2 {

    private final ProductRepository productRepository;
    private static final List<String> CATEGORIES = Arrays.asList("Sunscreen", "Cleanser", "Toner", "Moisturizer", "Exfoliator");

    public void matchProducts(TestResults testResult) {
        List<Product> filteredProducts = filterProducts(testResult);
        Map<String, List<Product>> productsByCategory = groupByCategory(filteredProducts);
        List<Product> selectedProducts = selectBestProducts(productsByCategory, testResult.getConcerns());
        adjustSelectionForBudget(selectedProducts, productsByCategory, testResult.getBudget(), testResult.getConcerns());
        testResult.setRecommendedProducts(selectedProducts);
    }

    private List<Product> filterProducts(TestResults testResult) {
        List<Ingredient> avoidIngredients = testResult.getAvoidIngredients();
        return productRepository.findAll().stream()
                .filter(p -> matchesSkinType(p, testResult.getSkinType().getSkintypeId()))
                .filter(p -> doesNotContainAvoidIngredients(p, avoidIngredients))
                .collect(Collectors.toList());
    }

    private Map<String, List<Product>> groupByCategory(List<Product> products) {
        return products.stream().collect(Collectors.groupingBy(p -> p.getCategory().getCategoryName()));
    }

    private List<Product> selectBestProducts(Map<String, List<Product>> productsByCategory, List<Concern> userConcerns) {
        List<Product> selected = new ArrayList<>();
        for (String category : CATEGORIES) {
            productsByCategory.getOrDefault(category, Collections.emptyList()).stream()
                    .max(Comparator.comparingInt(p -> countMatchingConcerns(p, userConcerns)))
                    .ifPresent(selected::add);
        }
        return selected;
    }

    private void adjustSelectionForBudget(List<Product> selectedProducts,
                                          Map<String, List<Product>> productsByCategory,
                                          Float maxBudget,
                                          List<Concern> userConcerns) {
        double totalCost = selectedProducts.stream().mapToDouble(Product::getPrice).sum();
        if (totalCost <= maxBudget) {
            System.out.print("Initial selection <= budget");
            return;
        }

        //Min-heap to prioritize swapping out the least valuable products (least concerns matched)
        PriorityQueue<Product> minHeap = new PriorityQueue<>(Comparator
                .comparingInt((Product p) -> countMatchingConcerns(p, userConcerns))
                .thenComparing(Product::getPrice, Comparator.reverseOrder()));

        minHeap.addAll(selectedProducts); // Add all selected products to the heap

        while (totalCost > maxBudget && !minHeap.isEmpty()) {
            Product leastEffectiveProduct = minHeap.poll(); // Get the product with the least concerns matched
            String category = leastEffectiveProduct.getCategory().getCategoryName();

            List<Product> cheaperAlternatives = productsByCategory.getOrDefault(category, Collections.emptyList()).stream()
                    .filter(p -> p.getPrice() < leastEffectiveProduct.getPrice()) // Find cheaper options
                    .sorted(Comparator
                            .comparingInt((Product p) -> countMatchingConcerns(p, userConcerns)).reversed()
                            .thenComparing(Product::getPrice)) // Prefer more concerns matched, then lower price
                    .collect(Collectors.toList());

            for (Product alternative : cheaperAlternatives) {
                double newTotal = totalCost - leastEffectiveProduct.getPrice() + alternative.getPrice();
                if (newTotal <= maxBudget) {
                    selectedProducts.remove(leastEffectiveProduct);
                    selectedProducts.add(alternative);
                    totalCost = newTotal;
                    minHeap.add(alternative); // Add the new product to be considered in future swaps
                    break;
                }
            }
        }
    }


    private boolean matchesSkinType(Product product, Integer skinTypeId) {
        return product.getSkintypes().stream()
                .anyMatch(st -> st.getSkintypeId().equals(skinTypeId));
    }

    private boolean doesNotContainAvoidIngredients(Product product, List<Ingredient> avoidIngredients) {
        return avoidIngredients == null || avoidIngredients.isEmpty() ||
                product.getIngredients().stream().noneMatch(i -> avoidIngredients.contains(i.getIngredientId()));
    }

    private int countMatchingConcerns(Product product, List<Concern> userConcerns) {
        Set<Integer> concernIds = userConcerns.stream().map(Concern::getConcernId).collect(Collectors.toSet());
        return (int) product.getConcerns().stream().filter(c -> concernIds.contains(c.getConcernId())).count();
    }

    public List<ProductDto> getProductDtos(List<Product> products) {
        return products.stream().map(p -> new ProductDto(
                p.getProductId(), p.getName(), p.getBrand(), p.getPrice(), p.getImageURL(), p.getIngredients(), p.getAverageScore()
        )).collect(Collectors.toList());
    }
}
