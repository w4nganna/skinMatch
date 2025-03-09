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
    @Query(value = "SELECT p FROM Product p " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'l2h' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'h2l' THEN p.price END DESC", nativeQuery = true)
    List<Product> findAllProductsSorted(@Param("sortBy") String sortBy);

    //------------Product search method II: SQL filter-----------
    @Query(value = "SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN p.concerns c " +
            "LEFT JOIN p.ingredients i " +
            "WHERE (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:brands IS NULL OR :brands = '' OR p.brand IN :brands) " +
            "AND (:types IS NULL OR :types = '' OR p.type IN :types) " +
            "AND (:concerns IS NULL OR :concerns = '' OR c.concernId IN :concerns) " +
            "AND (:avoidIngredients IS NULL OR :avoidIngredients = '' OR i.ingredientId NOT IN :avoidIngredients) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'price_low_to_high' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'price_high_to_low' THEN p.price END DESC", nativeQuery = true)
    List<Product> findFilteredProducts(
            @Param("maxPrice") Double maxPrice,
            @Param("brands") List<String> brands,
            @Param("types") List<String> types,
            @Param("concerns") List<Integer> concerns,
            @Param("avoidIngredients") List<Long> avoidIngredients,
            @Param("sortBy") String sortBy
    );
}
