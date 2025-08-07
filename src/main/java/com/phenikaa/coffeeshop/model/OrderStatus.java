package com.phenikaa.coffeeshop.model;

/**
 * Order Status Enumeration
 * Defines the possible states of an order
 */
public enum OrderStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"), 
    PREPARING("Preparing"),
    READY("Ready"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");
    
    private final String displayName;
    
    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}