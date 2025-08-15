package com.example.servingwebcontent.Model;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private int stock;
    private String description;
    private String category;

    public Product() {}

    public Product(String productId, String productName, double price, int stock, String description, String category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
