class SanPham {
    String IDSanPham;
    String tenSanPham;
    double giaBan;    
    double giaNhap;
    int soLuongTonKho;
    String maDanhMuc;

    public SanPham(String IDSanPham, String tenSanPham, double giaBan, double giaNhap, int soLuongTonKho, String maDanhMuc) {
        this.IDSanPham = IDSanPham;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.soLuongTonKho = soLuongTonKho;
        this.maDanhMuc = maDanhMuc;
    }

    public void hienThiThongTin() {
    System.out.println("ID: " + IDSanPham);
    System.out.println("Tên sản phẩm: " + tenSanPham);
    System.out.println("Giá bán: " + giaBan);
    System.out.println("Giá nhập: " + giaNhap);
    System.out.println("Số lượng hiện tại: " + soLuongTonKho);
    System.out.println("Mã danh mục: " + maDanhMuc);
    System.out.println("-----------------------------");
    }
}

