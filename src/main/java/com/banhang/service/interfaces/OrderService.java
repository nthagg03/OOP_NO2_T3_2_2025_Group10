package com.banhang.service.interfaces;

import com.banhang.model.dto.OrderDTO;
import com.banhang.model.dto.OrderItemDTO;
import com.banhang.model.entity.Order;
import com.banhang.model.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    
    // CRUD Operations
    OrderDTO createOrder(OrderDTO orderDTO);
    Optional<OrderDTO> getOrderById(Long id);
    OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
    
    // List Operations
    List<OrderDTO> getAllOrders();
    Page<OrderDTO> getAllOrdersPageable(Pageable pageable);
    Page<OrderDTO> getRecentOrders(Pageable pageable);
    
    // Search Operations
    Optional<OrderDTO> getOrderByCode(String orderCode);
    List<OrderDTO> getOrdersByCustomer(Long customerId);
    Page<OrderDTO> getOrdersByCustomer(Long customerId, Pageable pageable);
    List<OrderDTO> getOrdersByStatus(OrderStatus status);
    Page<OrderDTO> getOrdersByStatus(OrderStatus status, Pageable pageable);
    
    // Advanced Search
    Page<OrderDTO> searchOrders(String orderCode, Long customerId, OrderStatus status, 
                               LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    List<OrderDTO> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    Page<OrderDTO> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    
    // Order Item Operations
    OrderDTO addItemToOrder(Long orderId, OrderItemDTO orderItemDTO);
    OrderDTO updateOrderItem(Long orderId, Long orderItemId, OrderItemDTO orderItemDTO);
    OrderDTO removeItemFromOrder(Long orderId, Long orderItemId);
    List<OrderItemDTO> getOrderItems(Long orderId);
    
    // Business Operations
    OrderDTO confirmOrder(Long orderId);
    OrderDTO cancelOrder(Long orderId);
    OrderDTO completeOrder(Long orderId);
    OrderDTO updateOrderStatus(Long orderId, OrderStatus status);
    boolean canModifyOrder(Long orderId);
    boolean canCancelOrder(Long orderId);
    
    // Payment Operations
    OrderDTO updatePaymentMethod(Long orderId, String paymentMethod);
    OrderDTO applyDiscount(Long orderId, Double discountAmount);
    void calculateOrderTotals(OrderDTO orderDTO);
    
    // Statistics
    long getTotalOrderCount();
    long getOrderCountByStatus(OrderStatus status);
    long getOrderCountByCustomer(Long customerId);
    Double getTotalSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Object[]> getMonthlySalesStats();
    Page<Object[]> getTopCustomersByValue(Pageable pageable);
    
    // Validation
    boolean existsByOrderCode(String orderCode);
    String generateOrderCode();
    
    // Utility Methods
    OrderDTO convertToDTO(Order order);
    Order convertToEntity(OrderDTO orderDTO);
}