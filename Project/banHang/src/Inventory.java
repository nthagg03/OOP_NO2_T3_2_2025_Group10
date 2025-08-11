import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {
    private String inventoryId;
    private String productId;
    private int currentStock;
    private int minStock;
    private int maxStock;
    private String location;

    // Danh sách kho hàng
    private static List<Inventory> inventoryList = new ArrayList<>();

    public Inventory(String inventoryId, String productId, int currentStock, int minStock, int maxStock, String location) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.location = location;
    }

    // Thêm kho mới
    public static void addInventory(Inventory inventory) {
        try {
            if (inventory == null || inventory.productId == null || inventory.productId.isEmpty()) {
                throw new IllegalArgumentException("Mã sản phẩm không được để trống.");
            }
            inventoryList.add(inventory);
            System.out.println("✅ Đã thêm kho: " + inventory.inventoryId);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi thêm kho: " + e.getMessage());
        }
    }

    // Hiển thị toàn bộ kho hàng
    public static void displayAllInventories() {
        System.out.println("\n===== DANH SÁCH KHO HÀNG =====");
        if (inventoryList.isEmpty()) {
            System.out.println("Kho hàng hiện đang trống.");
            return;
        }
        for (Inventory inv : inventoryList) {
            inv.displayInventoryInfo();
            System.out.println("--------------------");
        }
    }

    // Hiển thị thông tin kho cụ thể
    public void displayInventoryInfo() {
        System.out.println("Mã kho: " + inventoryId);
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Số lượng tồn: " + currentStock);
        System.out.println("Tồn tối thiểu: " + minStock);
        System.out.println("Tồn tối đa: " + maxStock);
        System.out.println("Vị trí kho: " + location);
    }

    // Cập nhật thông tin kho
    public static void updateInventory(String inventoryId, String newProductId, Integer newStock, Integer newMin, Integer newMax, String newLocation) {
        try {
            boolean found = false;
            for (Inventory inv : inventoryList) {
                if (inv.inventoryId.equals(inventoryId)) {
                    if (newProductId != null && !newProductId.isEmpty()) inv.productId = newProductId;
                    if (newStock != null && newStock >= 0) inv.currentStock = newStock;
                    if (newMin != null && newMin >= 0) inv.minStock = newMin;
                    if (newMax != null && newMax >= 0) inv.maxStock = newMax;
                    if (newLocation != null && !newLocation.isEmpty()) inv.location = newLocation;
                    System.out.println("✅ Đã cập nhật kho: " + inventoryId);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("⚠ Không tìm thấy kho có ID: " + inventoryId);
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi cập nhật kho: " + e.getMessage());
        }
    }

    // Xóa kho
    public static void deleteInventory(String inventoryId) {
        try {
            Iterator<Inventory> iterator = inventoryList.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
                Inventory inv = iterator.next();
                if (inv.inventoryId.equals(inventoryId)) {
                    iterator.remove();
                    System.out.println("🗑 Đã xóa kho: " + inv.inventoryId);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("⚠ Không tìm thấy kho để xóa.");
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xóa kho: " + e.getMessage());
        }
    }

    // Các phương thức nghiệp vụ
    public void importGoods(int quantity) {
        try {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Số lượng nhập phải lớn hơn 0.");
            }
            currentStock += quantity;
            if (currentStock > maxStock) {
                System.out.println("⚠ Cảnh báo: Số lượng tồn vượt quá mức tối đa!");
            }
            System.out.println("✅ Đã nhập " + quantity + " sản phẩm. Tồn kho hiện tại: " + currentStock);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi nhập hàng: " + e.getMessage());
        }
    }

    public void exportGoods(int quantity) {
        try {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Số lượng xuất phải lớn hơn 0.");
            }
            if (quantity > currentStock) {
                throw new IllegalStateException("Không đủ hàng trong kho để xuất.");
            }
            currentStock -= quantity;
            System.out.println("✅ Đã xuất " + quantity + " sản phẩm. Tồn kho hiện tại: " + currentStock);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xuất hàng: " + e.getMessage());
        }
    }

    public void checkLowStock() {
        if (currentStock <= minStock) {
            System.out.println("⚠ Cảnh báo: Hàng tồn đã xuống dưới mức tối thiểu!");
        } else {
            System.out.println("✅ Tồn kho ổn định.");
        }
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getProductId() {
        return productId;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public String getLocation() {
        return location;
    }
}
 