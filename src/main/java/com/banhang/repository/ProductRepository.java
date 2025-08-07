package com.banhang.repository;

import com.banhang.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find by product name
    List<Product> findByProductNameContainingIgnoreCase(String productName);
    
    // Find by category
    List<Product> findByCategoryId(String categoryId);
    
    // Find by barcode
    Optional<Product> findByBarcode(String barcode);
    
    // Find active products
    List<Product> findByIsActiveTrue();
    
    // Find products with pagination
    Page<Product> findByIsActiveTrue(Pageable pageable);
    
    // Find by name and active status
    Page<Product> findByProductNameContainingIgnoreCaseAndIsActiveTrue(String productName, Pageable pageable);
    
    // Find by category and active status
    Page<Product> findByCategoryIdAndIsActiveTrue(String categoryId, Pageable pageable);
    
    // Find low stock products
    @Query("SELECT p FROM Product p WHERE p.stockQuantity <= :threshold AND p.isActive = true")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
    
    // Find products by price range
    @Query("SELECT p FROM Product p WHERE p.salePrice BETWEEN :minPrice AND :maxPrice AND p.isActive = true")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
    
    // Search products by multiple criteria
    @Query("SELECT p FROM Product p WHERE " +
           "(:name IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:categoryId IS NULL OR p.categoryId = :categoryId) AND " +
           "(:minPrice IS NULL OR p.salePrice >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.salePrice <= :maxPrice) AND " +
           "p.isActive = true")
    Page<Product> searchProducts(@Param("name") String name,
                                @Param("categoryId") String categoryId,
                                @Param("minPrice") Double minPrice,
                                @Param("maxPrice") Double maxPrice,
                                Pageable pageable);
    
    // Count active products
    long countByIsActiveTrue();
    
    // Count products by category
    long countByCategoryIdAndIsActiveTrue(String categoryId);
    
    // Get total stock value
    @Query("SELECT SUM(p.stockQuantity * p.salePrice) FROM Product p WHERE p.isActive = true")
    Double getTotalStockValue();
}