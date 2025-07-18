public class testDH {
    public static void test() {

        DonHang dh1 = new DonHang("DH001", "SP001", 2, 500000, 50000, "Dang xu ly", "Tien mat");

        DonHang dh2 = new DonHang("DH002", "SP002", 1, 1000000, 0, "Da giao", "Chuyen khoan");

        DonHang.themDonHang(dh2);
        DonHang.themDonHang(dh1);

        System.out.println("==================danh sach don hang: ===========================");

        readDonHang();
    }

    public static void readDonHang() {
        System.out.println("+----------+-------------+------------+---------------+");
        System.out.println("| Ma DH    | Ma SP       | So Luong   | Don Gia       |");
        System.out.println("+----------+-------------+------------+---------------+");

        for (DonHang donHang : DonHang.layTatCaDonHang()) {
            System.out.printf("| %-8s | %-11s | %-10d | %13.0f |\n",
                    donHang.getIdDonHang(),
                    donHang.getIdSanPham(),
                    donHang.getSoLuong(),
                    donHang.getDonGia());
        }

        System.out.println("+----------+-------------+------------+---------------+");
    }
}
