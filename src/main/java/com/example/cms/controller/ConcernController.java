package com.example.cms.controller;

import com.example.cms.controller.exceptions.ConcernNotFoundException;
import com.example.cms.model.entity.Concern;
import com.example.cms.model.repository.ConcernRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ConcernController {
    @Autowired
    private final ConcernRepository repository;

    public ConcernController(ConcernRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/concerns")
    List<Concern> retrieveAllConcerns()
    {
        return repository.findAll();
    }

    @PostMapping("/concerns")
    Concern createSkintype(@RequestBody Concern newconcern)
    {
        return repository.save(newconcern);
    }

    @GetMapping("/concerns/{id}")
    Concern retrieveConcernByCode(@PathVariable("id") int code)
    {
        return repository.findById(code)
                .orElseThrow(() -> new ConcernNotFoundException(code));
    }

}
