import java.util.ArrayList;

public class testInventory {
    public static void test() {
        ArrayList<Product> danhSachSanPham = new ArrayList<>();
        Inventory khoHang = new Inventory();
        
        khoHang.addProduct(danhSachSanPham, 101, "San pham A", 50);
        khoHang.addProduct(danhSachSanPham, 102, "San pham B", 30);
        khoHang.addProduct(danhSachSanPham, 103, "San pham C", 75);

        System.out.println("================== Test Quan Ly Kho Hang ===========================");
        
        System.out.println("\n1. Hien thi tat ca san pham trong kho:");
        hienThiDanhSachSanPham(danhSachSanPham);
        
        System.out.println("\n2. Cap nhat so luong ton kho sau khi ban:");
        khoHang.updateStockAfterSale(danhSachSanPham, 101, 10);
        System.out.println("Da ban 10 san pham A");
        hienThiDanhSachSanPham(danhSachSanPham);
        
        System.out.println("\n3. Xoa san pham khoi kho:");
        khoHang.deleteProduct(danhSachSanPham, 102);
        System.out.println("Da xoa san pham B");
        hienThiDanhSachSanPham(danhSachSanPham);
        
        System.out.println("\n4. Them san pham moi:");
        khoHang.addProduct(danhSachSanPham, 104, "San pham D", 60);
        System.out.println("Da them san pham D");
        hienThiDanhSachSanPham(danhSachSanPham);
    }

    public static void hienThiDanhSachSanPham(ArrayList<Product> danhSachSanPham) {
        System.out.println("+----------+---------------+---------------+");
        System.out.println("| Ma SP    | Ten San Pham  | So Luong Ton |");
        System.out.println("+----------+---------------+---------------+");
        
        for (Product sanPham : danhSachSanPham) {
            System.out.printf("| %-8d | %-13s | %13d |\n", 
                sanPham.productID, 
                sanPham.name, 
                sanPham.stockQuantity);
        }
        
        System.out.println("+----------+---------------+---------------+");
    }
}
