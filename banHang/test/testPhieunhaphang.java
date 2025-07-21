public class testPhieunhaphang {
    public static void test() {
        Phieunhaphang pn1 = new Phieunhaphang("PN001", "2025-07-18", "Nguyen Van B", 30, "Tra dao", 15000);
        Phieunhaphang pn2 = new Phieunhaphang("PN002", "2025-07-19", "Tran Thi C", 50, "Ca phe", 12000);
        Phieunhaphang pn3 = new Phieunhaphang("PN003", "2025-07-20", "Le Van D", 25, "Tra sua", 18000);

        Phieunhaphang.themPhieuNhap(pn1);
        Phieunhaphang.themPhieuNhap(pn2);
        Phieunhaphang.themPhieuNhap(pn3);

        System.out.println("================== Danh Sach Phieu Nhap Hang ===========================");
        
        hienThiDanhSachPhieuNhap();
    }

    public static void hienThiDanhSachPhieuNhap() {
        System.out.println("+----------+------------+---------------+----------+---------------+----------+---------------+");
        System.out.println("| Ma Phieu | Ngay Nhap  | Nhan Vien     | So Luong | Ten San Pham  | Gia Nhap | Tong Gia Tri |");
        System.out.println("+----------+------------+---------------+----------+---------------+----------+---------------+");
        
        for (Phieunhaphang phieu : Phieunhaphang.layTatCaPhieuNhap()) {
            System.out.printf("| %-8s | %-10s | %-13s | %8d | %-13s | %8.0f | %13.0f |\n", 
                phieu.getMaPhieuNhap(), 
                phieu.getNgayNhap(), 
                phieu.getNhanVienNhap(), 
                phieu.getSoLuongSanPham(), 
                phieu.getTenSanPham(), 
                phieu.getGiaNhap(), 
                phieu.getTongGiaTriNhap());
        }
        
        System.out.println("+----------+------------+---------------+----------+---------------+----------+---------------+");
    }
}
