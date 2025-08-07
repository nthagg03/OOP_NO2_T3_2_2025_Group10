package com.banhang.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;
    
    @Min(value = 0, message = "Current stock cannot be negative")
    @Column(name = "current_stock", nullable = false)
    private Integer currentStock = 0;
    
    @Min(value = 0, message = "Minimum stock cannot be negative")
    @Column(name = "min_stock", nullable = false)
    private Integer minStock = 0;
    
    @Min(value = 0, message = "Maximum stock cannot be negative")
    @Column(name = "max_stock", nullable = false)
    private Integer maxStock = 0;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "supplier_id")
    private String supplierId;
    
    @DecimalMin(value = "0.0", message = "Cost price cannot be negative")
    @Column(name = "cost_price")
    private Double costPrice = 0.0;
    
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;
    
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Constructors
    public Inventory() {
        this.createdDate = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Inventory(Product product, Integer currentStock, Integer minStock, Integer maxStock) {
        this();
        this.product = product;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Integer getCurrentStock() {
        return currentStock;
    }
    
    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Integer getMinStock() {
        return minStock;
    }
    
    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }
    
    public Integer getMaxStock() {
        return maxStock;
    }
    
    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
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
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
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
    public Boolean isLowStock() {
        return currentStock <= minStock;
    }
    
    public Boolean isOverstock() {
        return currentStock >= maxStock;
    }
    
    public Boolean canFulfillOrder(Integer quantity) {
        return currentStock >= quantity;
    }
    
    public void addStock(Integer quantity) {
        if (quantity > 0) {
            this.currentStock += quantity;
            this.lastUpdated = LocalDateTime.now();
        }
    }
    
    public void removeStock(Integer quantity) {
        if (quantity > 0 && this.currentStock >= quantity) {
            this.currentStock -= quantity;
            this.lastUpdated = LocalDateTime.now();
        }
    }
    
    public Integer getAvailableCapacity() {
        return maxStock - currentStock;
    }
    
    public Double getStockValue() {
        return currentStock * costPrice;
    }
    
    public String getStockStatus() {
        if (isLowStock()) {
            return "LOW_STOCK";
        } else if (isOverstock()) {
            return "OVERSTOCK";
        } else {
            return "NORMAL";
        }
    }
    
    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", productName='" + (product != null ? product.getProductName() : "N/A") + '\'' +
                ", currentStock=" + currentStock +
                ", minStock=" + minStock +
                ", maxStock=" + maxStock +
                ", location='" + location + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}