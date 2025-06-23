public class TestNguoiDung {
    public static void test() {
        // Tạo người dùng
        Nguoidung nguoidung = new Nguoidung("ND001", "user1", "pass123", "Nguyen Van A", "admin");

        // Thêm chi tiêu cho người dùng
        nguoidung.addChiTieu(200000); // Thêm chi tiêu

        // In thông tin
        System.out.println("Mã người dùng: " + nguoidung.getNguoiDungID());
        System.out.println("Tên đăng nhập: " + nguoidung.getTenDangNhap());
        System.out.println("Mật khẩu: " + nguoidung.getMatKhau());
        System.out.println("Họ tên: " + nguoidung.getHoTen());
        System.out.println("Vai trò: " + nguoidung.getVaitro());
        System.out.println("Tổng chi tiêu: " + nguoidung.getTongChiTieu() + " VND");
    }
}