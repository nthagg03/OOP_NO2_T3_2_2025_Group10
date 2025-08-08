import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDon {
    private static List<ChiTietHoaDon> danhSachChiTietHoaDon = new ArrayList<>();
    
    private String maHoaDon;
    private String maSanPham;
    private int soLuong;
    private double gia;
    private double thanhTien;

    public ChiTietHoaDon(String maHoaDon, String maSanPham, int soLuong, double gia) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.thanhTien = soLuong * gia;
    }
    public static boolean themChiTietHoaDon(ChiTietHoaDon chiTiet) {
        if (timChiTietTheoMa(chiTiet.getMaHoaDon(), chiTiet.getMaSanPham()) == null) {
            danhSachChiTietHoaDon.add(chiTiet);
            return true;
        }
        return false; 
    }
    public static ChiTietHoaDon timChiTietTheoMa(String maHoaDon, String maSanPham) {
        for (ChiTietHoaDon ct : danhSachChiTietHoaDon) {
            if (ct.getMaHoaDon().equals(maHoaDon) && ct.getMaSanPham().equals(maSanPham)) {
                return ct;
            }
        }
        return null;
    }

    public static List<ChiTietHoaDon> layTatCaChiTietHoaDon() {
        return new ArrayList<>(danhSachChiTietHoaDon);
    }

    public static List<ChiTietHoaDon> layChiTietTheoMaHoaDon(String maHoaDon) {
        List<ChiTietHoaDon> ketQua = new ArrayList<>();
        for (ChiTietHoaDon ct : danhSachChiTietHoaDon) {
            if (ct.getMaHoaDon().equals(maHoaDon)) {
                ketQua.add(ct);
            }
        }
        return ketQua;
    }

    public static boolean capNhatChiTietHoaDon(String maHoaDon, String maSanPham, int soLuongMoi, double giaMoi) {
        ChiTietHoaDon chiTiet = timChiTietTheoMa(maHoaDon, maSanPham);
        if (chiTiet != null) {
            chiTiet.setSoLuong(soLuongMoi);
            chiTiet.setGia(giaMoi);
            chiTiet.setThanhTien(soLuongMoi * giaMoi);
            return true;
        }
        return false;
    }

    public static boolean xoaChiTietHoaDon(String maHoaDon, String maSanPham) {
        ChiTietHoaDon chiTiet = timChiTietTheoMa(maHoaDon, maSanPham);
        if (chiTiet != null) {
            danhSachChiTietHoaDon.remove(chiTiet);
            return true;
        }
        return false;
    }

    public static boolean xoaTatCaChiTietTheoMaHoaDon(String maHoaDon) {
        boolean daXoa = false;
        List<ChiTietHoaDon> dsCanXoa = new ArrayList<>();
        for (ChiTietHoaDon ct : danhSachChiTietHoaDon) {
            if (ct.getMaHoaDon().equals(maHoaDon)) {
                dsCanXoa.add(ct);
                daXoa = true;
            }
        }
        danhSachChiTietHoaDon.removeAll(dsCanXoa);
        return daXoa;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", maSanPham='" + maSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", gia=" + gia +
                ", thanhTien=" + thanhTien +
                '}';
    }

}
