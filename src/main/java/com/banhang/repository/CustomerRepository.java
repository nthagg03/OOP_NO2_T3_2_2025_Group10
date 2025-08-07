package com.banhang.repository;

import com.banhang.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Find by customer code
    Optional<Customer> findByCustomerCode(String customerCode);
    
    // Find by customer name
    List<Customer> findByCustomerNameContainingIgnoreCase(String customerName);
    
    // Find by phone number
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    
    // Find by email
    Optional<Customer> findByEmail(String email);
    
    // Find active customers
    List<Customer> findByIsActiveTrue();
    
    // Find active customers with pagination
    Page<Customer> findByIsActiveTrue(Pageable pageable);
    
    // Find by name and active status
    Page<Customer> findByCustomerNameContainingIgnoreCaseAndIsActiveTrue(String customerName, Pageable pageable);
    
    // Search customers by multiple criteria
    @Query("SELECT c FROM Customer c WHERE " +
           "(:name IS NULL OR LOWER(c.customerName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:phone IS NULL OR c.phoneNumber LIKE CONCAT('%', :phone, '%')) AND " +
           "c.isActive = true")
    Page<Customer> searchCustomers(@Param("name") String name,
                                  @Param("email") String email,
                                  @Param("phone") String phone,
                                  Pageable pageable);
    
    // Find top customers by order count
    @Query("SELECT c FROM Customer c LEFT JOIN c.orders o " +
           "WHERE c.isActive = true " +
           "GROUP BY c " +
           "ORDER BY COUNT(o) DESC")
    Page<Customer> findTopCustomersByOrderCount(Pageable pageable);
    
    // Find customers by total spent range
    @Query("SELECT c FROM Customer c LEFT JOIN c.orders o " +
           "WHERE c.isActive = true AND o.status = 'COMPLETED' " +
           "GROUP BY c " +
           "HAVING SUM(o.finalAmount) BETWEEN :minAmount AND :maxAmount")
    List<Customer> findByTotalSpentRange(@Param("minAmount") Double minAmount, 
                                        @Param("maxAmount") Double maxAmount);
    
    // Count active customers
    long countByIsActiveTrue();
    
    // Check if customer code exists
    boolean existsByCustomerCode(String customerCode);
    
    // Check if email exists
    boolean existsByEmail(String email);
    
    // Check if phone exists
    boolean existsByPhoneNumber(String phoneNumber);
}