public class Main {
    public static void main(String[] args) {
    try {
        testUser.test();
    } catch (Exception e) {
        System.err.println("Lỗi ở testUser.test(): " + e.getMessage());
    }
}
}