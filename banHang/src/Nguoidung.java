public class Nguoidung {
    private String nguoiDungID;
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String vaitro;
    private double tongChiTieu; // Thêm thuộc tính giống medicalCost

    // Constructor
    public Nguoidung(String nguoiDungID, String tenDangNhap, String matKhau, String hoTen, String vaitro) {
        this.nguoiDungID = nguoiDungID;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaitro = vaitro;
        this.tongChiTieu = 0; // Khởi tạo chi tiêu ban đầu
    }

    // Getter & Setter
    public String getNguoiDungID() { return nguoiDungID; }
    public String getTenDangNhap() { return tenDangNhap; }
    public String getMatKhau() { return matKhau; }
    public String getHoTen() { return hoTen; }
    public String getVaitro() { return vaitro; }
    public double getTongChiTieu() { return tongChiTieu; }
    public void addChiTieu(double chiTieu) { this.tongChiTieu += chiTieu; }

    // In thông tin
    @Override
    public String toString() {
        return "Người dùng: " + hoTen + ", ID: " + nguoiDungID + ", Vai trò: " + vaitro;
    }
}