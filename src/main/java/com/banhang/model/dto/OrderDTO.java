package com.banhang.model.dto;

import com.banhang.model.entity.OrderStatus;
import com.banhang.model.entity.PaymentMethod;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;
    
    @NotBlank(message = "Order code is required")
    @Size(max = 50, message = "Order code must not exceed 50 characters")
    private String orderCode;
    
    @NotNull(message = "Customer is required")
    private Long customerId;
    private String customerName;
    
    private String staffId;
    
    private LocalDateTime orderDate;
    
    @DecimalMin(value = "0.0", message = "Total amount cannot be negative")
    private Double totalAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Tax amount cannot be negative")
    private Double taxAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Discount amount cannot be negative")
    private Double discountAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Final amount cannot be negative")
    private Double finalAmount = 0.0;
    
    private OrderStatus status = OrderStatus.PENDING;
    
    private PaymentMethod paymentMethod;
    
    private String notes;
    
    // Computed fields
    private Integer totalItems;
    private Boolean canBeModified;
    private Boolean canBeCancelled;
    
    // Order items for display
    private List<OrderItemDTO> orderItems;
    
    // Constructors
    public OrderDTO() {
        this.orderDate = LocalDateTime.now();
    }
    
    public OrderDTO(String orderCode, Long customerId, String staffId) {
        this();
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.staffId = staffId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getOrderCode() {
        return orderCode;
    }
    
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getStaffId() {
        return staffId;
    }
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Integer getTotalItems() {
        return totalItems;
    }
    
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
    
    public Boolean getCanBeModified() {
        return canBeModified;
    }
    
    public void setCanBeModified(Boolean canBeModified) {
        this.canBeModified = canBeModified;
    }
    
    public Boolean getCanBeCancelled() {
        return canBeCancelled;
    }
    
    public void setCanBeCancelled(Boolean canBeCancelled) {
        this.canBeCancelled = canBeCancelled;
    }
    
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}