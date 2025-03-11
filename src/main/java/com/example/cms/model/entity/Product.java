package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @NotNull
    // not sure if we need it
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private long productId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String brand;

    @NotNull
    private Double price;

    @NotEmpty
    private String category;

    @NotEmpty
    private String type;

    @NotEmpty
    private String imageURL;

    // Many-to-Many relationship with TestResults
    @ManyToMany(mappedBy = "recommendedProducts")
    private List<TestResults> testResults  = new ArrayList<>();

    //Many-to-Many relationship with Ingredients
    @ManyToMany
    @JoinTable(
            name = "ProductIngredients",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private List<Ingredient> ingredients  = new ArrayList<>();

    //Many-to-Many relationship with Concerns
    @ManyToMany
    @JoinTable(
            name = "userSkincareConcerns",
            joinColumns = @JoinColumn(name = "testResultId"),
            inverseJoinColumns = @JoinColumn(name = "concernId")
    )
    private List<Concern> concerns;

    //Many-to-Many relationship with Skintype
    @ManyToMany
    @JoinTable(
            name = "userSkintype",
            joinColumns = @JoinColumn(name = "testResultId"),
            inverseJoinColumns = @JoinColumn(name = "skintypeId")
    )
    private List<Skintype> skintypes;

    public Product(long productId, String name, String brand, Double price,
                   String category, String type, String imageURL, List<TestResults> testResults,
                   List<Ingredient> ingredients, List<Concern> concerns, List<Skintype> skintypes) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.type = type;
        this.imageURL = imageURL;
        this.testResults = testResults;
        this.ingredients = ingredients;
        this.concerns = concerns;
        this.skintypes = skintypes;

    }

}
