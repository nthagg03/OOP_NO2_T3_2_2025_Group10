public class TestQuanlySanPham {
    public static void test() {
        QuanLySanPham ql = new QuanLySanPham();

        SanPham sp1 = new SanPham("SP001", "Trà Sữa", 25000, 18000, 100, "DM01");
        SanPham sp2 = new SanPham("SP002", "Trà Đào", 27000, 19000, 50, "DM02");
        ql.themSanPham(sp1);
        ql.themSanPham(sp2);
        ql.hienThiTatCa();

        SanPham sp2New = new SanPham("SP002", "Trà Đào Sữa", 28000, 20000, 60, "DM02");
        ql.capNhatSanPham("SP002", sp2New);
        ql.hienThiTatCa();
        ql.xoaSanPham("SP001");
        ql.hienThiTatCa();
    }
}
