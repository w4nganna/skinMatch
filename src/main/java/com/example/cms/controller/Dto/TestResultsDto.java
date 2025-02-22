package com.example.cms.controller.Dto;

import com.example.cms.model.entity.Ingredient;
import lombok.Getter;
import lombok.Setter;
import com.example.cms.model.entity.TestResults;
import com.example.cms.model.entity.Product;
import com.example.cms.model.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestResultsDto {
    private Float budget;
    private String skinType;
    private List<Ingredient> avoidIngredients;
    //private List<Concern> concerns;
    private String user;
}