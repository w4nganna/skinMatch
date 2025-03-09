package com.example.cms.model.repository;

import com.example.cms.model.entity.Skintype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkintypeRepository extends JpaRepository<Skintype, Integer> {
}
