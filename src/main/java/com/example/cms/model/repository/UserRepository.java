package com.example.cms.model.repository;

import com.example.cms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM Users u " +
                    "WHERE u.userId = :userId", nativeQuery = true)
    User findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<User> findAll();

}
