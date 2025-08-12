import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {
    private String orderId;
    private String customerId; 
    private String productId;
    private int quantity;
    private double totalPrice;
    private String orderDate;
    private String status;

    // Danh sách đơn hàng
    private static List<Order> orderList = new ArrayList<>();

    public Order(String orderId, String customerId, String productId, int quantity, double totalPrice, String orderDate, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Thêm đơn hàng mới
    public static void addOrder(Order order) {
        try {
            if (order == null || order.customerId == null || order.customerId.isEmpty()) {
                throw new IllegalArgumentException("Mã khách hàng không được để trống.");
            }
            if (order.productId == null || order.productId.isEmpty()) {
                throw new IllegalArgumentException("Mã sản phẩm không được để trống.");
            }
            if (order.quantity <= 0) {
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
            }
            orderList.add(order);
            System.out.println("✅ Đã thêm đơn hàng: " + order.orderId);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi thêm đơn hàng: " + e.getMessage());
        }
    }

    // Hiển thị toàn bộ đơn hàng
    public static void displayAllOrders() {
        System.out.println("\n===== DANH SÁCH ĐƠN HÀNG =====");
        if (orderList.isEmpty()) {
            System.out.println("Hiện chưa có đơn hàng nào.");
            return;
        }
        for (Order o : orderList) {
            o.displayOrderInfo();
            System.out.println("--------------------");
        }
    }

    // Hiển thị chi tiết đơn hàng
    public void displayOrderInfo() {
        System.out.println("Mã đơn hàng: " + orderId);
        System.out.println("Mã khách hàng: " + customerId);
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Số lượng: " + quantity);
        System.out.println("Tổng tiền: " + totalPrice + " VND");
        System.out.println("Ngày đặt: " + orderDate);
        System.out.println("Trạng thái: " + status);
    }

    // Cập nhật đơn hàng
    public static void updateOrder(String orderId, String newCustomerId, String newProductId, Integer newQuantity, Double newTotalPrice, String newOrderDate, String newStatus) {
        try {
            boolean found = false;
            for (Order o : orderList) {
                if (o.orderId.equals(orderId)) {
                    if (newCustomerId != null && !newCustomerId.isEmpty()) o.customerId = newCustomerId;
                    if (newProductId != null && !newProductId.isEmpty()) o.productId = newProductId;
                    if (newQuantity != null && newQuantity > 0) o.quantity = newQuantity;
                    if (newTotalPrice != null && newTotalPrice > 0) o.totalPrice = newTotalPrice;
                    if (newOrderDate != null && !newOrderDate.isEmpty()) o.orderDate = newOrderDate;
                    if (newStatus != null && !newStatus.isEmpty()) o.status = newStatus;
                    System.out.println("✅ Đã cập nhật đơn hàng: " + orderId);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("⚠ Không tìm thấy đơn hàng có ID: " + orderId);
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi cập nhật đơn hàng: " + e.getMessage());
        }
    }

    // Xóa đơn hàng
    public static void deleteOrder(String orderId) {
        try {
            Iterator<Order> iterator = orderList.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
                Order o = iterator.next();
                if (o.orderId.equals(orderId)) {
                    iterator.remove();
                    System.out.println("🗑 Đã xóa đơn hàng: " + o.orderId);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("⚠ Không tìm thấy đơn hàng để xóa.");
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xóa đơn hàng: " + e.getMessage());
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }
}
