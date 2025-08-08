import java.util.ArrayList;
import java.util.Iterator;

public class QuanLySanPham {
    private ArrayList<SanPham> danhSachSanPham = new ArrayList<>();

    public void themSanPham(SanPham sp) {
        danhSachSanPham.add(sp);
        System.out.println("Đã thêm sản phẩm: " + sp.tenSanPham);
    }

    public void xoaSanPham(String id) {
        Iterator<SanPham> iterator = danhSachSanPham.iterator();
        while (iterator.hasNext()) {
            SanPham sp = iterator.next();
            if (sp.IDSanPham.equals(id)) {
                iterator.remove();
                System.out.println("Đã xoá sản phẩm có ID: " + id);
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm có ID: " + id);
    }

    public void capNhatSanPham(String id, SanPham sanPhamMoi) {
        for (int i = 0; i < danhSachSanPham.size(); i++) {
            if (danhSachSanPham.get(i).IDSanPham.equals(id)) {
                danhSachSanPham.set(i, sanPhamMoi);
                System.out.println("Đã cập nhật sản phẩm có ID: " + id);
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm để cập nhật.");
    }

    public void hienThiTatCa() {
        for (SanPham sp : danhSachSanPham) {
            sp.hienThiThongTin();
        }
    }
}
