package com.banhang.repository;

import com.banhang.model.entity.Order;
import com.banhang.model.entity.OrderStatus;
import com.banhang.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // Find by order code
    Optional<Order> findByOrderCode(String orderCode);
    
    // Find by customer
    List<Order> findByCustomer(Customer customer);
    
    // Find by customer with pagination
    Page<Order> findByCustomer(Customer customer, Pageable pageable);
    
    // Find by status
    List<Order> findByStatus(OrderStatus status);
    
    // Find by status with pagination
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
    
    // Find by customer and status
    List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);
    
    // Find orders by date range
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate ORDER BY o.orderDate DESC")
    List<Order> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                               @Param("endDate") LocalDateTime endDate);
    
    // Find orders by date range with pagination
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate ORDER BY o.orderDate DESC")
    Page<Order> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                               @Param("endDate") LocalDateTime endDate, 
                               Pageable pageable);
    
    // Search orders by multiple criteria
    @Query("SELECT o FROM Order o WHERE " +
           "(:orderCode IS NULL OR LOWER(o.orderCode) LIKE LOWER(CONCAT('%', :orderCode, '%'))) AND " +
           "(:customerId IS NULL OR o.customer.id = :customerId) AND " +
           "(:status IS NULL OR o.status = :status) AND " +
           "(:startDate IS NULL OR o.orderDate >= :startDate) AND " +
           "(:endDate IS NULL OR o.orderDate <= :endDate) " +
           "ORDER BY o.orderDate DESC")
    Page<Order> searchOrders(@Param("orderCode") String orderCode,
                            @Param("customerId") Long customerId,
                            @Param("status") OrderStatus status,
                            @Param("startDate") LocalDateTime startDate,
                            @Param("endDate") LocalDateTime endDate,
                            Pageable pageable);
    
    // Find recent orders
    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    Page<Order> findRecentOrders(Pageable pageable);
    
    // Get total sales by date range
    @Query("SELECT SUM(o.finalAmount) FROM Order o WHERE " +
           "o.status = 'COMPLETED' AND " +
           "o.orderDate BETWEEN :startDate AND :endDate")
    Double getTotalSalesByDateRange(@Param("startDate") LocalDateTime startDate, 
                                   @Param("endDate") LocalDateTime endDate);
    
    // Get order count by status
    long countByStatus(OrderStatus status);
    
    // Get order count by customer
    long countByCustomer(Customer customer);
    
    // Get top customers by order value
    @Query("SELECT o.customer, SUM(o.finalAmount) as totalValue FROM Order o " +
           "WHERE o.status = 'COMPLETED' " +
           "GROUP BY o.customer " +
           "ORDER BY totalValue DESC")
    Page<Object[]> findTopCustomersByValue(Pageable pageable);
    
    // Get sales statistics by month
    @Query("SELECT YEAR(o.orderDate), MONTH(o.orderDate), COUNT(o), SUM(o.finalAmount) " +
           "FROM Order o WHERE o.status = 'COMPLETED' " +
           "GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) " +
           "ORDER BY YEAR(o.orderDate) DESC, MONTH(o.orderDate) DESC")
    List<Object[]> getMonthlySalesStats();
    
    // Check if order code exists
    boolean existsByOrderCode(String orderCode);
}