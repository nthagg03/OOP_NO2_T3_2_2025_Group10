import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu {
    private String menuId;
    private String itemName;
    private double price;
    private String description;

    // Danh sách các món trong menu (static để quản lý chung)
    private static List<Menu> menuList = new ArrayList<>();

    public Menu(String menuId, String itemName, double price, String description) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }

    // Thêm món vào menu
    public static void addMenuItem(Menu menuItem) {
        try {
            if (menuItem == null || menuItem.itemName == null || menuItem.itemName.isEmpty()) {
                throw new IllegalArgumentException("Tên món không được để trống.");
            }
            menuList.add(menuItem);
            System.out.println("✅ Đã thêm món: " + menuItem.itemName);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi thêm món: " + e.getMessage());
        }
    }

    // Hiển thị toàn bộ menu
    public static void displayMenu() {
        System.out.println("\n===== DANH SÁCH MENU =====");
        if (menuList.isEmpty()) {
            System.out.println("Menu hiện đang trống.");
            return;
        }
        for (Menu m : menuList) {
            System.out.println("ID: " + m.menuId);
            System.out.println("Tên món: " + m.itemName);
            System.out.println("Giá: " + m.price + " VND");
            System.out.println("Mô tả: " + m.description);
            System.out.println("--------------------");
        }
    }

    // Cập nhật thông tin món
    public static void updateMenuItem(String menuId, String newName, double newPrice, String newDesc) {
        try {
            boolean found = false;
            for (Menu m : menuList) {
                if (m.menuId.equals(menuId)) {
                    if (newName != null && !newName.isEmpty()) m.itemName = newName;
                    if (newPrice > 0) m.price = newPrice;
                    if (newDesc != null && !newDesc.isEmpty()) m.description = newDesc;
                    found = true;
                    System.out.println("✅ Đã cập nhật món: " + menuId);
                    break;
                }
            }
            if (!found) {
                System.out.println("⚠ Không tìm thấy món có ID: " + menuId);
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi cập nhật món: " + e.getMessage());
        }
    }

    // Xóa món khỏi menu
    public static void deleteMenuItem(String menuId) {
        try {
            Iterator<Menu> iterator = menuList.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
                Menu m = iterator.next();
                if (m.menuId.equals(menuId)) {
                    iterator.remove();
                    found = true;
                    System.out.println("🗑 Đã xóa món: " + m.itemName);
                    break;
                }
            }
            if (!found) {
                System.out.println("⚠ Không tìm thấy món để xóa.");
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xóa món: " + e.getMessage());
        }
    }

    // Getter và Setter
    public String getMenuId() {
        return menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
