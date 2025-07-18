## OOP_NO2_T3_2_2025_GroupA
**Vũ Văn Phương**
**MSV**
**Nguyễn Xuân Thắng** 
**MSV:** 24100529

## OOP Project - Sales Management System
**Sales Management System** là một ứng dụng quản lý bán hàng được phát triển bằng Java.  
Ứng dụng hỗ trợ quản lý sản phẩm, giỏ hàng, đơn hàng và các tính năng liên quan đến việc bán hàng.  
Dự án được thiết kế với giao diện dòng lệnh (Console-based UI) đơn giản và dễ sử dụng.

---
## Phân tích đối tượng
### Người dùng:
- ID người dùng
- Tên đăng nhập
- Mật khẩu
- Họ tên
- Vai trò

### Sản phẩm:
- ID sản phẩm
- Tên sản phẩm
- Giá bán
- Giá nhập
- Số lượng tồn kho
- Danh mục sản phẩm

### Danh mục sản phẩm:
- ID danh mục
- Tên danh mục

### Đơn hàng(Chi tiết đơn hàng):
- ID đơn hàng
- ID sản phẩm
- Số lượng
- Đơn giá
- Giảm giá (nếu có)
- Thành tiền
- Hình thức thanh toán (tiền mặt, chuyển khoản, QR...)
- Trạng thái đơn hàng (đã thanh toán, đang xử lý, huỷ, trả hàng…)

### Phiếu nhập hàng:
- ID phiếu nhập
- số lượng sản phẩm
- tên sản phẩm
- giá nhập
- Ngày nhập
- Nhân viên thực hiện
- Tổng giá trị nhập


### Kho hàng:
- ID sản phẩm
- Số lượng hiện tại
- Ngày cập nhật cuối
