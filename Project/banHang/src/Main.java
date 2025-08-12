public class Main {
   public static void main(String[] args) {

    try {
        testPayment.test();
    } catch (Exception e) {
        System.err.println("Lỗi ở testPayment.test(): " + e.getMessage());
    }

    try {
        testTime.test();
    } catch (Exception e) {
        System.err.println("Lỗi ở testTime.test(): " + e.getMessage());
    }

    try {
        testUser.test();
    } catch (Exception e) {
        System.err.println("Lỗi ở testUser.test(): " + e.getMessage());
    }

    try {
        testUser us = new testUser();
        us.testEdit();
    } catch (Exception e) {
        System.err.println("Lỗi ở us.testEdit(): " + e.getMessage());
    }

    try {
        testUser.testRegisterUser();
    } catch (Exception e) {
        System.err.println("Lỗi ở testUser.testRegisterUser(): " + e.getMessage());
    }

    try {
        testProduct.testProductInput();
    } catch (Exception e) {
        System.err.println("Lỗi ở testProduct.testProductInput(): " + e.getMessage());
    }

    try {
        testOrder.test();;
    } catch (Exception e) {
        System.err.println("Lỗi ở testOrder.test(): " + e.getMessage());
    }

    try {
        Menu.displayMenu();
    } catch (Exception e) {
        System.err.println("Lỗi ở Menu.displayMenu(): " + e.getMessage());
    }

    try {
        testCustomerOrder.test();
    } catch (Exception e) {
        System.err.println("Lỗi ở testCustomerOrder.test(): " + e.getMessage());
    }
}

}