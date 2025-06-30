public class testDH {
    public static void test() {
        // Create a new order
        String orderId = "DH001";
        String customerName = "Phone Luong";
        String product = "Laptop";
        int quantity = 2;
        double price = 1500.00;

        // Simulate creating an order
        System.out.println("Creating order: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Product: " + product);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: $" + price);

        // Test case 2: Update an existing order
        quantity = 3; // Update quantity
        System.out.println("Updating order: " + orderId);
        System.out.println("New Quantity: " + quantity);

        // Test case 3: Cancel an order
        System.out.println("Cancelling order: " + orderId);
    }
}
