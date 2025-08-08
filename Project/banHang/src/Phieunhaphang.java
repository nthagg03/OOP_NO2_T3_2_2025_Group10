import java.util.ArrayList;
import java.util.List;

class Phieunhaphang {
    private static List<Phieunhaphang> danhSachPhieuNhap = new ArrayList<>();
    
    private String maPhieuNhap;
    private String ngayNhap;
    private String nhanVienNhap;
    private int soLuongSanPham;
    private String tenSanPham;
    private double giaNhap;
    private double tongGiaTriNhap;
    
    public Phieunhaphang(String maPhieuNhap, String ngayNhap, String nhanVienNhap, int soLuongSanPham, String tenSanPham, double giaNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.nhanVienNhap = nhanVienNhap;
        this.soLuongSanPham = soLuongSanPham;
        this.tenSanPham = tenSanPham;
        this.giaNhap = giaNhap;
        this.tongGiaTriNhap = soLuongSanPham * giaNhap;
    }

    // CREATE - Them phieu nhap hang
    public static void themPhieuNhap(Phieunhaphang phieuNhap) {
        danhSachPhieuNhap.add(phieuNhap);
    }

    // READ - Tim phieu nhap theo ma
    public static Phieunhaphang timPhieuNhapTheoMa(String maPhieuNhap) {
        for (Phieunhaphang phieu : danhSachPhieuNhap) {
            if (phieu.getMaPhieuNhap().equals(maPhieuNhap)) {
                return phieu;
            }
        }
        return null;
    }

    // READ - Lay tat ca phieu nhap
    public static List<Phieunhaphang> layTatCaPhieuNhap() {
        return new ArrayList<>(danhSachPhieuNhap);
    }

    // UPDATE - Cap nhat phieu nhap
    public static boolean capNhatPhieuNhap(String maPhieuNhap, String ngayNhap, String nhanVienNhap, int soLuongSanPham, String tenSanPham, double giaNhap) {
        Phieunhaphang phieu = timPhieuNhapTheoMa(maPhieuNhap);
        if (phieu != null) {
            phieu.setNgayNhap(ngayNhap);
            phieu.setNhanVienNhap(nhanVienNhap);
            phieu.setSoLuongSanPham(soLuongSanPham);
            phieu.setTenSanPham(tenSanPham);
            phieu.setGiaNhap(giaNhap);
            phieu.setTongGiaTriNhap(soLuongSanPham * giaNhap);
            return true;
        }
        return false;
    }

    // DELETE - Xoa phieu nhap
    public static boolean xoaPhieuNhap(String maPhieuNhap) {
        return danhSachPhieuNhap.removeIf(phieu -> phieu.getMaPhieuNhap().equals(maPhieuNhap));
    }

    // Tim phieu nhap theo nhan vien
    public static List<Phieunhaphang> timPhieuNhapTheoNhanVien(String nhanVienNhap) {
        List<Phieunhaphang> ketQua = new ArrayList<>();
        for (Phieunhaphang phieu : danhSachPhieuNhap) {
            if (phieu.getNhanVienNhap().toLowerCase().contains(nhanVienNhap.toLowerCase())) {
                ketQua.add(phieu);
            }
        }
        return ketQua;
    }

    // Tim phieu nhap theo ngay
    public static List<Phieunhaphang> timPhieuNhapTheoNgay(String ngayNhap) {
        List<Phieunhaphang> ketQua = new ArrayList<>();
        for (Phieunhaphang phieu : danhSachPhieuNhap) {
            if (phieu.getNgayNhap().equals(ngayNhap)) {
                ketQua.add(phieu);
            }
        }
        return ketQua;
    }

    // Getter methods
    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public String getNhanVienNhap() {
        return nhanVienNhap;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public double getTongGiaTriNhap() {
        return tongGiaTriNhap;
    }

    // Setter methods
    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setNhanVienNhap(String nhanVienNhap) {
        this.nhanVienNhap = nhanVienNhap;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public void setTongGiaTriNhap(double tongGiaTriNhap) {
        this.tongGiaTriNhap = tongGiaTriNhap;
    }

    @Override
    public String toString() {
        return "Phieunhaphang{" +
                "maPhieuNhap='" + maPhieuNhap + '\'' +
                ", ngayNhap='" + ngayNhap + '\'' +
                ", nhanVienNhap='" + nhanVienNhap + '\'' +
                ", soLuongSanPham=" + soLuongSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaNhap=" + giaNhap +
                ", tongGiaTriNhap=" + tongGiaTriNhap +
                '}';
    }
}
