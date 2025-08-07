package com.phenikaa.coffeeshop.exception;

/**
 * Exception thrown when there is insufficient stock for an operation
 */
public class InsufficientStockException extends RuntimeException {
    
    public InsufficientStockException() {
        super();
    }
    
    public InsufficientStockException(String message) {
        super(message);
    }
    
    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InsufficientStockException(Throwable cause) {
        super(cause);
    }
}