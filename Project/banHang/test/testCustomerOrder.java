public class testCustomerOrder {
	public static void test() {
		ListCustomerOrder list = new ListCustomerOrder();
		CustomerOrder c1 = new CustomerOrder("KH001","O100",false);
		CustomerOrder c2 = new CustomerOrder("KH001","O101",true);
		CustomerOrder c3 = new CustomerOrder("KH002","O102",true);
		list.add(c1);
		list.add(c2);
		list.add(c3);
		System.out.println("--- Tất cả ---");
		list.display();
		System.out.println("--- Đơn KH001 ---");
		ListCustomerOrder.display(list.filterByUserId("KH001"));
		System.out.println("--- Đơn đã thanh toán KH001 ---");
		ListCustomerOrder.display(list.getPaidOrdersByUser("KH001"));
	}
}
