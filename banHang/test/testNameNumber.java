public class testNameNumber {
    public static void test() {
        NameNumber nameNumber1 = new NameNumber("Nguyen Van A", "0123456789");
        NameNumber nameNumber2 = new NameNumber("Tran Thi B", "0987654321");
        
        System.out.println("Test NameNumber:");
        System.out.println("Ten: " + nameNumber1.getLastName() + ", So dien thoai: " + nameNumber1.getTelNumber());
        System.out.println("Ten: " + nameNumber2.getLastName() + ", So dien thoai: " + nameNumber2.getTelNumber());
        
        NameNumber nameNumber3 = new NameNumber();
        System.out.println("Tao doi tuong khong tham so thanh cong");
    }
}
