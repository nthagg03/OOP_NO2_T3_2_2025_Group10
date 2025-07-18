public class TestPhieuNhapHang {
    public static void main(String[] args) {
        Receipt phieu1 = new Receipt("PN001", 30, "Trà Đào", 15000, "2025-07-18", "Nguyễn Văn B");
        System.out.println("--- Thông tin Phiếu nhập hàng ---");
        System.out.println("Mã phiếu nhập: " + phieu1.receiptId);
        System.out.println("Tên sản phẩm: " + phieu1.productName);
        System.out.println("Số lượng: " + phieu1.quantity);
        System.out.println("Giá nhập: " + phieu1.importPrice);
        System.out.println("Ngày nhập: " + phieu1.importDate);
        System.out.println("Nhân viên: " + phieu1.employee);
        System.out.println("Tổng giá trị nhập: " + phieu1.totalPrice);
    }
}
