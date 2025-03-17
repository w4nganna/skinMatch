package com.example.cms.model.repository;

import com.example.cms.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //------------Product search method I: Java filter-----------
    @Query(value = "SELECT * FROM products p " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'l2h' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'h2l' THEN p.price END DESC", nativeQuery = true)
    List<Product> findAllProductsSorted(@Param("sortBy") String sortBy);

    //------------Product search method II: SQL filter-----------
    @Query(value = "SELECT p.* FROM products p " +
            "LEFT JOIN ProductConcerns pc ON p.productId = pc.productId " +
            "LEFT JOIN ProductIngredients pi ON p.productId = pi.productId " +
            "LEFT JOIN ProductCategory pca ON p.productId = pca.productId " +
            "WHERE (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:brands IS NULL OR :brands = '' OR p.brand IN :brands) " +
            "AND (:category IS NULL OR :category = '' OR pca.categoryId = :category) " +
            "AND (:types IS NULL OR :types = '' OR p.type IN :types) " +
            "AND (:concerns IS NULL OR :concerns = '' OR pc.concernId IN :concerns) " +
            "AND (:avoidIngredients IS NULL OR :avoidIngredients = '' OR pi.ingredientId NOT IN :avoidIngredients) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'price_low_to_high' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'price_high_to_low' THEN p.price END DESC", nativeQuery = true)
    List<Product> findFilteredProducts(
            @Param("maxPrice") Double maxPrice,
            @Param("brands") List<String> brands,
            @Param("category") Integer category,
            @Param("types") List<String> types,
            @Param("concerns") List<Integer> concerns,
            @Param("avoidIngredients") List<Long> avoidIngredients,
            @Param("sortBy") String sortBy
    );
    //------------Product average Rating -----------
    @Query("SELECT COALESCE(AVG(r.score), 0) FROM Review r WHERE r.reviewId.productId = :productId")
    Double findAverageScoreByProductId(@Param("productId") Long productId);
}
