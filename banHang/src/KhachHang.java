public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String email;
    private String diaChi;

    public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoai, String email, String diaChi) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
    }

    public void hienThiThongTin() {
        System.out.println("Mã KH: " + maKhachHang);
        System.out.println("Tên KH: " + tenKhachHang);
        System.out.println("SĐT   : " + soDienThoai);
        System.out.println("Email : " + email);
        System.out.println("Địa chỉ: " + diaChi);
        System.out.println("---------------------------");
    }
}
