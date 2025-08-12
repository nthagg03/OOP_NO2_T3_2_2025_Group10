import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class testUser {
    public static void test() {
        try {
            Customer customer_1 = new Customer("KH001", "Vũ Văn Phương", "Nam", "2005-03-06",
                    "0336363636", "ramau@gmail.com", "Hà Nội", "123456");
            Seller seller_1 = new Seller("NC001", "Trịnh Trần Phương Tuấn", "Nam", "1997-12-06",
                    "0912345678", "j97@gmail.com", "Bến Tre", "abcdef");

            System.out.println("== Thông tin Khách hàng ==");
            customer_1.showUser();
            System.out.println("\n== Thông tin Nhà cung cấp ==");
            seller_1.showUser();

            System.out.println("\n== Kiểm tra đăng nhập ==");
            System.out.println("Customer login (số điện thoại): " + customer_1.login("0336363636", "123456"));
            System.out.println("Seller login (email): " + seller_1.login("j97@gmail.com", "abcdef"));

            System.out.println("\n== Giao dịch ==");
            customer_1.buy("Capuchino");
            seller_1.sell("Americano");
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi xử lý thông tin người dùng: " + e.getMessage());
        }
    }

    ArrayList<User> sl = new ArrayList<User>();

    public ArrayList<User> addList() {
        sl.clear(); // tránh thêm trùng nếu gọi nhiều lần
        sl.add(new User("KH001", "Vũ Văn Phương", "Nam", "2005-03-06", "0336363636",
                "ramau@gmail.com", "Hà Nội", "123456", "Khách hàng"));
        sl.add(new User("NC001", "Trịnh Trần Phương Tuấn", "Nam", "1997-12-06", "0912345678",
                "j97@gmail.com", "Bến Tre", "abcdef", "Khách hàng"));
        sl.add(new User("NC001", "Nguyễn Xuân Thắng", "Nam", "2006-30-01", "0981818118",
                "oop@gmail.com", "Thanh Hóa", "363636", "Nhà cung cấp"));
        return sl;
    }

    public void testEdit() {

        ArrayList<User> sl = addList();
        UserList userList = new UserList();
        for (User u : sl) {
            userList.addUser(u);
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Nhập User ID cần sửa: ");
            String s = sc.nextLine().trim();

            System.out.print("Nhập tên mới: ");
            String newName = sc.nextLine().trim();

            userList.getEditUser(newName, s);
        } catch (Exception e) {
            System.out.println("❌ Đã xảy ra lỗi khi sửa thông tin: " + e.getMessage());
        }

        userList.printUserList();
    }

    public void testDelete(UserList userList, String UserID) {
        try {
            userList.getDeleteUser(UserID);
            userList.printUserList();
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi xóa người dùng: " + e.getMessage());
        }
    }

    public static void testRegisterUser() {
        System.out.println("\n== Đăng ký ==");

        ArrayList<User> sl = new ArrayList<>();
        User s1 = new User();

        try {
            s1.registerUser();
            sl.add(s1);

            UserList userList = new UserList();
            userList.addUser(s1);

            System.out.println("\n== Danh sách người dùng ==");
            userList.printUserList();
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi đăng ký người dùng: " + e.getMessage());
        }
    }
}