public class TestUser {
    public static void test() {
        User user = new User("Phone Luong", "vp03122005@gmail.com", 20);
        System.out.println(user.getName());
        user.setAge(21);
        System.out.println("Updated age: " + user.getAge());
    }
}