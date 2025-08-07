package com.phenikaa.coffeeshop.repository;

import com.phenikaa.coffeeshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Product Repository - Data access layer for Product entity
 * Provides CRUD operations and custom queries for products
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find by custom product ID
    Optional<Product> findByProductId(String productId);
    
    // Find active products
    List<Product> findByActiveTrue();
    
    // Find by category
    List<Product> findByCategoryAndActiveTrue(String category);
    
    // Search products by name (case-insensitive)
    List<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name);
    
    // Find products with low stock
    @Query("SELECT p FROM Product p WHERE p.stock <= :threshold AND p.active = true")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
    
    // Find products in stock
    @Query("SELECT p FROM Product p WHERE p.stock > 0 AND p.active = true")
    List<Product> findInStockProducts();
    
    // Find products by price range
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.active = true")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
    
    // Get distinct categories
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.active = true ORDER BY p.category")
    List<String> findDistinctCategories();
    
    // Count products by category
    @Query("SELECT COUNT(p) FROM Product p WHERE p.category = :category AND p.active = true")
    Long countByCategory(@Param("category") String category);
    
    // Find products with profit margin
    @Query("SELECT p FROM Product p WHERE (p.price - p.costPrice) >= :minProfit AND p.active = true")
    List<Product> findByMinimumProfit(@Param("minProfit") Double minProfit);
}