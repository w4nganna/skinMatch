package com.example.cms.model.repository;

import com.example.cms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value =  "SELECT * FROM Users " +
                    "WHERE userId = :userId " +
                    "OR email = :email", nativeQuery = true)
    List<User> checkUniqueUser(@Param("userId") String userId, @Param("email") String email);


}
