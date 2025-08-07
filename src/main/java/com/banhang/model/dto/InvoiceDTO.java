package com.banhang.model.dto;

import com.banhang.model.entity.PaymentMethod;
import com.banhang.model.entity.PaymentStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class InvoiceDTO {
    private Long id;
    
    @NotBlank(message = "Invoice number is required")
    @Size(max = 50, message = "Invoice number must not exceed 50 characters")
    private String invoiceNumber;
    
    @NotNull(message = "Order is required")
    private Long orderId;
    private String orderCode;
    private String customerName;
    
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    
    @DecimalMin(value = "0.0", message = "Total amount cannot be negative")
    private Double totalAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Tax amount cannot be negative")
    private Double taxAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Discount amount cannot be negative")
    private Double discountAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Final amount cannot be negative")
    private Double finalAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Paid amount cannot be negative")
    private Double paidAmount = 0.0;
    
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    
    private PaymentMethod paymentMethod;
    
    private LocalDateTime paymentDate;
    
    private String notes;
    
    // Computed fields
    private Double outstandingAmount;
    private Boolean isFullyPaid;
    private Boolean isOverdue;
    
    // Constructors
    public InvoiceDTO() {
        this.invoiceDate = LocalDateTime.now();
    }
    
    public InvoiceDTO(String invoiceNumber, Long orderId) {
        this();
        this.invoiceNumber = invoiceNumber;
        this.orderId = orderId;
    }
    
    // Business methods
    public void calculateOutstandingAmount() {
        this.outstandingAmount = (finalAmount != null ? finalAmount : 0.0) - (paidAmount != null ? paidAmount : 0.0);
        this.isFullyPaid = outstandingAmount <= 0.0;
        this.isOverdue = dueDate != null && LocalDateTime.now().isAfter(dueDate) && !isFullyPaid;
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
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderCode() {
        return orderCode;
    }
    
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        calculateOutstandingAmount();
    }
    
    public Double getPaidAmount() {
        return paidAmount;
    }
    
    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
        calculateOutstandingAmount();
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
    
    public Double getOutstandingAmount() {
        return outstandingAmount;
    }
    
    public void setOutstandingAmount(Double outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
    
    public Boolean getIsFullyPaid() {
        return isFullyPaid;
    }
    
    public void setIsFullyPaid(Boolean isFullyPaid) {
        this.isFullyPaid = isFullyPaid;
    }
    
    public Boolean getIsOverdue() {
        return isOverdue;
    }
    
    public void setIsOverdue(Boolean isOverdue) {
        this.isOverdue = isOverdue;
    }
}