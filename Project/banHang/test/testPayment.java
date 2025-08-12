public class testPayment {
	public static void test() {
		Payment pay1 = new Payment("PM001","O002",96000d,"2025-08-12","CASH","PENDING");
		Payment pay2 = new Payment("PM002","O003",125000d,"2025-08-12","CARD","DONE");
		pay1.displayPaymentInfo();
		pay2.displayPaymentInfo();
		pay1.setStatus("DONE");
		System.out.println("--- Sau cập nhật trạng thái PM001 ---");
		pay1.displayPaymentInfo();
	}
}
