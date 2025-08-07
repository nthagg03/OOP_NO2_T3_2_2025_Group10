package com.banhang.model.dto;

import jakarta.validation.constraints.*;

public class OrderItemDTO {
    private Long id;
    
    @NotNull(message = "Order ID is required")
    private Long orderId;
    
    @NotNull(message = "Product is required")
    private Long productId;
    private String productName;
    private String productUnit;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
    @NotNull(message = "Unit price is required")
    private Double unitPrice;
    
    @DecimalMin(value = "0.0", message = "Discount amount cannot be negative")
    private Double discountAmount = 0.0;
    
    private Double lineTotal = 0.0;
    
    private String notes;
    
    // Computed fields
    private Double subTotal;
    private Double discountPercentage;
    
    // Constructors
    public OrderItemDTO() {
    }
    
    public OrderItemDTO(Long orderId, Long productId, Integer quantity, Double unitPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        calculateTotals();
    }
    
    // Business method to calculate totals
    public void calculateTotals() {
        if (quantity != null && unitPrice != null) {
            this.subTotal = quantity * unitPrice;
            this.lineTotal = subTotal - (discountAmount != null ? discountAmount : 0.0);
            if (subTotal > 0 && discountAmount != null) {
                this.discountPercentage = (discountAmount / subTotal) * 100;
            }
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductUnit() {
        return productUnit;
    }
    
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateTotals();
    }
    
    public Double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        calculateTotals();
    }
    
    public Double getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
        calculateTotals();
    }
    
    public Double getLineTotal() {
        return lineTotal;
    }
    
    public void setLineTotal(Double lineTotal) {
        this.lineTotal = lineTotal;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Double getSubTotal() {
        return subTotal;
    }
    
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
    public Double getDiscountPercentage() {
        return discountPercentage;
    }
    
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}