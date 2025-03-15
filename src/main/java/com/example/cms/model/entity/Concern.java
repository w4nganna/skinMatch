package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "concerns")

public class Concern {

    @Id
    @NotNull
    private Integer concernId;

    @NotEmpty
    private String description;

    // Many-to-Many relationship with TestResults
    @ManyToMany(mappedBy = "concerns")
    @JsonIgnore
    private List<TestResults> testResults  = new ArrayList<>();

    // Many-to-Many relationship with Product
    @ManyToMany(mappedBy = "concerns")
    @JsonIgnore
    private List<Product> products  = new ArrayList<>();

    public Concern(int id, String description)
    {
        this.concernId = id;
        this.description = description;
    }

}
