package com.banhang.model.dto;

import jakarta.validation.constraints.*;

public class InventoryDTO {
    private Long id;
    
    @NotNull(message = "Product is required")
    private Long productId;
    private String productName;
    private String productCategory;
    
    @Min(value = 0, message = "Current stock cannot be negative")
    @NotNull(message = "Current stock is required")
    private Integer currentStock;
    
    @Min(value = 0, message = "Minimum stock cannot be negative")
    @NotNull(message = "Minimum stock is required")
    private Integer minStock;
    
    @Min(value = 0, message = "Maximum stock cannot be negative")
    @NotNull(message = "Maximum stock is required")
    private Integer maxStock;
    
    private String location;
    
    private String supplierId;
    
    @DecimalMin(value = "0.0", message = "Cost price cannot be negative")
    private Double costPrice = 0.0;
    
    // Computed fields
    private Boolean isLowStock;
    private Boolean isOverstock;
    private Integer availableCapacity;
    private Double stockValue;
    private String stockStatus;
    
    // Constructors
    public InventoryDTO() {
    }
    
    public InventoryDTO(Long productId, Integer currentStock, Integer minStock, Integer maxStock) {
        this.productId = productId;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
        calculateComputedFields();
    }
    
    // Business methods
    public void calculateComputedFields() {
        if (currentStock != null && minStock != null && maxStock != null) {
            this.isLowStock = currentStock <= minStock;
            this.isOverstock = currentStock >= maxStock;
            this.availableCapacity = maxStock - currentStock;
            
            if (costPrice != null) {
                this.stockValue = currentStock * costPrice;
            }
            
            if (isLowStock) {
                this.stockStatus = "LOW_STOCK";
            } else if (isOverstock) {
                this.stockStatus = "OVERSTOCK";
            } else {
                this.stockStatus = "NORMAL";
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
    
    public String getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    
    public Integer getCurrentStock() {
        return currentStock;
    }
    
    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
        calculateComputedFields();
    }
    
    public Integer getMinStock() {
        return minStock;
    }
    
    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
        calculateComputedFields();
    }
    
    public Integer getMaxStock() {
        return maxStock;
    }
    
    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
        calculateComputedFields();
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getSupplierId() {
        return supplierId;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    public Double getCostPrice() {
        return costPrice;
    }
    
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
        calculateComputedFields();
    }
    
    public Boolean getIsLowStock() {
        return isLowStock;
    }
    
    public void setIsLowStock(Boolean isLowStock) {
        this.isLowStock = isLowStock;
    }
    
    public Boolean getIsOverstock() {
        return isOverstock;
    }
    
    public void setIsOverstock(Boolean isOverstock) {
        this.isOverstock = isOverstock;
    }
    
    public Integer getAvailableCapacity() {
        return availableCapacity;
    }
    
    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
    
    public Double getStockValue() {
        return stockValue;
    }
    
    public void setStockValue(Double stockValue) {
        this.stockValue = stockValue;
    }
    
    public String getStockStatus() {
        return stockStatus;
    }
    
    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }
}