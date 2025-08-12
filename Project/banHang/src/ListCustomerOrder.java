import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListCustomerOrder {
    private final List<CustomerOrder> orders = new ArrayList<>();

    public List<CustomerOrder> add(CustomerOrder order) {
        if (order == null) throw new IllegalArgumentException("Order null");
        orders.add(order);
        return Collections.unmodifiableList(orders);
    }

    public List<CustomerOrder> getAll() { return Collections.unmodifiableList(orders); }

    public void display() {
        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống.");
            return;
        }
        for (CustomerOrder o : orders) System.out.println(o);
    }

    public static void display(List<CustomerOrder> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách đầu vào trống hoặc null.");
            return;
        }
        for (CustomerOrder o : list) System.out.println(o);
    }

    public void shortList() {
        int size = Math.min(2, orders.size());
        display(orders.subList(0, size));
    }

    public List<CustomerOrder> filterByUserId(String userId) {
        List<CustomerOrder> result = new ArrayList<>();
        if (userId == null || userId.isEmpty()) return result;
        for (CustomerOrder o : orders) if (o.getUserId().equals(userId)) result.add(o);
        return result;
    }

    public List<CustomerOrder> getPaidOrdersByUser(String userId) {
        List<CustomerOrder> result = new ArrayList<>();
        if (userId == null || userId.isEmpty()) return result;
        for (CustomerOrder o : orders) if (o.getUserId().equals(userId) && o.isPaid()) result.add(o);
        return result;
    }
}