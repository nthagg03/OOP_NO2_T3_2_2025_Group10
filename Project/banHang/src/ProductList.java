import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    private String categoryId;
    private String categoryName;
    private String description;

    public ProductCategory(String categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Danh mục sản phẩm: " + categoryName + " (ID: " + categoryId + "), Mô tả: " + description;
    }
}
