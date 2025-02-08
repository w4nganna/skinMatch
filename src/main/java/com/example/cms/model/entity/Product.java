package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private String texture;

    @NotEmpty
    private String type;

    public Product(long productId, String name, String brand, Double price,
                   String category, String texture, String type) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.texture = texture;
        this.type = type;

    }



}
