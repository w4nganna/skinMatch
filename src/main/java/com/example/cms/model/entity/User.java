package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @NotEmpty
    //  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment userId
    private String userId;

    @NotEmpty
    @Email // Ensures valid email format
    //Removed 'unique = true' to allow updating username (and we have FE validation for unique
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    // User has a reference to TestResults, but TestResults owns the relationship
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "testResultsId")
    @JsonIgnore //Prevent recursion issues - Don't return test results when getting user
    private TestResults testResults;

    //Many-to-Many relationship with products
    @JsonManagedReference
    @ManyToMany()
    @JoinTable(
            name = "userFavourites",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "productId")
    )
    private Set<Product> favourites = new HashSet<>();

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
