package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category {

    @Id
    @NotNull
    private Integer categoryId;
    private String categoryName;

    // Many-to-One relationship with Product
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public Category(int id, String name)
    {
        this.categoryId = id;
        this.categoryName = name;
    }

}
