public class User {
    private static String userName;
    private static String userAddress;

    public static String getUserName() { return userName; }
    public static String getUserAddress() { return userAddress; }
    public static void setUser(String userName, String userAddress) { 
        User.userName = userName; 
        User.userAddress = userAddress; 
    }
}