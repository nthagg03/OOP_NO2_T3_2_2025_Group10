package com.phenikaa.coffeeshop.repository;

import com.phenikaa.coffeeshop.model.OrderItem;
import com.phenikaa.coffeeshop.model.Order;
import com.phenikaa.coffeeshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OrderItem Repository - Data access layer for OrderItem entity
 * Provides CRUD operations and custom queries for order items
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
    // Find order items by order
    List<OrderItem> findByOrder(Order order);
    
    // Find order items by product
    List<OrderItem> findByProduct(Product product);
    
    // Find order items by order ID
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.orderId = :orderId")
    List<OrderItem> findByOrderOrderId(@Param("orderId") String orderId);
    
    // Find order items by product ID
    @Query("SELECT oi FROM OrderItem oi WHERE oi.product.productId = :productId")
    List<OrderItem> findByProductProductId(@Param("productId") String productId);
    
    // Calculate total quantity sold for a product
    @Query("SELECT COALESCE(SUM(oi.quantity), 0) FROM OrderItem oi WHERE oi.product = :product AND oi.order.status = 'COMPLETED'")
    Integer calculateTotalQuantitySold(@Param("product") Product product);
    
    // Calculate total revenue for a product
    @Query("SELECT COALESCE(SUM(oi.subtotal), 0) FROM OrderItem oi WHERE oi.product = :product AND oi.order.status = 'COMPLETED'")
    Double calculateProductRevenue(@Param("product") Product product);
    
    // Find best-selling products
    @Query("SELECT oi.product, SUM(oi.quantity) as totalSold FROM OrderItem oi WHERE oi.order.status = 'COMPLETED' GROUP BY oi.product ORDER BY totalSold DESC")
    List<Object[]> findBestSellingProducts();
    
    // Find most profitable products
    @Query("SELECT oi.product, SUM(oi.subtotal - (oi.quantity * oi.product.costPrice)) as profit FROM OrderItem oi WHERE oi.order.status = 'COMPLETED' GROUP BY oi.product ORDER BY profit DESC")
    List<Object[]> findMostProfitableProducts();
    
    // Find order items in date range
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.orderDate BETWEEN :startDate AND :endDate AND oi.order.status = 'COMPLETED'")
    List<OrderItem> findOrderItemsBetweenDates(@Param("startDate") LocalDateTime startDate, 
                                              @Param("endDate") LocalDateTime endDate);
    
    // Calculate total items sold in period
    @Query("SELECT COALESCE(SUM(oi.quantity), 0) FROM OrderItem oi WHERE oi.order.orderDate BETWEEN :startDate AND :endDate AND oi.order.status = 'COMPLETED'")
    Integer calculateTotalItemsSoldInPeriod(@Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
    
    // Find items by category in period
    @Query("SELECT oi FROM OrderItem oi WHERE oi.product.category = :category AND oi.order.orderDate BETWEEN :startDate AND :endDate AND oi.order.status = 'COMPLETED'")
    List<OrderItem> findItemsByCategoryInPeriod(@Param("category") String category,
                                               @Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);
    
    // Calculate average order value
    @Query("SELECT AVG(o.totalAmount) FROM Order o WHERE o.status = 'COMPLETED'")
    Double calculateAverageOrderValue();
    
    // Find popular product combinations
    @Query("SELECT oi1.product, oi2.product, COUNT(*) as frequency FROM OrderItem oi1 JOIN OrderItem oi2 ON oi1.order = oi2.order WHERE oi1.product.id < oi2.product.id AND oi1.order.status = 'COMPLETED' GROUP BY oi1.product, oi2.product ORDER BY frequency DESC")
    List<Object[]> findPopularProductCombinations();
}