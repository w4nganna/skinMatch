package com.example.cms.model.repository;

import com.example.cms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u.userId, u.email FROM Users u " +
                    "WHERE u.userId = :userId", nativeQuery = true)
    List<User> getUserIdAndEmail(@Param("userId") String userId);

    //Get list of users with matching userId OR email
    @Query(value =  "SELECT * FROM Users " +
            "WHERE userId = :userId " +
            "OR email = :email", nativeQuery = true)
    List<User> checkUniqueUser(@Param("userId") String userId, @Param("email") String email);

    // Return true if user or email already exists
    @Query(value =  "SELECT COUNT(*) > 0 FROM users " +
            "WHERE userId = :userId OR email = :email", nativeQuery = true)
    Boolean existsByUserIdOrEmail(@Param("userId") String userId, @Param("email") String email);

    // Check if a user with the same userId exists
    @Query(value = "SELECT COUNT(*) > 0 FROM users WHERE userId = :userId", nativeQuery = true)
    Boolean usernameExists(@Param("userId") String userId);

    // Check if a user with the same email exists
    @Query(value = "SELECT COUNT(*) > 0 FROM users WHERE email = :email", nativeQuery = true)
    Boolean emailExists(@Param("email") String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.favourites WHERE u.userId = :userId")
    Optional<User> findByIdWithFavourites(@Param("userId") String userId);

}
