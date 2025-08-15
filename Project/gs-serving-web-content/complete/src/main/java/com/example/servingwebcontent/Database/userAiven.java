
package com.example.servingwebcontent.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.servingwebcontent.Model.User;
import com.example.servingwebcontent.Model.User.Gender;
import com.example.servingwebcontent.Model.User.UserType;

@Service
public class userAiven {

    @Autowired
    private myConnection myConnection;

    public ArrayList<User> userAivenList() {
        ArrayList<User> items = new ArrayList<>();
        try (Connection conn = myConnection.getConnection();
             Statement sta = conn.createStatement();
             ResultSet setdata = sta.executeQuery("SELECT * FROM Users")) {

            while (setdata.next()) {
                User user = new User();
                user.setUser(setdata.getString("userID"), setdata.getString("name"), Gender.valueOf(setdata.getString("gender")),
                            setdata.getString("birthDate"), setdata.getString("phoneNumber"), setdata.getString("email"),
                            setdata.getString("address"), UserType.valueOf(setdata.getString("userType")));
                items.add(user);
            }

        } catch (Exception e) {
            System.out.println("Error in database connection:");
            e.printStackTrace();
        }

        return items;
    }

    public User getUserById(String id) {
        User user = new User();
        try (Connection conn = myConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE userID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet setdata = pst.executeQuery();

            if (setdata.next()) {
                user.setUser(setdata.getString("userID"), setdata.getString("name"), Gender.valueOf(setdata.getString("gender")),
                            setdata.getString("birthDate"), setdata.getString("phoneNumber"), setdata.getString("email"),
                            setdata.getString("address"), UserType.valueOf(setdata.getString("userType")));
            }
        } catch (Exception e) {
            System.out.println("Error in getUserById:");
            e.printStackTrace();
        }
        return user;
    }

    // Cập nhật thông tin User
    public void updateUser(User u) {
        try (Connection conn = myConnection.getConnection()) {
            String sql = "UPDATE Users SET name=?, gender=?, birthDate=?, phoneNumber=?, email=?, address=?, userType=? WHERE userID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getName());
            pst.setString(2, u.getGender().name());
            pst.setString(3, u.getBirthDate());
            pst.setString(4, u.getPhoneNumber());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getAddress());
            pst.setString(7, u.getUserType().name());
            pst.setString(8, u.getUserId());

            pst.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (Exception e) {
            System.out.println("Error in updateUser:");
            e.printStackTrace();
        }
    }

    // Xóa User
    public void deleteUser(String id) {
        try (Connection conn = myConnection.getConnection()) {
            String sql = "DELETE FROM Users WHERE userID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in deleteUser:");
            e.printStackTrace();
        }
    }

    public void addUser(User u) {
        try (Connection conn = myConnection.getConnection()) {
            String sql = "INSERT INTO Users (name, gender, birthDate, phoneNumber, email, address, password, userType) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getName());
            pst.setString(2, u.getGender().name());
            pst.setString(3, u.getBirthDate());
            pst.setString(4, u.getPhoneNumber());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getAddress());
            pst.setString(7, u.getPassword());
            pst.setString(8, u.getUserType().name());

            pst.executeUpdate();
            System.out.println("User added successfully.");
        } catch (Exception e) {
            System.out.println("Error in addUser:");
            e.printStackTrace();
        }
    }

    public boolean register(User user) {
        try (Connection conn = myConnection.getConnection()) {
            // Kiểm tra email đã tồn tại chưa
            String checkSql = "SELECT COUNT(*) FROM Users WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, user.getEmail());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false; // Email đã tồn tại
            }

            // Thêm người dùng
            String sql = "INSERT INTO Users (name, gender, birthDate, phoneNumber, email, address, password, userType) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getGender().name());
            pst.setString(3, user.getBirthDate());
            pst.setString(4, user.getPhoneNumber());
            pst.setString(5, user.getEmail());
            pst.setString(6, user.getAddress());
            pst.setString(7, user.getPassword());
            pst.setString(8, user.getUserType().name());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error in register:");
            e.printStackTrace();
            return false;
        }
    }

    public User login(String email, String password) {
        try (Connection conn = myConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUser(rs.getString("userID"), rs.getString("name"),
                            Gender.valueOf(rs.getString("gender")),
                            rs.getString("birthDate"), rs.getString("phoneNumber"),
                            rs.getString("email"), rs.getString("address"),
                            UserType.valueOf(rs.getString("userType")));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (Exception e) {
            System.out.println("Error in login:");
            e.printStackTrace();
        }
        return null;
    }

    // Helpers for seeding and admin tasks
    public User getByEmail(String email) {
        try (Connection conn = myConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUser(rs.getString("userID"), rs.getString("name"),
                             Gender.valueOf(rs.getString("gender")),
                             rs.getString("birthDate"), rs.getString("phoneNumber"),
                             rs.getString("email"), rs.getString("address"),
                             UserType.valueOf(rs.getString("userType")));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error in getByEmail:");
            e.printStackTrace();
        }
        return null;
    }

    public void updatePasswordByEmail(String email, String newPassword) {
        try (Connection conn = myConnection.getConnection()) {
            String sql = "UPDATE Users SET password = ? WHERE email = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newPassword);
            pst.setString(2, email);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in updatePasswordByEmail:");
            e.printStackTrace();
        }
    }

}
