public class TestTime {
    public static void test() {
        Time t = new Time(1, 59, 50);
        System.out.println("Current time: " + t);
        t.addSeconds(15);
        System.out.println("After adding 15 seconds: " + t);
    }
}
