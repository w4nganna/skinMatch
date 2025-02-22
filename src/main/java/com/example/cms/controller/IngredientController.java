package com.example.cms.controller;

import com.example.cms.model.entity.Ingredient;
import com.example.cms.model.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class IngredientController {
    @Autowired
    private final IngredientRepository repository;

    public IngredientController(IngredientRepository repository) {
        this.repository = repository;
    }
    //-------------------Get Mapping---------------
    @GetMapping("/ingredients")
    List<Ingredient> retrieveAllIngredients() {
        return repository.findAll();
    }

    //-------------------Post Mapping---------------
    @PostMapping("/ingredients")
    Ingredient createIngredient(@RequestBody Ingredient newIngredient) {
        return repository.save(newIngredient);
    }

    //-------------------Delete Mapping---------------
    @DeleteMapping("/ingredients/{id}")
    void deleteIngredient(@PathVariable("id") Long ingredientId) {
        repository.deleteById(ingredientId);
    }
}
