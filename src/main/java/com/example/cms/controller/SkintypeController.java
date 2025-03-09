package com.example.cms.controller;

import com.example.cms.model.repository.SkintypeRepository;
import com.example.cms.model.entity.Skintype;
import com.example.cms.controller.exceptions.SkintypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SkintypeController {
    @Autowired
    private final SkintypeRepository repository;

    public SkintypeController(SkintypeRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/skintypes")
    List<Skintype> retrieveAllSkintypes()
    {
        return repository.findAll();
    }

    @GetMapping("/skintypes/{id}")
    Skintype retrieveSkintypeByCode(@PathVariable("id") int code)
    {
        return repository.findById(code)
                .orElseThrow(() -> new SkintypeNotFoundException(code));
    }

}
