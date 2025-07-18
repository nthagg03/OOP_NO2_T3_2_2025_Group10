public class TestSanPham {
    public static void main(String[] args) {
        Product sanPham1 = new Product("SP001", "Trà Sữa", 25000, 18000, 100, "DM01");
        System.out.println("--- Thông tin Sản phẩm ---");
        System.out.println("Mã SP: " + sanPham1.id);
        System.out.println("Tên SP: " + sanPham1.name);
        System.out.println("Giá bán: " + sanPham1.sellPrice);
        System.out.println("Giá nhập: " + sanPham1.buyPrice);
        System.out.println("Tồn kho: " + sanPham1.stock);
        System.out.println("Mã danh mục: " + sanPham1.categoryId);
    }
}
