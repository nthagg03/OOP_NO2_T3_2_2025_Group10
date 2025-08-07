package com.phenikaa.coffeeshop.repository;

import com.phenikaa.coffeeshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Customer Repository - Data access layer for Customer entity
 * Provides CRUD operations and custom queries for customers
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Find by custom customer ID
    Optional<Customer> findByCustomerId(String customerId);
    
    // Find by email
    Optional<Customer> findByEmail(String email);
    
    // Find by phone
    Optional<Customer> findByPhone(String phone);
    
    // Find active customers
    List<Customer> findByActiveTrue();
    
    // Search customers by name (case-insensitive)
    List<Customer> findByNameContainingIgnoreCaseAndActiveTrue(String name);
    
    // Find customers by city/address
    List<Customer> findByAddressContainingIgnoreCaseAndActiveTrue(String address);
    
    // Find customers created in date range
    @Query("SELECT c FROM Customer c WHERE c.createdAt BETWEEN :startDate AND :endDate AND c.active = true")
    List<Customer> findCustomersCreatedBetween(@Param("startDate") LocalDateTime startDate, 
                                              @Param("endDate") LocalDateTime endDate);
    
    // Find top customers by order count
    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE c.active = true GROUP BY c ORDER BY COUNT(o) DESC")
    List<Customer> findTopCustomersByOrderCount();
    
    // Find customers with orders
    @Query("SELECT DISTINCT c FROM Customer c JOIN c.orders o WHERE c.active = true")
    List<Customer> findCustomersWithOrders();
    
    // Find customers without orders
    @Query("SELECT c FROM Customer c WHERE c.active = true AND c.id NOT IN (SELECT DISTINCT o.customer.id FROM Order o)")
    List<Customer> findCustomersWithoutOrders();
    
    // Count active customers
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.active = true")
    Long countActiveCustomers();
    
    // Search customers by email domain
    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain% AND c.active = true")
    List<Customer> findByEmailDomain(@Param("domain") String domain);
}