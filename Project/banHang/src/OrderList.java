import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderList {
	private final List<Order> orders = new ArrayList<>();

	public List<Order> add(Order order) {
		if (order == null) throw new IllegalArgumentException("Order null");
		orders.add(order);
		return Collections.unmodifiableList(orders);
	}

	public List<Order> remove(String orderId) {
		orders.removeIf(o -> o.getOrderId().equals(orderId));
		return Collections.unmodifiableList(orders);
	}

	public Order findById(String orderId) {
		for (Order o : orders) if (o.getOrderId().equals(orderId)) return o;
		return null;
	}

	public List<Order> getAll() { return Collections.unmodifiableList(orders); }

	public void printAll() { Order.displayAllOrders(); }
}
