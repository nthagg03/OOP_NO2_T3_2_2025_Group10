import java.util.ArrayList;
import java.util.Scanner;

public class testProduct {

    public static void test() {
        // Tạo vài sản phẩm mẫu (sellerId để tạm 1, 2, 3)
        Product p1 = new Product("P001", "Capuchino", 120000, 10, "coffee mang hương vị phong phú");
        Product p2 = new Product("P002", "Đen đá không đường", 20000, 15, "coffee mang hương vị đậm đà");
        Product p3 = new Product("P003", "Trà vải lài", 50000, 30, "Trà vải lài thơm ngon");

        ProductList productList = new ProductList();
        productList.addProduct(p1);
        productList.addProduct(p2);
        productList.addProduct(p3);

        Scanner sc = new Scanner(System.in);

        System.out.println("== Danh sach san pham ban dau ==");
        productList.printProductList();

        // === CẬP NHẬT TÊN SẢN PHẨM ===
        System.out.println("\n== Cap nhap ten san pham ==");
        System.out.print("Nhap Product ID can sua: ");
        String productId = sc.nextLine();
        System.out.print("Nhap ten san pham moi: ");
        String newName = sc.nextLine();
        productList.getEditProduct(newName, productId);
        productList.printProductList();

        // === XÓA SẢN PHẨM ===
        System.out.println("\n== Xoa san pham ==");
        System.out.print("Nhap Product ID can xoa: ");
        String delProductId = sc.nextLine();
        productList.getDeleteProduct(delProductId);
        productList.printProductList();

        // === THÊM SẢN PHẨM MỚI ===
        System.out.println("\n== Them san pham moi ==");
        System.out.print("Nhap ma san pham: ");
        String newId = sc.nextLine();

        System.out.print("Nhap ten san pham: ");
        String name = sc.nextLine();

        System.out.print("Nhap gia san pham: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Nhap so luong ton kho: ");
        int stock = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap mo ta san pham: ");
        String desc = sc.nextLine();

        Product newProduct = new Product(newId, name, price, stock, desc);
        productList.addProduct(newProduct);

        System.out.println("\n== Danh sach sau khi them ==");
        productList.printProductList();
        
    }

    public static void testProductInput() {
        System.out.println("\n== Nhập thông tin sản phẩm ==");
        ArrayList<Product> category = new ArrayList<Product>();
        Product p1 = new Product();
        p1.productInput();
        category.add(p1);

        ProductList productList = new ProductList();
        productList.addProduct(p1);
        System.out.println("\n== Danh sách sản phẩm ==");
        productList.printProductList();
    }

}