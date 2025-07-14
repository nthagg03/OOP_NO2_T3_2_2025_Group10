# Class Diagram - Hệ Thống Bán Hàng

Đây là class diagram mô tả hệ thống quản lý bán hàng với các class chính và mối quan hệ giữa chúng.

```mermaid
classDiagram
    class NguoiDung {
        -String userID
        -String tenDangNhap
        -String matKhau
        -String hoTen
        -String vaiTro
        +setName(String hoten) String
        +getName() void
    }

    class SanPham {
        -String sanPhamID
        -String tenSanPham
        -String giaBan
        -String giaNhap
        -String soluongTonKho
        -String danhMucSanPham
        +getSanPhamID() String
        +setSanPhamID(String sanPhamID) void
        +getTenSanPham() String
        +setTenSanPham(String tenSanPham) void
        +getGiaBan() String
        +setGiaBan(String giaBan) void
        +getGiaNhap() String
        +setGiaNhap(String giaNhap) void
        +getSoluongTonKho() String
        +setSoluongTonKho(String soluongTonKho) void
        +getDanhMucSanPham() String
        +setDanhMucSanPham(String danhMucSanPham) void
    }

    class DanhMucSanPham {
        -String danhMucID
        -String tenDanhMuc
        +getDanhMucID() String
        +setDanhMucID(String danhMucID) void
        +getTenDanhMuc() String
        +setTenDanhMuc(String tenDanhMuc) void
    }

    class DonHang {
        -String IDDonHang
        -String IDSanPham
        -String soluong
        -String dongia
        -String GiamGia
        -String thanhtien
        -String trangthaidonhang
        -String hinhthucthanhtoan
        +DonHang(String IDDonHang, String IDSanPham, String soluong, String dongia, String GiamGia, String thanhtien, String trangthaidonhang, String hinhthucthanhtoan)
        +getIDDonHang() String
        +setIDDonHang(String IDDonHang) void
        +getIDSanPham() String
        +setIDSanPham(String IDSanPham) void
        +getSoluong() String
        +setSoluong(String soluong) void
        +getDongia() String
        +setDongia(String dongia) void
        +getGiamGia() String
        +setGiamGia(String GiamGia) void
        +getThanhtien() String
        +setThanhtien(String thanhtien) void
        +getTrangthaidonhang() String
        +setTrangthaidonhang(String trangthaidonhang) void
        +getHinhthucthanhtoan() String
        +setHinhthucthanhtoan(String hinhthucthanhtoan) void
    }

    class PhieuNhapHang {
        -String phieuNhapID
        -String ngayNhap
        -String nhanVienNhap
        -String soluongSanPham
        -String tenSanPham
        -String giaNhap
        -String tongGiatrinhap
        +getPhieuNhapID() String
        +setPhieuNhapID(String phieuNhapID) void
        +getNgayNhap() String
        +setNgayNhap(String ngayNhap) void
        +getNhanVienNhap() String
        +setNhanVienNhap(String nhanVienNhap) void
        +getSoluongSanPham() String
        +setSoluongSanPham(String soluongSanPham) void
        +getTenSanPham() String
        +setTenSanPham(String tenSanPham) void
        +getGiaNhap() String
        +setGiaNhap(String giaNhap) void
        +getTongGiatrinhap() String
        +setTongGiatrinhap(String tongGiatrinhap) void
    }

    class KhoHang {
        -String sanPhamID
        -String soluongHienTai
        -String ngayCapNhatCuoi
        +getSanPhamID() String
        +setSanPhamID(String sanPhamID) void
        +getSoluongHienTai() String
        +setSoluongHienTai(String soluongHienTai) void
        +getNgayCapNhatCuoi() String
        +setNgayCapNhatCuoi(String ngayCapNhatCuoi) void
    }

    %% Relationships
    SanPham }|--|| DanhMucSanPham : "thuộc về"
    DonHang }|--|| SanPham : "chứa"
    KhoHang ||--|| SanPham : "quản lý"
    PhieuNhapHang }|--|| SanPham : "nhập"
    NguoiDung ||--o{ DonHang : "tạo"
    NguoiDung ||--o{ PhieuNhapHang : "thực hiện"

    %% Notes
    note for SanPham "Sản phẩm có thể thuộc một danh mục"
    note for DonHang "Đơn hàng chứa nhiều sản phẩm"
    note for KhoHang "Quản lý tồn kho cho từng sản phẩm"
    note for PhieuNhapHang "Ghi nhận việc nhập hàng vào kho"
```

## Giải thích Relationships

### 1. SanPham - DanhMucSanPham
- **Relationship**: Many-to-One (N:1)
- **Mô tả**: Nhiều sản phẩm có thể thuộc về một danh mục sản phẩm
- **Cardinality**: `}|--||` (nhiều sản phẩm - một danh mục)

### 2. DonHang - SanPham
- **Relationship**: Many-to-One (N:1)
- **Mô tả**: Nhiều đơn hàng có thể chứa cùng một sản phẩm
- **Cardinality**: `}|--||` (nhiều đơn hàng - một sản phẩm)

### 3. KhoHang - SanPham
- **Relationship**: One-to-One (1:1)
- **Mô tả**: Mỗi sản phẩm có một bản ghi tồn kho tương ứng
- **Cardinality**: `||--||` (một kho hàng - một sản phẩm)

### 4. PhieuNhapHang - SanPham
- **Relationship**: Many-to-One (N:1)
- **Mô tả**: Nhiều phiếu nhập có thể chứa cùng một sản phẩm
- **Cardinality**: `}|--||` (nhiều phiếu nhập - một sản phẩm)

### 5. NguoiDung - DonHang
- **Relationship**: One-to-Many (1:N)
- **Mô tả**: Một người dùng có thể tạo nhiều đơn hàng
- **Cardinality**: `||--o{` (một người dùng - nhiều đơn hàng)

### 6. NguoiDung - PhieuNhapHang
- **Relationship**: One-to-Many (1:N)
- **Mô tả**: Một nhân viên có thể thực hiện nhiều phiếu nhập hàng
- **Cardinality**: `||--o{` (một người dùng - nhiều phiếu nhập)

## Access Modifiers

- **Private (-)**: Tất cả các thuộc tính được đánh dấu private để đảm bảo tính đóng gói
- **Public (+)**: Các phương thức getter, setter và constructor được đánh dấu public để có thể truy cập từ bên ngoài class

## Inheritance/Composition

Trong hệ thống hiện tại, các class chủ yếu sử dụng **Association** thay vì Inheritance hay Composition:
- Các class độc lập với nhau và liên kết thông qua các thuộc tính ID
- Không có quan hệ kế thừa (inheritance) giữa các class
- Sử dụng composition thông qua việc tham chiếu ID của các object khác