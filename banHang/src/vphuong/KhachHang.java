package vphuong;
public class Customer {
    private String customerId;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer(String customerId, String name, String phone, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
