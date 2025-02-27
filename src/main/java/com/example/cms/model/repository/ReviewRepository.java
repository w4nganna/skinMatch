package com.example.cms.model.repository;

import com.example.cms.model.entity.Review;
import com.example.cms.model.entity.ReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewKey> {

}
