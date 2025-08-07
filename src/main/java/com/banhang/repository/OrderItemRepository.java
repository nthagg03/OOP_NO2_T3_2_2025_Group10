package com.banhang.repository;

import com.banhang.model.entity.OrderItem;
import com.banhang.model.entity.Order;
import com.banhang.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
    // Find by order
    List<OrderItem> findByOrder(Order order);
    
    // Find by product
    List<OrderItem> findByProduct(Product product);
    
    // Find by order and product
    List<OrderItem> findByOrderAndProduct(Order order, Product product);
    
    // Get top selling products
    @Query("SELECT oi.product, SUM(oi.quantity) as totalQuantity " +
           "FROM OrderItem oi JOIN oi.order o " +
           "WHERE o.status = 'COMPLETED' " +
           "GROUP BY oi.product " +
           "ORDER BY totalQuantity DESC")
    Page<Object[]> findTopSellingProducts(Pageable pageable);
    
    // Get top selling products by date range
    @Query("SELECT oi.product, SUM(oi.quantity) as totalQuantity " +
           "FROM OrderItem oi JOIN oi.order o " +
           "WHERE o.status = 'COMPLETED' AND " +
           "o.orderDate BETWEEN :startDate AND :endDate " +
           "GROUP BY oi.product " +
           "ORDER BY totalQuantity DESC")
    Page<Object[]> findTopSellingProductsByDateRange(@Param("startDate") LocalDateTime startDate,
                                                      @Param("endDate") LocalDateTime endDate,
                                                      Pageable pageable);
    
    // Get revenue by product
    @Query("SELECT oi.product, SUM(oi.lineTotal) as totalRevenue " +
           "FROM OrderItem oi JOIN oi.order o " +
           "WHERE o.status = 'COMPLETED' " +
           "GROUP BY oi.product " +
           "ORDER BY totalRevenue DESC")
    Page<Object[]> findProductsByRevenue(Pageable pageable);
    
    // Get sales statistics by product
    @Query("SELECT oi.product, COUNT(oi), SUM(oi.quantity), SUM(oi.lineTotal), AVG(oi.unitPrice) " +
           "FROM OrderItem oi JOIN oi.order o " +
           "WHERE o.status = 'COMPLETED' " +
           "GROUP BY oi.product")
    List<Object[]> getProductSalesStatistics();
    
    // Get total quantity sold by product
    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi JOIN oi.order o " +
           "WHERE oi.product = :product AND o.status = 'COMPLETED'")
    Long getTotalQuantitySoldByProduct(@Param("product") Product product);
    
    // Get total revenue by product
    @Query("SELECT SUM(oi.lineTotal) FROM OrderItem oi JOIN oi.order o " +
           "WHERE oi.product = :product AND o.status = 'COMPLETED'")
    Double getTotalRevenueByProduct(@Param("product") Product product);
    
    // Get sales data by date range
    @Query("SELECT oi FROM OrderItem oi JOIN oi.order o " +
           "WHERE o.status = 'COMPLETED' AND " +
           "o.orderDate BETWEEN :startDate AND :endDate " +
           "ORDER BY o.orderDate DESC")
    List<OrderItem> findSalesByDateRange(@Param("startDate") LocalDateTime startDate,
                                        @Param("endDate") LocalDateTime endDate);
}