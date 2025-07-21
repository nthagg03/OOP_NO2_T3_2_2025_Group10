public class testSanpham {
    public static void test() {
        System.out.println("================== Danh Sach San Pham ===========================");
        
        hienThiDanhSachSanPham();
    }

    public static void hienThiDanhSachSanPham() {
        SanPham sp1 = new SanPham("SP001", "Tra sua", 25000, 18000, 100, "DM01");
        SanPham sp2 = new SanPham("SP002", "Ca phe", 20000, 15000, 50, "DM02");
        SanPham sp3 = new SanPham("SP003", "Tra dao", 22000, 16000, 75, "DM01");
        
        System.out.println("+----------+---------------+----------+----------+----------+----------+");
        System.out.println("| Ma SP    | Ten San Pham  | Gia Ban  | Gia Nhap | Ton Kho  | Ma DM    |");
        System.out.println("+----------+---------------+----------+----------+----------+----------+");
        
        hienThiSanPham(sp1);
        hienThiSanPham(sp2);
        hienThiSanPham(sp3);
        
        System.out.println("+----------+---------------+----------+----------+----------+----------+");
    }
    
    public static void hienThiSanPham(SanPham sp) {
        System.out.printf("| %-8s | %-13s | %8.0f | %8.0f | %8d | %-8s |\n", 
            sp.maSanPham, 
            sp.tenSanPham, 
            sp.giaBan, 
            sp.giaNhap, 
            sp.soLuongTonKho, 
            sp.maDanhMuc);
    }
}
