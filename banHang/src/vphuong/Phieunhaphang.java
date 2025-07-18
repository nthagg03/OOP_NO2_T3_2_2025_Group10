package vphuong;
public class Phieunhaphang {
    
    String phieuNhapID;
    String ngayNhap;
    String nhanVienNhap;
    String soluongSanPham;
    String tenSanPham;
    String giaNhap;
    String tongGiatrinhap;
    
}
    public PhieuNhapHang(String maPhieuNhap, String ngayNhap, String nhanVienNhap, int soLuongSanPham, String tenSanPham, double giaNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.nhanVienNhap = nhanVienNhap;
        this.soLuongSanPham = soLuongSanPham;
        this.tenSanPham = tenSanPham;
        this.giaNhap = giaNhap;
        this.tongGiaTriNhap = soLuongSanPham * giaNhap;
    }
}
