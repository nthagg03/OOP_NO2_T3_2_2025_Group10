public class TestUser {
    public static void test() {
        User.setUser("Phone Luong", "Xa Dan");
        System.out.println("User Name: " + User.getUserName());
        System.out.println("User Address: " + User.getUserAddress());
    }
}