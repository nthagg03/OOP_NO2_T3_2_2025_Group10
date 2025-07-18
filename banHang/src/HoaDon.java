

public class HoaDon {
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

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

}
    