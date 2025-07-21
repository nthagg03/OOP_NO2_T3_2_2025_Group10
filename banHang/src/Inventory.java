import java.util.ArrayList;

class Product {
    int productID;
    String name;
    int stockQuantity;

    public Product(int productID, String name, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public void displayProductDetails() {
        System.out.println("Product ID: " + this.productID + ", Name: " + this.name + ", Stock Quantity: " + this.stockQuantity);
    }
}

class Inventory {

    // Thêm sản phẩm vào kho
    public void addProduct(ArrayList<Product> productList, int productID, String name, int stockQuantity) {
        Product newProduct = new Product(productID, name, stockQuantity);
        productList.add(newProduct);
    }

    // Hiển thị tất cả sản phẩm trong kho
    public void displayAllProducts(ArrayList<Product> productList) {
        for (Product product : productList) {
            product.displayProductDetails();
        }
    }

    // Cập nhật số lượng tồn kho sau khi bán
    public void updateStockAfterSale(ArrayList<Product> productList, int productID, int quantitySold) {
        for (Product product : productList) {
            if (product.productID == productID) {
                product.stockQuantity -= quantitySold;
            }
        }
    }

    // Xóa sản phẩm khỏi kho
    public void deleteProduct(ArrayList<Product> productList, int productID) {
        productList.removeIf(product -> product.productID == productID);
    }
}
