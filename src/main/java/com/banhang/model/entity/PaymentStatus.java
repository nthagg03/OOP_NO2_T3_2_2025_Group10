package com.banhang.model.entity;

public enum PaymentStatus {
    PENDING("Pending"),
    PARTIAL("Partial Payment"),
    PAID("Fully Paid"),
    OVERDUE("Overdue"),
    REFUNDED("Refunded");
    
    private final String displayName;
    
    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}