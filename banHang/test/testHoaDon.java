public class testHoaDon {
    public static void test() {

        HoaDon hd1 = new HoaDon("HD001", "KH001", "2023-10-01", 2950000);

        HoaDon hd2 = new HoaDon("HD002", "KH002", "2023-10-02", 1500000);

        HoaDon.themHoaDon(hd2);
        HoaDon.themHoaDon(hd1);

        System.out.println("==================danh sach hoa don: ===========================");

        readHoaDon();
    }

    public static void readHoaDon() {
        System.out.println("+----------+-------------+------------+---------------+");
        System.out.println("| Ma HD    | Ma KH       | Ngay Lap   | Tong Tien     |");
        System.out.println("+----------+-------------+------------+---------------+");
        
        for (HoaDon hoaDon : HoaDon.layTatCaHoaDon()) {
            System.out.printf("| %-8s | %-11s | %-10s | %13.0f |\n", 
                hoaDon.getMaHoaDon(), 
                hoaDon.getMaKhachHang(), 
                hoaDon.getNgayLap(), 
                hoaDon.getTongTien());
        }
        
        System.out.println("+----------+-------------+------------+---------------+");
    }
}
