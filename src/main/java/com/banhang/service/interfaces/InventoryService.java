package com.banhang.service.interfaces;

import com.banhang.model.dto.InventoryDTO;
import com.banhang.model.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    
    // CRUD Operations
    InventoryDTO createInventory(InventoryDTO inventoryDTO);
    Optional<InventoryDTO> getInventoryById(Long id);
    InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO);
    void deleteInventory(Long id);
    
    // List Operations
    List<InventoryDTO> getAllInventory();
    Page<InventoryDTO> getAllInventoryPageable(Pageable pageable);
    
    // Search Operations
    Optional<InventoryDTO> getInventoryByProductId(Long productId);
    List<InventoryDTO> getInventoryByLocation(String location);
    List<InventoryDTO> getInventoryBySupplier(String supplierId);
    
    // Advanced Search
    Page<InventoryDTO> searchInventory(String productName, String location, String supplierId, 
                                      Boolean lowStock, Pageable pageable);
    
    // Stock Operations
    boolean addStock(Long productId, Integer quantity);
    boolean removeStock(Long productId, Integer quantity);
    boolean updateStock(Long productId, Integer newQuantity);
    boolean canFulfillOrder(Long productId, Integer quantity);
    
    // Inventory Analysis
    List<InventoryDTO> getLowStockItems();
    Page<InventoryDTO> getLowStockItems(Pageable pageable);
    List<InventoryDTO> getOverstockItems();
    List<InventoryDTO> getItemsNeedingRestock();
    List<InventoryDTO> getItemsByStockRange(Integer minStock, Integer maxStock);
    List<InventoryDTO> getProductsWithSufficientStock(Integer quantity);
    
    // Statistics
    Double getTotalInventoryValue();
    Double getInventoryValueByLocation(String location);
    Double getInventoryValueBySupplier(String supplierId);
    long getLowStockItemCount();
    long getOverstockItemCount();
    List<Object[]> getInventoryStatsByLocation();
    List<Object[]> getInventoryStatsBySupplier();
    
    // Business Operations
    void processStockMovement(Long productId, Integer quantity, String movementType, String reason);
    List<InventoryDTO> generateRestockReport();
    void updateStockLevels(Long productId, Integer minStock, Integer maxStock);
    void performStockTaking(Long productId, Integer actualQuantity);
    
    // Alerts and Notifications
    List<InventoryDTO> getStockAlerts();
    void sendLowStockAlerts();
    void sendOverstockAlerts();
    
    // Utility Methods
    InventoryDTO convertToDTO(Inventory inventory);
    Inventory convertToEntity(InventoryDTO inventoryDTO);
    void initializeInventoryForProduct(Long productId, Integer initialStock, String location);
}