package com.banhang.service.interfaces;

import com.banhang.model.dto.InvoiceDTO;
import com.banhang.model.entity.Invoice;
import com.banhang.model.entity.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    
    // CRUD Operations
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO createInvoiceFromOrder(Long orderId);
    Optional<InvoiceDTO> getInvoiceById(Long id);
    InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);
    void deleteInvoice(Long id);
    
    // List Operations
    List<InvoiceDTO> getAllInvoices();
    Page<InvoiceDTO> getAllInvoicesPageable(Pageable pageable);
    
    // Search Operations
    Optional<InvoiceDTO> getInvoiceByNumber(String invoiceNumber);
    Optional<InvoiceDTO> getInvoiceByOrder(Long orderId);
    List<InvoiceDTO> getInvoicesByPaymentStatus(PaymentStatus paymentStatus);
    Page<InvoiceDTO> getInvoicesByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);
    
    // Advanced Search
    Page<InvoiceDTO> searchInvoices(String invoiceNumber, Long customerId, PaymentStatus paymentStatus,
                                   LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    List<InvoiceDTO> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    Page<InvoiceDTO> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    
    // Payment Operations
    InvoiceDTO processPayment(Long invoiceId, Double amount, String paymentMethod);
    InvoiceDTO markAsPaid(Long invoiceId, Double amount, String paymentMethod);
    InvoiceDTO markAsPartiallyPaid(Long invoiceId, Double amount, String paymentMethod);
    InvoiceDTO refundPayment(Long invoiceId, Double amount);
    
    // Business Operations
    List<InvoiceDTO> getOverdueInvoices();
    List<InvoiceDTO> getInvoicesDueSoon(int days);
    InvoiceDTO updateDueDate(Long invoiceId, LocalDateTime dueDate);
    void sendOverdueReminders();
    
    // Statistics
    long getTotalInvoiceCount();
    long getInvoiceCountByPaymentStatus(PaymentStatus paymentStatus);
    long getOverdueInvoiceCount();
    Double getTotalOutstandingAmount();
    Double getTotalPaidAmountByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Object[]> getMonthlyPaymentStats();
    
    // Validation
    boolean existsByInvoiceNumber(String invoiceNumber);
    String generateInvoiceNumber();
    
    // Utility Methods
    InvoiceDTO convertToDTO(Invoice invoice);
    Invoice convertToEntity(InvoiceDTO invoiceDTO);
    byte[] generateInvoicePDF(Long invoiceId);
    void sendInvoiceEmail(Long invoiceId, String recipientEmail);
}