package com.example.cms.model.repository;

import com.example.cms.model.entity.Review;
import com.example.cms.model.entity.ReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewKey> {
    //------------Review search by Product -----------
    @Query("SELECT r FROM Review r WHERE r.reviewId.productId = :productId ORDER BY CASE WHEN r.reviewId.userId = :userId THEN 0 ELSE 1 END")
    List<Review> findByProductId(@Param("productId") Long productId, @Param("userId") String userId);
    //------------Average score of a product -----------
    @Query("SELECT COALESCE(AVG(r.score), 0) FROM Review r WHERE r.reviewId.productId = :productId")
    Double findAverageScoreByProductId(@Param("productId") Long productId);
}
