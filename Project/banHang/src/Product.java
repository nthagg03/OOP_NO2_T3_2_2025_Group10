package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private int stock;
    private String description;
    private String categoryId;

    // Danh sách lưu trữ tất cả sản phẩm
    private static List<Product> productList = new ArrayList<>();

    public Product(String productId, String productName, double price, int stock, String description, String categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.categoryId = categoryId;
    }

    public static void addProduct(Product product) {
        try {
            if (product == null) throw new IllegalArgumentException("Sản phẩm không được null.");
            for (Product p : productList) {
                if (p.getProductId().equals(product.getProductId())) {
                    throw new IllegalArgumentException("Mã sản phẩm đã tồn tại.");
                }
            }
            productList.add(product);
            System.out.println("Đã thêm sản phẩm: " + product.getProductName());
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }

    public static void displayAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        for (Product p : productList) {
            p.displayProductInfo();
            System.out.println("----------------------");
        }
    }

    public void displayProductInfo() {
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá: " + price);
        System.out.println("Tồn kho: " + stock);
        System.out.println("Mô tả: " + description);
        System.out.println("Mã danh mục: " + categoryId);
    }

    public static void updateProduct(String productId, String newName, double newPrice, int newStock, String newDescription, String newCategoryId) {
        try {
            Product p = findProductById(productId);
            if (p == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm có ID: " + productId);

            p.setProductName(newName);
            p.setPrice(newPrice);
            p.setStock(newStock);
            p.setDescription(newDescription);
            p.setCategoryId(newCategoryId);

            System.out.println("Đã cập nhật sản phẩm: " + productId);
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    public static void deleteProduct(String productId) {
        try {
            Product p = findProductById(productId);
            if (p == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm có ID: " + productId);

            productList.remove(p);
            System.out.println("Đã xóa sản phẩm: " + productId);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
    }

    public static Product findProductById(String productId) {
        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Giá sản phẩm không thể âm.");
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Số lượng tồn kho không thể âm.");
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
