import java.util.ArrayList;
import java.util.List;

public class HoaDon {
    private static List<HoaDon> danhSachHoaDon = new ArrayList<>();
    
    private String maHoaDon;
    private String maKhachHang;
    private String ngayLap;
    private double tongTien;

    public HoaDon(String maHoaDon, String maKhachHang, String ngayLap, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public static boolean themHoaDon(HoaDon hoaDon) {
        if (timHoaDonTheoMa(hoaDon.getMaHoaDon()) == null) {
            danhSachHoaDon.add(hoaDon);
            return true;
        }
        return false;
    }

    public static HoaDon timHoaDonTheoMa(String maHoaDon) {
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getMaHoaDon().equals(maHoaDon)) {
                return hd;
            }
        }
        return null;
    }

    public static List<HoaDon> layTatCaHoaDon() {
        return new ArrayList<>(danhSachHoaDon);
    }

    public static List<HoaDon> layHoaDonTheoKhachHang(String maKhachHang) {
        List<HoaDon> ketQua = new ArrayList<>();
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getMaKhachHang().equals(maKhachHang)) {
                ketQua.add(hd);
            }
        }
        return ketQua;
    }

    public static boolean capNhatHoaDon(String maHoaDon, String maKhachHangMoi, String ngayLapMoi, double tongTienMoi) {
        HoaDon hoaDon = timHoaDonTheoMa(maHoaDon);
        if (hoaDon != null) {
            hoaDon.setMaKhachHang(maKhachHangMoi);
            hoaDon.setNgayLap(ngayLapMoi);
            hoaDon.setTongTien(tongTienMoi);
            return true;
        }
        return false;
    }

    public static boolean xoaHoaDon(String maHoaDon) {
        HoaDon hoaDon = timHoaDonTheoMa(maHoaDon);
        if (hoaDon != null) {
            danhSachHoaDon.remove(hoaDon);
            return true;
        }
        return false;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                ", ngayLap='" + ngayLap + '\'' +
                ", tongTien=" + tongTien +
                '}';
    }
}
    