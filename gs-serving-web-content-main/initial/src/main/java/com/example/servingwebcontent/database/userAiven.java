package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.User;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class userAiven {
    
    private static List<User> users = new ArrayList<>();
    private static AtomicLong idGenerator = new AtomicLong(1);
    
    static {
        // Khởi tạo dữ liệu mẫu
        initSampleData();
    }
    
    private static void initSampleData() {
        User user1 = new User("KH001", "Nguyễn Văn An", "0123456789", "an@example.com", "123 Đường ABC, TP.HCM");
        user1.setId(idGenerator.getAndIncrement());
        users.add(user1);
        
        User user2 = new User("KH002", "Trần Thị Bình", "0987654321", "binh@example.com", "456 Đường XYZ, Hà Nội");
        user2.setId(idGenerator.getAndIncrement());
        users.add(user2);
        
        User user3 = new User("KH003", "Lê Văn Cường", "0369852147", "cuong@example.com", "789 Đường DEF, Đà Nẵng");
        user3.setId(idGenerator.getAndIncrement());
        users.add(user3);
    }
    
    // CREATE - Thêm user mới
    public User saveUser(User user) {
        // Kiểm tra mã khách hàng đã tồn tại
        if (findByMaKhachHang(user.getMaKhachHang()) != null && user.getId() == null) {
            throw new RuntimeException("Mã khách hàng đã tồn tại: " + user.getMaKhachHang());
        }
        
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
            users.add(user);
        } else {
            updateUser(user);
        }
        return user;
    }
    
    // READ - Lấy tất cả users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    // READ - Tìm user theo ID
    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    // READ - Tìm user theo mã khách hàng
    public User findByMaKhachHang(String maKhachHang) {
        return users.stream()
                .filter(user -> user.getMaKhachHang().equals(maKhachHang))
                .findFirst()
                .orElse(null);
    }
    
    // READ - Tìm kiếm users
    public List<User> searchUsers(String keyword) {
        return users.stream()
                .filter(user -> 
                    user.getTenKhachHang().toLowerCase().contains(keyword.toLowerCase()) ||
                    user.getMaKhachHang().toLowerCase().contains(keyword.toLowerCase()) ||
                    (user.getEmail() != null && user.getEmail().toLowerCase().contains(keyword.toLowerCase()))
                )
                .toList();
    }
    
    // READ - Tìm users theo tên
    public List<User> findByTenKhachHangContaining(String tenKhachHang) {
        return users.stream()
                .filter(user -> user.getTenKhachHang().toLowerCase().contains(tenKhachHang.toLowerCase()))
                .toList();
    }
    
    // UPDATE - Cập nhật user
    public User updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(updatedUser.getId())) {
                // Kiểm tra mã khách hàng trùng lặp (trừ chính nó)
                User existingUser = findByMaKhachHang(updatedUser.getMaKhachHang());
                if (existingUser != null && !existingUser.getId().equals(updatedUser.getId())) {
                    throw new RuntimeException("Mã khách hàng đã tồn tại: " + updatedUser.getMaKhachHang());
                }
                
                updatedUser.setCreatedAt(user.getCreatedAt()); // Giữ nguyên created_at
                users.set(i, updatedUser);
                return updatedUser;
            }
        }
        throw new RuntimeException("User không tồn tại với ID: " + updatedUser.getId());
    }
    
    // DELETE - Xóa user
    public void deleteUser(Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (!removed) {
            throw new RuntimeException("User không tồn tại với ID: " + id);
        }
    }
    
    // UTILITY - Đếm số lượng users
    public long count() {
        return users.size();
    }
    
    // UTILITY - Kiểm tra user tồn tại
    public boolean existsById(Long id) {
        return users.stream().anyMatch(user -> user.getId().equals(id));
    }
    
    // UTILITY - Kiểm tra mã khách hàng tồn tại
    public boolean existsByMaKhachHang(String maKhachHang) {
        return users.stream().anyMatch(user -> user.getMaKhachHang().equals(maKhachHang));
    }
}
