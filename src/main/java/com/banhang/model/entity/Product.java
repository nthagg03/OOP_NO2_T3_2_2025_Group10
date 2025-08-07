package com.banhang.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    @Column(name = "product_name", nullable = false)
    private String productName;
    
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description")
    private String description;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Import price must be greater than 0")
    @Column(name = "import_price", nullable = false)
    private Double importPrice;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Sale price must be greater than 0")
    @Column(name = "sale_price", nullable = false)
    private Double salePrice;
    
    @Min(value = 0, message = "Stock quantity cannot be negative")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;
    
    @NotBlank(message = "Category ID is required")
    @Column(name = "category_id", nullable = false)
    private String categoryId;
    
    @Column(name = "barcode", unique = true)
    private String barcode;
    
    @NotBlank(message = "Unit is required")
    @Column(name = "unit", nullable = false)
    private String unit;
    
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    // Constructors
    public Product() {
        this.createdDate = LocalDateTime.now();
        this.isActive = true;
    }
    
    public Product(String productName, String description, Double importPrice, Double salePrice, 
                   Integer stockQuantity, String categoryId, String unit) {
        this();
        this.productName = productName;
        this.description = description;
        this.importPrice = importPrice;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.unit = unit;
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
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    // Business methods
    public Double getProfitMargin() {
        if (importPrice != null && salePrice != null && importPrice > 0) {
            return ((salePrice - importPrice) / importPrice) * 100;
        }
        return 0.0;
    }
    
    public Boolean isLowStock(Integer threshold) {
        return stockQuantity != null && stockQuantity <= threshold;
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", importPrice=" + importPrice +
                ", salePrice=" + salePrice +
                ", stockQuantity=" + stockQuantity +
                ", categoryId='" + categoryId + '\'' +
                ", unit='" + unit + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}