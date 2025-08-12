public class testOrder {
	public static void test() {
		Order o1 = new Order("O001","KH001","P001",2,50000,"2025-08-12","NEW");
		Order o2 = new Order("O002","KH001","P002",1,32000,"2025-08-12","NEW");
		Order.addOrder(o1);
		Order.addOrder(o2);
		System.out.println("--- Sau khi thêm ---");
		Order.displayAllOrders();

		Order.updateOrder("O002",null,null,3,96000d,null,"UPDATED");
		System.out.println("--- Sau cập nhật O002 ---");
		Order.displayAllOrders();

		Order.deleteOrder("O001");
		System.out.println("--- Sau khi xóa O001 ---");
		Order.displayAllOrders();
	}
}
