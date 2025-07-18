package xthang;

public class DanhMucSanPham {
    private String danhMucID;
    private String tenDanhMuc;

    public DanhMucSanPham(String danhMucID, String tenDanhMuc) {
        this.danhMucID = danhMucID;
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getDanhMucID() {
        return danhMucID;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

}
