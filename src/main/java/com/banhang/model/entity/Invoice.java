package com.banhang.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Invoice number is required")
    @Size(max = 50, message = "Invoice number must not exceed 50 characters")
    @Column(name = "invoice_number", unique = true, nullable = false)
    private String invoiceNumber;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;
    
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    @DecimalMin(value = "0.0", message = "Total amount cannot be negative")
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Tax amount cannot be negative")
    @Column(name = "tax_amount", nullable = false)
    private Double taxAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Discount amount cannot be negative")
    @Column(name = "discount_amount", nullable = false)
    private Double discountAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Final amount cannot be negative")
    @Column(name = "final_amount", nullable = false)
    private Double finalAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Paid amount cannot be negative")
    @Column(name = "paid_amount", nullable = false)
    private Double paidAmount = 0.0;
    
    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Constructors
    public Invoice() {
        this.createdDate = LocalDateTime.now();
        this.invoiceDate = LocalDateTime.now();
        this.paymentStatus = PaymentStatus.PENDING;
    }
    
    public Invoice(String invoiceNumber, Order order) {
        this();
        this.invoiceNumber = invoiceNumber;
        this.order = order;
        copyAmountsFromOrder();
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }
    
    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Double getTaxAmount() {
        return taxAmount;
    }
    
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }
    
    public Double getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public Double getFinalAmount() {
        return finalAmount;
    }
    
    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }
    
    public Double getPaidAmount() {
        return paidAmount;
    }
    
    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }
    
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    // Business methods
    private void copyAmountsFromOrder() {
        if (order != null) {
            this.totalAmount = order.getTotalAmount();
            this.taxAmount = order.getTaxAmount();
            this.discountAmount = order.getDiscountAmount();
            this.finalAmount = order.getFinalAmount();
            this.paymentMethod = order.getPaymentMethod();
        }
    }
    
    public Double getOutstandingAmount() {
        return finalAmount - paidAmount;
    }
    
    public Boolean isFullyPaid() {
        return paidAmount >= finalAmount;
    }
    
    public Boolean isOverdue() {
        return dueDate != null && LocalDateTime.now().isAfter(dueDate) && !isFullyPaid();
    }
    
    public void markAsPaid(Double amount, PaymentMethod method) {
        this.paidAmount += amount;
        this.paymentMethod = method;
        this.paymentDate = LocalDateTime.now();
        
        if (isFullyPaid()) {
            this.paymentStatus = PaymentStatus.PAID;
        } else {
            this.paymentStatus = PaymentStatus.PARTIAL;
        }
    }
    
    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", orderCode='" + (order != null ? order.getOrderCode() : "N/A") + '\'' +
                ", finalAmount=" + finalAmount +
                ", paidAmount=" + paidAmount +
                ", paymentStatus=" + paymentStatus +
                ", invoiceDate=" + invoiceDate +
                '}';
    }
}