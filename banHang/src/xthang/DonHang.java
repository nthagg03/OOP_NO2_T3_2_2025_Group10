package xthang;
public class DonHang {
   
    // biến thành viên
    String IDDonHang;
    String IDSanPham;
    String soluong;
    String dongia;
    String GiamGia;
    String thanhtien;
    String trangthaidonhang;
    String hinhthucthanhtoan;

    // phương thức khởi tạo
    public DonHang(String IDDonHang, String IDSanPham, String soluong, String dongia, String GiamGia, String thanhtien, String trangthaidonhang, String hinhthucthanhtoan) {
        this.IDDonHang = IDDonHang;
        this.IDSanPham = IDSanPham;
        this.soluong = soluong;
        this.dongia = dongia;
        this.GiamGia = GiamGia;
        this.thanhtien = thanhtien;
        this.trangthaidonhang = trangthaidonhang;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }
}
