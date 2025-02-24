package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "skintypes")

public class Skintype {

    @Id
    @NotNull
    private int skintypeId;

    @NotEmpty
    private String description;

    public Skintype(int id, String description)
    {
        this.skintypeId = id;
        this.description = description;
    }

}
