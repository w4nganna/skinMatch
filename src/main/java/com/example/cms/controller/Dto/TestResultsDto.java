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
    private Integer skinType;
    private List<Long> avoidIngredients;
    //private List<Concern> concerns;
    private String user;
}