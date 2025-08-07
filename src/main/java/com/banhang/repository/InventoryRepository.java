package com.banhang.repository;

import com.banhang.model.entity.Inventory;
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
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
    // Find by product
    Optional<Inventory> findByProduct(Product product);
    
    // Find by product ID
    @Query("SELECT i FROM Inventory i WHERE i.product.id = :productId")
    Optional<Inventory> findByProductId(@Param("productId") Long productId);
    
    // Find by location
    List<Inventory> findByLocation(String location);
    
    // Find by supplier
    List<Inventory> findBySupplierId(String supplierId);
    
    // Find low stock items
    @Query("SELECT i FROM Inventory i WHERE i.currentStock <= i.minStock")
    List<Inventory> findLowStockItems();
    
    // Find low stock items with pagination
    @Query("SELECT i FROM Inventory i WHERE i.currentStock <= i.minStock")
    Page<Inventory> findLowStockItems(Pageable pageable);
    
    // Find overstock items
    @Query("SELECT i FROM Inventory i WHERE i.currentStock >= i.maxStock")
    List<Inventory> findOverstockItems();
    
    // Find items by stock range
    @Query("SELECT i FROM Inventory i WHERE i.currentStock BETWEEN :minStock AND :maxStock")
    List<Inventory> findByStockRange(@Param("minStock") Integer minStock, 
                                    @Param("maxStock") Integer maxStock);
    
    // Search inventory by multiple criteria
    @Query("SELECT i FROM Inventory i WHERE " +
           "(:productName IS NULL OR LOWER(i.product.productName) LIKE LOWER(CONCAT('%', :productName, '%'))) AND " +
           "(:location IS NULL OR LOWER(i.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:supplierId IS NULL OR i.supplierId = :supplierId) AND " +
           "(:lowStock IS NULL OR (:lowStock = true AND i.currentStock <= i.minStock))")
    Page<Inventory> searchInventory(@Param("productName") String productName,
                                   @Param("location") String location,
                                   @Param("supplierId") String supplierId,
                                   @Param("lowStock") Boolean lowStock,
                                   Pageable pageable);
    
    // Get total inventory value
    @Query("SELECT SUM(i.currentStock * i.costPrice) FROM Inventory i")
    Double getTotalInventoryValue();
    
    // Get inventory value by location
    @Query("SELECT SUM(i.currentStock * i.costPrice) FROM Inventory i WHERE i.location = :location")
    Double getInventoryValueByLocation(@Param("location") String location);
    
    // Get inventory value by supplier
    @Query("SELECT SUM(i.currentStock * i.costPrice) FROM Inventory i WHERE i.supplierId = :supplierId")
    Double getInventoryValueBySupplier(@Param("supplierId") String supplierId);
    
    // Count low stock items
    @Query("SELECT COUNT(i) FROM Inventory i WHERE i.currentStock <= i.minStock")
    long countLowStockItems();
    
    // Count overstock items
    @Query("SELECT COUNT(i) FROM Inventory i WHERE i.currentStock >= i.maxStock")
    long countOverstockItems();
    
    // Get inventory statistics by location
    @Query("SELECT i.location, COUNT(i), SUM(i.currentStock), SUM(i.currentStock * i.costPrice) " +
           "FROM Inventory i " +
           "GROUP BY i.location " +
           "ORDER BY i.location")
    List<Object[]> getInventoryStatsByLocation();
    
    // Get inventory statistics by supplier
    @Query("SELECT i.supplierId, COUNT(i), SUM(i.currentStock), SUM(i.currentStock * i.costPrice) " +
           "FROM Inventory i " +
           "WHERE i.supplierId IS NOT NULL " +
           "GROUP BY i.supplierId " +
           "ORDER BY i.supplierId")
    List<Object[]> getInventoryStatsBySupplier();
    
    // Find items that need restocking
    @Query("SELECT i FROM Inventory i WHERE i.currentStock < (i.minStock * 1.5)")
    List<Inventory> findItemsNeedingRestock();
    
    // Get products that can fulfill order quantity
    @Query("SELECT i FROM Inventory i WHERE i.currentStock >= :quantity")
    List<Inventory> findProductsWithSufficientStock(@Param("quantity") Integer quantity);
}