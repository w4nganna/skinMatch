package com.example.cms.model.repository;

import com.example.cms.model.entity.Concern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcernRepository extends JpaRepository<Concern, Integer> {
}
