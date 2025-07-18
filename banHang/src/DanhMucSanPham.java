import java.util.ArrayList;
import java.util.List;

public class DanhMucSanPham {
    private static List<DanhMucSanPham> danhSachDanhMuc = new ArrayList<>();
    
    private String danhMucID;
    private String tenDanhMuc;

    public DanhMucSanPham(String danhMucID, String tenDanhMuc) {
        this.danhMucID = danhMucID;
        this.tenDanhMuc = tenDanhMuc;
    }

    public static boolean themDanhMuc(DanhMucSanPham danhMuc) {
        if (timDanhMucTheoID(danhMuc.getDanhMucID()) == null) {
            danhSachDanhMuc.add(danhMuc);
            return true;
        }
        return false;
    }

    public static DanhMucSanPham timDanhMucTheoID(String id) {
        for (DanhMucSanPham dm : danhSachDanhMuc) {
            if (dm.getDanhMucID().equals(id)) {
                return dm;
            }
        }
        return null;
    }

    public static List<DanhMucSanPham> layTatCaDanhMuc() {
        return new ArrayList<>(danhSachDanhMuc);
    }

    public static boolean capNhatDanhMuc(String id, String tenDanhMucMoi) {
        DanhMucSanPham danhMuc = timDanhMucTheoID(id);
        if (danhMuc != null) {
            danhMuc.setTenDanhMuc(tenDanhMucMoi);
            return true;
        }
        return false;
    }

    public static boolean xoaDanhMuc(String id) {
        DanhMucSanPham danhMuc = timDanhMucTheoID(id);
        if (danhMuc != null) {
            danhSachDanhMuc.remove(danhMuc);
            return true;
        }
        return false;
    }

    public String getDanhMucID() {
        return danhMucID;
    }

    public void setDanhMucID(String danhMucID) {
        this.danhMucID = danhMucID;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Override
    public String toString() {
        return "DanhMucSanPham{" +
                "danhMucID='" + danhMucID + '\'' +
                ", tenDanhMuc='" + tenDanhMuc + '\'' +
                '}';
    }
}
