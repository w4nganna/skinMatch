package com.example.cms.controller.Dto;

import com.example.cms.model.entity.*;
import lombok.Getter;
import lombok.Setter;
import com.example.cms.model.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestResultsDto {
    private Float budget;
    private Skintype skinType;
    private List<Ingredient> avoidIngredients;
    private List<Concern> concerns;
    private List<Product> recommendedProducts;
    private String user;
    private Long testResultId;
}