package com.banhang.service.interfaces;

import com.banhang.model.dto.ProductDTO;
import com.banhang.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    // CRUD Operations
    ProductDTO createProduct(ProductDTO productDTO);
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    
    // List Operations
    List<ProductDTO> getAllProducts();
    Page<ProductDTO> getAllProductsPageable(Pageable pageable);
    List<ProductDTO> getActiveProducts();
    Page<ProductDTO> getActiveProductsPageable(Pageable pageable);
    
    // Search Operations
    List<ProductDTO> searchProductsByName(String name);
    Page<ProductDTO> searchProductsByName(String name, Pageable pageable);
    List<ProductDTO> getProductsByCategory(String categoryId);
    Page<ProductDTO> getProductsByCategory(String categoryId, Pageable pageable);
    Optional<ProductDTO> getProductByBarcode(String barcode);
    
    // Advanced Search
    Page<ProductDTO> searchProducts(String name, String categoryId, Double minPrice, Double maxPrice, Pageable pageable);
    
    // Business Operations
    List<ProductDTO> getLowStockProducts(Integer threshold);
    List<ProductDTO> getProductsByPriceRange(Double minPrice, Double maxPrice);
    boolean updateStock(Long productId, Integer quantity);
    boolean isStockAvailable(Long productId, Integer requiredQuantity);
    
    // Statistics
    long getTotalProductCount();
    long getActiveProductCount();
    long getProductCountByCategory(String categoryId);
    Double getTotalStockValue();
    
    // Utility Methods
    ProductDTO convertToDTO(Product product);
    Product convertToEntity(ProductDTO productDTO);
    boolean existsByBarcode(String barcode);
    void activateProduct(Long id);
    void deactivateProduct(Long id);
}