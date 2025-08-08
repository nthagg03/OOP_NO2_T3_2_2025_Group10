import java.util.*;

public class UserActivitySimulation {
    static Scanner scanner = new Scanner(System.in);
    static boolean loggedIn = false;
    static List<String> cart = new ArrayList<>();

    public static void main(String[] args) {
        start();
    }

    static void start() {
        System.out.println("=== Welcome to Sales Management System ===");
        login();
        if (loggedIn) {
            viewDashboard();
            logout();
        } else {
            System.out.println("Login failed. Exiting system.");
        }
    }

    static void login() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals("admin") && password.equals("123")) {
                loggedIn = true;
                System.out.println("Login successful.\n");
                return;
            } else {
                System.out.println("Invalid credentials. Try again.\n");
                attempts++;
            }
        }
    }

    static void viewDashboard() {
        while (true) {
            System.out.println("\n--- Dashboard ---");
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. Place Order");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    reviewCart();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void viewProducts() {
        List<String> products = List.of("Tea", "Coffee", "Milk", "Bread");
        System.out.println("\n-- Product List --");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, products.get(i));
        }
        System.out.print("Select a product to add to cart (0 to go back): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice > 0 && choice <= products.size()) {
            cart.add(products.get(choice - 1));
            System.out.println(products.get(choice - 1) + " added to cart.");
        }
    }

    static void reviewCart() {
        System.out.println("\n-- Your Cart --");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (String item : cart) {
                System.out.println("- " + item);
            }
        }
    }

    static void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty. Add items before placing an order.");
            return;
        }

        System.out.println("\n-- Place Order --");
        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Bank Transfer");
        System.out.println("3. QR Code");
        System.out.print("Your choice: ");
        String method = scanner.nextLine();

        System.out.println("\nOrder placed successfully using payment method: " + method);
        cart.clear();
    }

    static void logout() {
        System.out.println("\nLogging out... Goodbye!");
    }
}
