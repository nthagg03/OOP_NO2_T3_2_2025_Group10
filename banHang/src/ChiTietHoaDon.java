

public class ChiTietHoaDon {
    private String maHoaDon;
    private String maSanPham;
    private int soLuong;
    private double gia;

    public ChiTietHoaDon(String maHoaDon, String maSanPham, int soLuong, double gia) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGia() {
        return gia;
    }

}
