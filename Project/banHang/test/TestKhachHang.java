public class TestKhachHang {
    public static void main(String[] args) {
        KhachHang khach1 = new KhachHang("KH001", "Nguyễn Thị Lan", "0901234567", "lan123@gmail.com", "123 Lê Lợi, Q1, TP.HCM");
        System.out.println("--- Thông tin Khách hàng ---");
        khach1.hienThiThongTin();
    }
}
