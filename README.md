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
<summary><strong>1. Người dùng (User)</strong></summary>

- `userId`: ID người dùng (Khóa chính)
- `username`: Tên đăng nhập
- `password`: Mật khẩu (đã được mã hóa)
- `fullName`: Họ và tên
- `role`: Vai trò (ví dụ: `ADMIN`, `STAFF`)

</details>

<details>
<summary><strong>2. Sản phẩm (Product)</strong></summary>

- `productId`: ID sản phẩm (Khóa chính)
- `productName`: Tên sản phẩm
- `importPrice`: Giá nhập
- `salePrice`: Giá bán
- `stockQuantity`: Số lượng tồn kho
- `categoryId`: ID danh mục (Khóa ngoại)

</details>

<details>
<summary><strong>3. Danh mục sản phẩm (Category)</strong></summary>

- `categoryId`: ID danh mục (Khóa chính)
- `categoryName`: Tên danh mục

</details>

<details>
<summary><strong>4. Đơn hàng (Order)</strong></summary>

- `orderId`: ID đơn hàng (Khóa chính)
- `orderDate`: Ngày tạo đơn hàng
- `totalAmount`: Tổng tiền
- `paymentMethod`: Hình thức thanh toán (`CASH`, `TRANSFER`, `QR_CODE`)
- `status`: Trạng thái (`PENDING`, `PAID`, `CANCELLED`, `RETURNED`)

</details>

<details>
<summary><strong>5. Chi tiết Đơn hàng (OrderDetail)</strong></summary>

- `orderDetailId`: ID chi tiết đơn hàng (Khóa chính)
- `orderId`: ID đơn hàng (Khóa ngoại)
- `productId`: ID sản phẩm (Khóa ngoại)
- `quantity`: Số lượng
- `unitPrice`: Đơn giá tại thời điểm bán
- `discount`: Giảm giá (nếu có)

</details>

<details>
<summary><strong>6. Phiếu nhập hàng (GoodsReceipt)</strong></summary>

- `receiptId`: ID phiếu nhập (Khóa chính)
- `receiptDate`: Ngày nhập
- `staffId`: ID nhân viên thực hiện (Khóa ngoại)
- `totalValue`: Tổng giá trị nhập

</details>

<details>
<summary><strong>7. Chi tiết Phiếu nhập (ReceiptDetail)</strong></summary>

- `receiptDetailId`: ID chi tiết phiếu nhập (Khóa chính)
- `receiptId`: ID phiếu nhập (Khóa ngoại)
- `productId`: ID sản phẩm (Khóa ngoại)
- `quantity`: Số lượng
- `importPrice`: Giá nhập tại thời điểm nhập

</details>

---
