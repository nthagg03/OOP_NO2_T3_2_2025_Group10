import java.util.ArrayList;
import java.util.List;

public class KhachHang {
    private static List<KhachHang> danhSachKhachHang = new ArrayList<>();
    
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

    // CREATE - Them khach hang moi
    public static boolean themKhachHang(KhachHang khachHang) {
        if (timKhachHangTheoMa(khachHang.getMaKhachHang()) == null) {
            danhSachKhachHang.add(khachHang);
            return true;
        }
        return false;
    }

    // READ - Tim khach hang theo ma
    public static KhachHang timKhachHangTheoMa(String maKhachHang) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getMaKhachHang().equals(maKhachHang)) {
                return kh;
            }
        }
        return null;
    }

    // READ - Lay tat ca khach hang
    public static List<KhachHang> layTatCaKhachHang() {
        return new ArrayList<>(danhSachKhachHang);
    }

    // READ - Tim khach hang theo ten
    public static List<KhachHang> timKhachHangTheoTen(String tenKhachHang) {
        List<KhachHang> ketQua = new ArrayList<>();
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getTenKhachHang().toLowerCase().contains(tenKhachHang.toLowerCase())) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }

    // UPDATE - Cap nhat khach hang
    public static boolean capNhatKhachHang(String maKhachHang, String tenMoi, String sdtMoi, String emailMoi, String diaChiMoi) {
        KhachHang khachHang = timKhachHangTheoMa(maKhachHang);
        if (khachHang != null) {
            khachHang.setTenKhachHang(tenMoi);
            khachHang.setSoDienThoai(sdtMoi);
            khachHang.setEmail(emailMoi);
            khachHang.setDiaChi(diaChiMoi);
            return true;
        }
        return false;
    }

    // DELETE - Xoa khach hang
    public static boolean xoaKhachHang(String maKhachHang) {
        KhachHang khachHang = timKhachHangTheoMa(maKhachHang);
        if (khachHang != null) {
            danhSachKhachHang.remove(khachHang);
            return true;
        }
        return false;
    }

    public void hienThiThongTin() {
        System.out.println("Ma KH: " + maKhachHang);
        System.out.println("Ten KH: " + tenKhachHang);
        System.out.println("SDT   : " + soDienThoai);
        System.out.println("Email : " + email);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("---------------------------");
    }

    // Getter methods
    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    // Setter methods
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
