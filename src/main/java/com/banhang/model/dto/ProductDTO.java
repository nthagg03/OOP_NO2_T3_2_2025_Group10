package com.banhang.model.dto;

import jakarta.validation.constraints.*;

public class ProductDTO {
    private Long id;
    
    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    private String productName;
    
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
    
    @NotNull(message = "Import price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Import price must be greater than 0")
    private Double importPrice;
    
    @NotNull(message = "Sale price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Sale price must be greater than 0")
    private Double salePrice;
    
    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;
    
    @NotBlank(message = "Category ID is required")
    private String categoryId;
    
    private String barcode;
    
    @NotBlank(message = "Unit is required")
    private String unit;
    
    private Boolean isActive = true;
    
    // Computed fields
    private Double profitMargin;
    private Boolean lowStock;
    
    // Constructors
    public ProductDTO() {
    }
    
    public ProductDTO(String productName, String description, Double importPrice, 
                     Double salePrice, Integer stockQuantity, String categoryId, String unit) {
        this.productName = productName;
        this.description = description;
        this.importPrice = importPrice;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.unit = unit;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getImportPrice() {
        return importPrice;
    }
    
    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }
    
    public Double getSalePrice() {
        return salePrice;
    }
    
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
    
    public Integer getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public Double getProfitMargin() {
        return profitMargin;
    }
    
    public void setProfitMargin(Double profitMargin) {
        this.profitMargin = profitMargin;
    }
    
    public Boolean getLowStock() {
        return lowStock;
    }
    
    public void setLowStock(Boolean lowStock) {
        this.lowStock = lowStock;
    }
}