import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerOrder {
    private String userId;
    private String orderId;
    private boolean paid;

    private static final List<CustomerOrder> ORDERS = new ArrayList<>();

    public CustomerOrder(String userId, String orderId, boolean paid) {
        if (userId == null || userId.isEmpty()) throw new IllegalArgumentException("UserId không hợp lệ");
        if (orderId == null || orderId.isEmpty()) throw new IllegalArgumentException("OrderId không hợp lệ");
        this.userId = userId;
        this.orderId = orderId;
        this.paid = paid;
    }

    public String getUserId() { return userId; }
    public String getOrderId() { return orderId; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public void displayCustomerOrder() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "User ID: " + userId + " | Order ID: " + orderId + " | Thanh toán: " + (paid ? "Đã thanh toán" : "Chưa thanh toán");
    }

    // Static helpers
    public static void add(CustomerOrder order) {
        if (order == null) throw new IllegalArgumentException("Order null");
        ORDERS.add(order);
    }

    public static List<CustomerOrder> getAll() { return Collections.unmodifiableList(ORDERS); }
    public static List<CustomerOrder> getByUser(String userId) {
        List<CustomerOrder> result = new ArrayList<>();
        for (CustomerOrder o : ORDERS) if (o.userId.equals(userId)) result.add(o);
        return result;
    }
    public static List<CustomerOrder> getPaidByUser(String userId) {
        List<CustomerOrder> result = new ArrayList<>();
        for (CustomerOrder o : ORDERS) if (o.userId.equals(userId) && o.paid) result.add(o);
        return result;
    }
}