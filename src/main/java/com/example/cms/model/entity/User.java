package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @NotEmpty
    // not sure if this is needed
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment userId
    private String userId;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty
    @Email // Ensures valid email format
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;


    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
