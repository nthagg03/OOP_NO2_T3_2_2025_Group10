import java.util.ArrayList;
import java.util.List;

public class DonHang {
    // Static list để lưu trữ tất cả đơn hàng
    private static List<DonHang> danhSachDonHang = new ArrayList<>();
    
    // Thuộc tính
    private String idDonHang;
    private String idSanPham;
    private int soLuong;
    private double donGia;
    private double giamGia;
    private double thanhTien;
    private String trangThaiDonHang;
    private String hinhThucThanhToan;

    // Constructor
    public DonHang(String idDonHang, String idSanPham, int soLuong, double donGia, double giamGia, String trangThaiDonHang, String hinhThucThanhToan) {
        this.idDonHang = idDonHang;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.giamGia = giamGia;
        this.thanhTien = (soLuong * donGia) - giamGia;
        this.trangThaiDonHang = trangThaiDonHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }


    public static boolean themDonHang(DonHang donHang) {
        if (timDonHangTheoId(donHang.getIdDonHang()) == null) {
            danhSachDonHang.add(donHang);
            return true;
        }
        return false; // ID đã tồn tại
    }

    // READ - Tìm đơn hàng theo ID
    public static DonHang timDonHangTheoId(String id) {
        for (DonHang dh : danhSachDonHang) {
            if (dh.getIdDonHang().equals(id)) {
                return dh;
            }
        }
        return null;
    }

    // READ - Lấy tất cả đơn hàng
    public static List<DonHang> layTatCaDonHang() {
        return new ArrayList<>(danhSachDonHang);
    }

    // UPDATE - Cập nhật đơn hàng
    public static boolean capNhatDonHang(String id, String idSanPham, int soLuong, double donGia, double giamGia, String trangThaiDonHang, String hinhThucThanhToan) {
        DonHang donHang = timDonHangTheoId(id);
        if (donHang != null) {
            donHang.setIdSanPham(idSanPham);
            donHang.setSoLuong(soLuong);
            donHang.setDonGia(donGia);
            donHang.setGiamGia(giamGia);
            donHang.setThanhTien((soLuong * donGia) - giamGia);
            donHang.setTrangThaiDonHang(trangThaiDonHang);
            donHang.setHinhThucThanhToan(hinhThucThanhToan);
            return true;
        }
        return false;
    }

    // DELETE - Xóa đơn hàng
    public static boolean xoaDonHang(String id) {
        DonHang donHang = timDonHangTheoId(id);
        if (donHang != null) {
            danhSachDonHang.remove(donHang);
            return true;
        }
        return false;
    }

    // Getter và Setter
    public String getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(String idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "idDonHang='" + idDonHang + '\'' +
                ", idSanPham='" + idSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", giamGia=" + giamGia +
                ", thanhTien=" + thanhTien +
                ", trangThaiDonHang='" + trangThaiDonHang + '\'' +
                ", hinhThucThanhToan='" + hinhThucThanhToan + '\'' +
                '}';
    }
}