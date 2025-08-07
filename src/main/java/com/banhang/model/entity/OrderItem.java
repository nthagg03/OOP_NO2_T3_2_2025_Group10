package com.banhang.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;
    
    @DecimalMin(value = "0.0", message = "Discount amount cannot be negative")
    @Column(name = "discount_amount", nullable = false)
    private Double discountAmount = 0.0;
    
    @DecimalMin(value = "0.0", message = "Line total cannot be negative")
    @Column(name = "line_total", nullable = false)
    private Double lineTotal = 0.0;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    
    // Constructors
    public OrderItem() {
        this.createdDate = LocalDateTime.now();
    }
    
    public OrderItem(Order order, Product product, Integer quantity, Double unitPrice) {
        this();
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        calculateLineTotal();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateLineTotal();
    }
    
    public Double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        calculateLineTotal();
    }
    
    public Double getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
        calculateLineTotal();
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
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    // Business methods
    private void calculateLineTotal() {
        if (quantity != null && unitPrice != null && discountAmount != null) {
            this.lineTotal = (quantity * unitPrice) - discountAmount;
        }
    }
    
    public Double getSubTotal() {
        return quantity != null && unitPrice != null ? quantity * unitPrice : 0.0;
    }
    
    public Double getDiscountPercentage() {
        Double subTotal = getSubTotal();
        if (subTotal > 0 && discountAmount != null) {
            return (discountAmount / subTotal) * 100;
        }
        return 0.0;
    }
    
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", productName='" + (product != null ? product.getProductName() : "N/A") + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineTotal=" + lineTotal +
                '}';
    }
}