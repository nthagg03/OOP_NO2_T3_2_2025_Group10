package com.phenikaa.coffeeshop.repository;

import com.phenikaa.coffeeshop.model.Order;
import com.phenikaa.coffeeshop.model.OrderStatus;
import com.phenikaa.coffeeshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Order Repository - Data access layer for Order entity
 * Provides CRUD operations and custom queries for orders
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // Find by custom order ID
    Optional<Order> findByOrderId(String orderId);
    
    // Find orders by status
    List<Order> findByStatus(OrderStatus status);
    
    // Find orders by customer
    List<Order> findByCustomerOrderByOrderDateDesc(Customer customer);
    
    // Find orders by customer ID
    @Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId ORDER BY o.orderDate DESC")
    List<Order> findByCustomerCustomerId(@Param("customerId") String customerId);
    
    // Find orders in date range
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate ORDER BY o.orderDate DESC")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate, 
                                      @Param("endDate") LocalDateTime endDate);
    
    // Find orders by status and date range
    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.orderDate BETWEEN :startDate AND :endDate ORDER BY o.orderDate DESC")
    List<Order> findByStatusAndDateRange(@Param("status") OrderStatus status,
                                        @Param("startDate") LocalDateTime startDate,
                                        @Param("endDate") LocalDateTime endDate);
    
    // Find recent orders (last N days)
    @Query("SELECT o FROM Order o WHERE o.orderDate >= :date ORDER BY o.orderDate DESC")
    List<Order> findRecentOrders(@Param("date") LocalDateTime date);
    
    // Calculate total revenue for date range
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = 'COMPLETED' AND o.orderDate BETWEEN :startDate AND :endDate")
    Double calculateRevenueForPeriod(@Param("startDate") LocalDateTime startDate, 
                                    @Param("endDate") LocalDateTime endDate);
    
    // Calculate total revenue for today
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = 'COMPLETED' AND DATE(o.orderDate) = DATE(:date)")
    Double calculateDailyRevenue(@Param("date") LocalDateTime date);
    
    // Count orders by status
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") OrderStatus status);
    
    // Find top customers by total spent
    @Query("SELECT o.customer, SUM(o.totalAmount) as total FROM Order o WHERE o.status = 'COMPLETED' GROUP BY o.customer ORDER BY total DESC")
    List<Object[]> findTopCustomersBySpending();
    
    // Find orders by payment method
    List<Order> findByPaymentMethodAndStatusOrderByOrderDateDesc(String paymentMethod, OrderStatus status);
    
    // Find large orders (above threshold)
    @Query("SELECT o FROM Order o WHERE o.totalAmount >= :threshold ORDER BY o.totalAmount DESC")
    List<Order> findLargeOrders(@Param("threshold") Double threshold);
    
    // Find pending orders older than specified time
    @Query("SELECT o FROM Order o WHERE o.status = 'PENDING' AND o.orderDate < :datetime")
    List<Order> findStaleOrders(@Param("datetime") LocalDateTime datetime);
    
    // Get monthly revenue report
    @Query("SELECT YEAR(o.orderDate), MONTH(o.orderDate), SUM(o.totalAmount) FROM Order o WHERE o.status = 'COMPLETED' GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)")
    List<Object[]> getMonthlyRevenueReport();
}