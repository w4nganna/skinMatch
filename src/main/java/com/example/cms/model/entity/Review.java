package com.example.cms.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reviews")
public class Review {

    @EmbeddedId
    ReviewKey reviewId;

    @JsonIgnore // This will completely hide the user object in JSON responses
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"reviews"})
    private User user;

    @JsonIgnore // This will completely hide the product object in JSON responses
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    @JsonIgnoreProperties({"reviews"})
    private Product product;

    @NotNull
    private String date;

    @NotNull
    private int score; //Number of stars from 1-5

    @NotNull
    private String reviewBody;
}
