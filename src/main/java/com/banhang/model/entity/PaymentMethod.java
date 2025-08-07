package com.banhang.model.entity;

public enum PaymentMethod {
    CASH("Cash"),
    E_WALLET("E-Wallet"),
    CREDIT_CARD("Credit Card"),
    BANK_TRANSFER("Bank Transfer"),
    QR_CODE("QR Code");
    
    private final String displayName;
    
    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}