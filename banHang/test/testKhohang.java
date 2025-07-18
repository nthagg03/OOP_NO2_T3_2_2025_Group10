public class TestKhoHang {
    public static void main(String[] args) {
        Inventory kho1 = new Inventory("SP001", 120, "2025-07-18");
        System.out.println("--- Thông tin Kho hàng ---");
        System.out.println("Mã sản phẩm: " + kho1.productId);
        System.out.println("Số lượng hiện tại: " + kho1.currentStock);
        System.out.println("Ngày cập nhật cuối: " + kho1.lastUpdated);
    }
}
