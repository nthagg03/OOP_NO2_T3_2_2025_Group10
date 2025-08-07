package com.banhang.repository;

import com.banhang.model.entity.Invoice;
import com.banhang.model.entity.PaymentStatus;
import com.banhang.model.entity.Order;
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
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    // Find by invoice number
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    
    // Find by order
    Optional<Invoice> findByOrder(Order order);
    
    // Find by payment status
    List<Invoice> findByPaymentStatus(PaymentStatus paymentStatus);
    
    // Find by payment status with pagination
    Page<Invoice> findByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);
    
    // Find invoices by date range
    @Query("SELECT i FROM Invoice i WHERE i.invoiceDate BETWEEN :startDate AND :endDate ORDER BY i.invoiceDate DESC")
    List<Invoice> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                 @Param("endDate") LocalDateTime endDate);
    
    // Find invoices by date range with pagination
    @Query("SELECT i FROM Invoice i WHERE i.invoiceDate BETWEEN :startDate AND :endDate ORDER BY i.invoiceDate DESC")
    Page<Invoice> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                 @Param("endDate") LocalDateTime endDate, 
                                 Pageable pageable);
    
    // Find overdue invoices
    @Query("SELECT i FROM Invoice i WHERE i.dueDate < :currentDate AND i.paymentStatus != 'PAID'")
    List<Invoice> findOverdueInvoices(@Param("currentDate") LocalDateTime currentDate);
    
    // Find invoices due soon
    @Query("SELECT i FROM Invoice i WHERE i.dueDate BETWEEN :startDate AND :endDate AND i.paymentStatus != 'PAID'")
    List<Invoice> findInvoicesDueSoon(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);
    
    // Search invoices by multiple criteria
    @Query("SELECT i FROM Invoice i WHERE " +
           "(:invoiceNumber IS NULL OR LOWER(i.invoiceNumber) LIKE LOWER(CONCAT('%', :invoiceNumber, '%'))) AND " +
           "(:customerId IS NULL OR i.order.customer.id = :customerId) AND " +
           "(:paymentStatus IS NULL OR i.paymentStatus = :paymentStatus) AND " +
           "(:startDate IS NULL OR i.invoiceDate >= :startDate) AND " +
           "(:endDate IS NULL OR i.invoiceDate <= :endDate) " +
           "ORDER BY i.invoiceDate DESC")
    Page<Invoice> searchInvoices(@Param("invoiceNumber") String invoiceNumber,
                                @Param("customerId") Long customerId,
                                @Param("paymentStatus") PaymentStatus paymentStatus,
                                @Param("startDate") LocalDateTime startDate,
                                @Param("endDate") LocalDateTime endDate,
                                Pageable pageable);
    
    // Get total outstanding amount
    @Query("SELECT SUM(i.finalAmount - i.paidAmount) FROM Invoice i WHERE i.paymentStatus != 'PAID'")
    Double getTotalOutstandingAmount();
    
    // Get total paid amount by date range
    @Query("SELECT SUM(i.paidAmount) FROM Invoice i WHERE " +
           "i.paymentDate BETWEEN :startDate AND :endDate")
    Double getTotalPaidAmountByDateRange(@Param("startDate") LocalDateTime startDate, 
                                        @Param("endDate") LocalDateTime endDate);
    
    // Count invoices by payment status
    long countByPaymentStatus(PaymentStatus paymentStatus);
    
    // Count overdue invoices
    @Query("SELECT COUNT(i) FROM Invoice i WHERE i.dueDate < :currentDate AND i.paymentStatus != 'PAID'")
    long countOverdueInvoices(@Param("currentDate") LocalDateTime currentDate);
    
    // Get payment statistics by month
    @Query("SELECT YEAR(i.paymentDate), MONTH(i.paymentDate), COUNT(i), SUM(i.paidAmount) " +
           "FROM Invoice i WHERE i.paymentDate IS NOT NULL " +
           "GROUP BY YEAR(i.paymentDate), MONTH(i.paymentDate) " +
           "ORDER BY YEAR(i.paymentDate) DESC, MONTH(i.paymentDate) DESC")
    List<Object[]> getMonthlyPaymentStats();
    
    // Check if invoice number exists
    boolean existsByInvoiceNumber(String invoiceNumber);
}