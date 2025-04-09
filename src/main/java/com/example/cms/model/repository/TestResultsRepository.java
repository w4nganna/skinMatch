package com.example.cms.model.repository;

import com.example.cms.model.entity.TestResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestResultsRepository extends JpaRepository<TestResults, Long> {
    Optional<TestResults> findByUser_UserId(String userId);
}
