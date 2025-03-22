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
@Table(name = "skintypes")
public class Skintype {

    @Id
    @NotNull
    private Integer skintypeId;

    @NotEmpty
    private String description;

    // Many-to-Many relationship with Product
<<<<<<< HEAD
    @ManyToMany(mappedBy = "skintypes")
    @JsonIgnore
=======
    @ManyToMany(mappedBy = "skintypeId")
>>>>>>> 247a1d0 (6.3.7 updates)
    private List<Product> products  = new ArrayList<>();

    public Skintype(int id, String description)
    {
        this.skintypeId = id;
        this.description = description;
    }

}
