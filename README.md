# 🚀 Hệ thống Quản lý Bán hàng
> **Dự án cuối kỳ môn Lập trình Hướng Đối Tượng (OOP)**  
> Nhóm A – K18 – Term 3 – Năm học 2025  
> Giảng viên hướng dẫn: TS. Nguyễn Lệ Thu


Dự án **Hệ thống Quản lý Bán hàng** được phát triển bằng ngôn ngữ Java, là bài tập lớn cho môn học Lập trình Hướng đối tượng (OOP). Ứng dụng cung cấp các chức năng cơ bản để quản lý sản phẩm, đơn hàng, khách hàng và kho hàng thông qua giao diện dòng lệnh (Console-based UI).

---
## 🎯 Giới thiệu

Mục tiêu của dự án là xây dựng một ứng dụng phần mềm hoàn chỉnh, áp dụng các nguyên tắc của lập trình hướng đối tượng (OOP) như tính đóng gói, kế thừa, đa hình và trừu tượng.

Hệ thống được thiết kế để giải quyết các bài toán cơ bản trong việc quản lý bán hàng, bao gồm quản lý sản phẩm, theo dõi đơn hàng, và kiểm soát hàng tồn kho. Với giao diện dòng lệnh (console) đơn giản và thân thiện, ứng dụng phù hợp cho mục đích học tập và có thể dễ dàng mở rộng, phát triển thêm các tính năng nâng cao trong tương lai.

---
## 📖 Mục lục

- [Giới thiệu](#-giới-thiệu)
- [Thành viên nhóm](#-thành-viên-nhóm)
- [Tính năng chính](#-tính-năng-chính)
- [Phân tích và Thiết kế](#-phân-tích-và-thiết-kế)
  


---

## 👥 Thành viên nhóm

| STT | Họ tên           |     Mã sinh viên  |            GitHub                                    |
|-----|------------------|-------------------|------------------------------------------------------|
| 1   | Nguyễn Xuân Thắng|   24100529        | [nthagg03](https://github.com/nthagg03)              |
| 2   | Vũ Văn Phương    |   24100373        | [mphw0312](https://github.com/mphw0312)              |
| 3   | Nguyễn Lệ Thu    |                   | [nglthu](https://github.com/nglthu)                  |

---

## ✨ Tính năng chính

-   ✅ **Quản lý Sản phẩm:** Thêm, xóa, sửa, và tìm kiếm sản phẩm.
-   ✅ **Quản lý Đơn hàng:** Tạo đơn hàng, xử lý thanh toán và cập nhật trạng thái.
-   ✅ **Quản lý Kho hàng:** Theo dõi tồn kho và tạo phiếu nhập hàng.
-   ✅ **Quản lý Người dùng:** Phân quyền người dùng (quản trị viên, nhân viên).
-   ✅ **Thanh toán:** Hỗ trợ nhiều hình thức thanh toán.
-   📊 **Thống kê & Báo cáo:** (Gợi ý) Xem báo cáo doanh thu, sản phẩm bán chạy.

---
## 🏗️ Phân tích và Thiết kế

Dưới đây là cấu trúc các đối tượng chính trong hệ thống:

<details>
<summary><strong>👤 Người dùng (User)</strong></summary>

**Thuộc tính:**
- `userId`: ID người dùng (Khóa chính)
- `username`: Tên đăng nhập
- `password`: Mật khẩu (đã được mã hóa)
- `fullName`: Họ và tên
- `email`: Email liên hệ
- `phoneNumber`: Số điện thoại
- `role`: Vai trò (`ADMIN`, `MANAGER`, `STAFF`)
- `isActive`: Trạng thái hoạt động
- `createdDate`: Ngày tạo tài khoản

**Phương thức:**
- `login()`: Đăng nhập vào hệ thống
- `logout()`: Đăng xuất khỏi hệ thống
- `updateProfile()`: Cập nhật thông tin cá nhân
- `changePassword()`: Thay đổi mật khẩu

</details>

<details>
<summary><strong>📦 Sản phẩm (Product)</strong></summary>

**Thuộc tính:**
- `productId`: ID sản phẩm (Khóa chính)
- `productName`: Tên sản phẩm
- `description`: Mô tả sản phẩm
- `importPrice`: Giá nhập
- `salePrice`: Giá bán
- `stockQuantity`: Số lượng tồn kho
- `categoryId`: ID danh mục sản phẩm
- `barcode`: Mã vạch sản phẩm
- `unit`: Đơn vị tính (cái, kg, lít...)
- `createdDate`: Ngày tạo sản phẩm
- `isActive`: Trạng thái hoạt động

**Phương thức:**
- `addProduct()`: Thêm sản phẩm mới
- `updateProduct()`: Cập nhật thông tin sản phẩm
- `deleteProduct()`: Xóa sản phẩm
- `searchProduct()`: Tìm kiếm sản phẩm
- `checkStock()`: Kiểm tra tồn kho

</details>

<details>
<summary><strong>🛒 Đơn hàng (Order)</strong></summary>

**Thuộc tính:**
- `orderId`: ID đơn hàng (Khóa chính)
- `customerId`: ID khách hàng
- `staffId`: ID nhân viên tạo đơn
- `orderDate`: Ngày tạo đơn hàng
- `totalAmount`: Tổng tiền trước thuế
- `taxAmount`: Tiền thuế
- `discountAmount`: Tiền giảm giá
- `finalAmount`: Tổng tiền cuối cùng
- `status`: Trạng thái (`PENDING`, `CONFIRMED`, `CANCELLED`, `COMPLETED`)
- `orderItems`: Danh sách sản phẩm trong đơn hàng

**Phương thức:**
- `createOrder()`: Tạo đơn hàng mới
- `addItem()`: Thêm sản phẩm vào đơn hàng
- `removeItem()`: Xóa sản phẩm khỏi đơn hàng
- `updateQuantity()`: Cập nhật số lượng sản phẩm
- `calculateTotal()`: Tính tổng tiền đơn hàng
- `cancelOrder()`: Hủy đơn hàng

</details>

<details>
<summary><strong>💳 Thanh toán (Payment)</strong></summary>

**Thuộc tính:**
- `paymentId`: ID thanh toán (Khóa chính)
- `orderId`: ID đơn hàng (Khóa ngoại)
- `paymentMethod`: Phương thức thanh toán (`CASH`, `BANK_TRANSFER`, `QR_CODE`, `CREDIT_CARD`)
- `amount`: Số tiền thanh toán
- `paidAmount`: Số tiền đã trả
- `changeAmount`: Tiền thừa trả lại
- `paymentDate`: Ngày thanh toán
- `status`: Trạng thái (`PENDING`, `COMPLETED`, `FAILED`, `REFUNDED`)
- `transactionId`: Mã giao dịch (nếu có)
- `note`: Ghi chú thanh toán

**Phương thức:**
- `processPayment()`: Xử lý thanh toán
- `refundPayment()`: Hoàn tiền
- `validatePayment()`: Xác thực thanh toán
- `generateReceipt()`: Tạo hóa đơn thanh toán

</details>

<details>
<summary><strong>📊 Kho hàng (Inventory)</strong></summary>

**Thuộc tính:**
- `inventoryId`: ID kho hàng (Khóa chính)
- `productId`: ID sản phẩm (Khóa ngoại)
- `currentStock`: Số lượng hiện tại
- `minStock`: Số lượng tối thiểu
- `maxStock`: Số lượng tối đa
- `lastUpdated`: Ngày cập nhật cuối
- `location`: Vị trí trong kho
- `supplierId`: ID nhà cung cấp
- `costPrice`: Giá vốn trung bình

**Phương thức:**
- `updateStock()`: Cập nhật số lượng tồn kho
- `checkLowStock()`: Kiểm tra hàng sắp hết
- `generateStockReport()`: Tạo báo cáo tồn kho
- `importGoods()`: Nhập hàng vào kho
- `exportGoods()`: Xuất hàng từ kho
- `stockTaking()`: Kiểm kê hàng hóa

</details>

---

