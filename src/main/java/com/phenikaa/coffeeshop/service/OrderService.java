package com.phenikaa.coffeeshop.service;

import com.phenikaa.coffeeshop.model.*;
import com.phenikaa.coffeeshop.repository.OrderRepository;
import com.phenikaa.coffeeshop.repository.OrderItemRepository;
import com.phenikaa.coffeeshop.exception.ResourceNotFoundException;
import com.phenikaa.coffeeshop.exception.DuplicateResourceException;
import com.phenikaa.coffeeshop.exception.InsufficientStockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order Service - Business logic layer for Order operations
 * Provides CRUD operations and business rules for orders
 */
@Service
@Transactional
public class OrderService {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private EmployeeService employeeService;
    
    // CREATE operations
    public Order createOrder(Order order) {
        logger.info("Creating new order: {}", order.getOrderId());
        
        // Check if order ID already exists
        if (orderRepository.findByOrderId(order.getOrderId()).isPresent()) {
            throw new DuplicateResourceException("Order with ID " + order.getOrderId() + " already exists");
        }
        
        // Validate business rules
        validateOrder(order);
        
        Order savedOrder = orderRepository.save(order);
        logger.info("Order created successfully with ID: {}", savedOrder.getId());
        return savedOrder;
    }
    
    public Order createOrderWithItems(String customerId, String employeeId, List<OrderItem> orderItems) {
        logger.info("Creating order with items for customer: {}", customerId);
        
        // Fetch customer and employee
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        Employee employee = employeeId != null ? employeeService.getEmployeeByEmployeeId(employeeId) : null;
        
        // Generate order ID
        String orderId = generateOrderId();
        
        // Create order
        Order order = new Order(orderId, customer, employee);
        order = orderRepository.save(order);
        
        // Add order items and check stock
        for (OrderItem item : orderItems) {
            addItemToOrder(order.getId(), item.getProduct().getProductId(), item.getQuantity());
        }
        
        // Recalculate totals
        order.calculateTotals();
        
        Order savedOrder = orderRepository.save(order);
        logger.info("Order with items created successfully: {}", savedOrder.getOrderId());
        return savedOrder;
    }
    
    // READ operations
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        logger.debug("Fetching all orders");
        return orderRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        logger.debug("Fetching order by ID: {}", id);
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public Order getOrderByOrderId(String orderId) {
        logger.debug("Fetching order by order ID: {}", orderId);
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
    }
    
    @Transactional(readOnly = true)
    public List<Order> getOrdersByStatus(OrderStatus status) {
        logger.debug("Fetching orders by status: {}", status);
        return orderRepository.findByStatus(status);
    }
    
    @Transactional(readOnly = true)
    public List<Order> getOrdersByCustomer(Customer customer) {
        logger.debug("Fetching orders for customer: {}", customer.getCustomerId());
        return orderRepository.findByCustomerOrderByOrderDateDesc(customer);
    }
    
    @Transactional(readOnly = true)
    public List<Order> getOrdersByCustomerId(String customerId) {
        logger.debug("Fetching orders for customer ID: {}", customerId);
        return orderRepository.findByCustomerCustomerId(customerId);
    }
    
    @Transactional(readOnly = true)
    public List<Order> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Fetching orders between {} and {}", startDate, endDate);
        return orderRepository.findOrdersBetweenDates(startDate, endDate);
    }
    
    @Transactional(readOnly = true)
    public List<Order> getRecentOrders(int days) {
        LocalDateTime date = LocalDateTime.now().minusDays(days);
        logger.debug("Fetching orders from last {} days", days);
        return orderRepository.findRecentOrders(date);
    }
    
    @Transactional(readOnly = true)
    public List<Order> getOrdersByPaymentMethod(String paymentMethod, OrderStatus status) {
        logger.debug("Fetching orders by payment method: {} and status: {}", paymentMethod, status);
        return orderRepository.findByPaymentMethodAndStatusOrderByOrderDateDesc(paymentMethod, status);
    }
    
    @Transactional(readOnly = true)
    public List<Order> getLargeOrders(Double threshold) {
        logger.debug("Fetching large orders above threshold: {}", threshold);
        return orderRepository.findLargeOrders(threshold);
    }
    
    @Transactional(readOnly = true)
    public List<Order> getStaleOrders(int hours) {
        LocalDateTime datetime = LocalDateTime.now().minusHours(hours);
        logger.debug("Fetching stale orders older than {} hours", hours);
        return orderRepository.findStaleOrders(datetime);
    }
    
    // UPDATE operations
    public Order updateOrder(Long id, Order orderDetails) {
        logger.info("Updating order with ID: {}", id);
        
        Order existingOrder = getOrderById(id);
        
        // Check if changing order ID would create duplicate
        if (!existingOrder.getOrderId().equals(orderDetails.getOrderId()) &&
            orderRepository.findByOrderId(orderDetails.getOrderId()).isPresent()) {
            throw new DuplicateResourceException("Order with ID " + orderDetails.getOrderId() + " already exists");
        }
        
        // Update fields (be careful not to break business logic)
        existingOrder.setOrderId(orderDetails.getOrderId());
        existingOrder.setStatus(orderDetails.getStatus());
        existingOrder.setPaymentMethod(orderDetails.getPaymentMethod());
        existingOrder.setNotes(orderDetails.getNotes());
        existingOrder.setDiscount(orderDetails.getDiscount());
        
        // Recalculate totals if needed
        existingOrder.calculateTotals();
        
        validateOrder(existingOrder);
        
        Order updatedOrder = orderRepository.save(existingOrder);
        logger.info("Order updated successfully: {}", updatedOrder.getId());
        return updatedOrder;
    }
    
    public Order updateOrderStatus(Long id, OrderStatus status) {
        logger.info("Updating order status for ID: {} to {}", id, status);
        
        Order order = getOrderById(id);
        OrderStatus oldStatus = order.getStatus();
        
        // Business rules for status transitions
        validateStatusTransition(oldStatus, status);
        
        order.setStatus(status);
        
        // If marking as completed, ensure stock is properly reserved
        if (status == OrderStatus.COMPLETED && oldStatus != OrderStatus.COMPLETED) {
            // Stock should already be reserved, but verify
            for (OrderItem item : order.getOrderItems()) {
                if (!productService.isInStock(item.getProduct().getProductId(), 0)) {
                    logger.warn("Product {} may have insufficient stock when completing order {}",
                        item.getProduct().getProductId(), order.getOrderId());
                }
            }
        }
        
        // If cancelling, release reserved stock
        if (status == OrderStatus.CANCELLED && oldStatus != OrderStatus.CANCELLED) {
            releaseOrderStock(order);
        }
        
        Order updatedOrder = orderRepository.save(order);
        logger.info("Order status updated successfully: {} -> {}", oldStatus, status);
        return updatedOrder;
    }
    
    public Order applyDiscount(Long id, Double discount) {
        logger.info("Applying discount of {} to order ID: {}", discount, id);
        
        Order order = getOrderById(id);
        order.applyDiscount(discount);
        
        Order updatedOrder = orderRepository.save(order);
        logger.info("Discount applied successfully to order: {}", id);
        return updatedOrder;
    }
    
    // ORDER ITEM operations
    public Order addItemToOrder(Long orderId, String productId, Integer quantity) {
        logger.info("Adding {} units of product {} to order {}", quantity, productId, orderId);
        
        Order order = getOrderById(orderId);
        Product product = productService.getProductByProductId(productId);
        
        // Check if order can be modified
        if (!canModifyOrder(order)) {
            throw new IllegalStateException("Cannot modify order in status: " + order.getStatus());
        }
        
        // Check stock availability
        if (!productService.isInStock(productId, quantity)) {
            throw new InsufficientStockException("Insufficient stock for product: " + productId);
        }
        
        // Check if product already exists in order
        OrderItem existingItem = order.getOrderItems().stream()
            .filter(item -> item.getProduct().getProductId().equals(productId))
            .findFirst()
            .orElse(null);
        
        if (existingItem != null) {
            // Update quantity of existing item
            int newQuantity = existingItem.getQuantity() + quantity;
            if (!productService.isInStock(productId, newQuantity - existingItem.getQuantity())) {
                throw new InsufficientStockException("Insufficient stock for product: " + productId);
            }
            existingItem.setQuantity(newQuantity);
        } else {
            // Add new item
            OrderItem orderItem = new OrderItem(order, product, quantity, product.getPrice());
            order.addOrderItem(orderItem);
        }
        
        // Reserve stock
        productService.reserveStock(productId, quantity);
        
        Order updatedOrder = orderRepository.save(order);
        logger.info("Item added successfully to order: {}", orderId);
        return updatedOrder;
    }
    
    public Order removeItemFromOrder(Long orderId, String productId) {
        logger.info("Removing product {} from order {}", productId, orderId);
        
        Order order = getOrderById(orderId);
        
        // Check if order can be modified
        if (!canModifyOrder(order)) {
            throw new IllegalStateException("Cannot modify order in status: " + order.getStatus());
        }
        
        OrderItem itemToRemove = order.getOrderItems().stream()
            .filter(item -> item.getProduct().getProductId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Product not found in order: " + productId));
        
        // Release stock
        productService.releaseStock(productId, itemToRemove.getQuantity());
        
        order.removeOrderItem(itemToRemove);
        orderItemRepository.delete(itemToRemove);
        
        Order updatedOrder = orderRepository.save(order);
        logger.info("Item removed successfully from order: {}", orderId);
        return updatedOrder;
    }
    
    public Order updateItemQuantity(Long orderId, String productId, Integer newQuantity) {
        logger.info("Updating quantity of product {} in order {} to {}", productId, orderId, newQuantity);
        
        Order order = getOrderById(orderId);
        
        // Check if order can be modified
        if (!canModifyOrder(order)) {
            throw new IllegalStateException("Cannot modify order in status: " + order.getStatus());
        }
        
        OrderItem item = order.getOrderItems().stream()
            .filter(i -> i.getProduct().getProductId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Product not found in order: " + productId));
        
        int oldQuantity = item.getQuantity();
        int quantityDiff = newQuantity - oldQuantity;
        
        // Check stock for increase
        if (quantityDiff > 0 && !productService.isInStock(productId, quantityDiff)) {
            throw new InsufficientStockException("Insufficient stock for product: " + productId);
        }
        
        // Update quantity
        item.setQuantity(newQuantity);
        
        // Adjust stock
        if (quantityDiff > 0) {
            productService.reserveStock(productId, quantityDiff);
        } else if (quantityDiff < 0) {
            productService.releaseStock(productId, Math.abs(quantityDiff));
        }
        
        order.calculateTotals();
        
        Order updatedOrder = orderRepository.save(order);
        logger.info("Item quantity updated successfully in order: {}", orderId);
        return updatedOrder;
    }
    
    // DELETE operations
    public void deleteOrder(Long id) {
        logger.info("Deleting order with ID: {}", id);
        
        Order order = getOrderById(id);
        
        // Can only delete pending or cancelled orders
        if (order.getStatus() != OrderStatus.PENDING && order.getStatus() != OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot delete order in status: " + order.getStatus());
        }
        
        // Release stock if pending
        if (order.getStatus() == OrderStatus.PENDING) {
            releaseOrderStock(order);
        }
        
        orderRepository.delete(order);
        logger.info("Order deleted successfully: {}", id);
    }
    
    // Business logic methods
    private boolean canModifyOrder(Order order) {
        return order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.CONFIRMED;
    }
    
    private void validateStatusTransition(OrderStatus from, OrderStatus to) {
        // Define valid transitions
        boolean validTransition = switch (from) {
            case PENDING -> to == OrderStatus.CONFIRMED || to == OrderStatus.CANCELLED;
            case CONFIRMED -> to == OrderStatus.PREPARING || to == OrderStatus.CANCELLED;
            case PREPARING -> to == OrderStatus.READY || to == OrderStatus.CANCELLED;
            case READY -> to == OrderStatus.COMPLETED;
            case COMPLETED -> false; // Cannot transition from completed
            case CANCELLED -> false; // Cannot transition from cancelled
        };
        
        if (!validTransition) {
            throw new IllegalStateException("Invalid status transition from " + from + " to " + to);
        }
    }
    
    private void releaseOrderStock(Order order) {
        logger.info("Releasing stock for cancelled order: {}", order.getOrderId());
        for (OrderItem item : order.getOrderItems()) {
            productService.releaseStock(item.getProduct().getProductId(), item.getQuantity());
        }
    }
    
    public String generateOrderId() {
        String prefix = "ORD";
        String timestamp = String.valueOf(System.currentTimeMillis());
        return prefix + timestamp.substring(timestamp.length() - 8);
    }
    
    // Statistics and reporting
    @Transactional(readOnly = true)
    public Double calculateRevenueForPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.calculateRevenueForPeriod(startDate, endDate);
    }
    
    @Transactional(readOnly = true)
    public Double calculateDailyRevenue(LocalDateTime date) {
        return orderRepository.calculateDailyRevenue(date);
    }
    
    @Transactional(readOnly = true)
    public Long countOrdersByStatus(OrderStatus status) {
        return orderRepository.countByStatus(status);
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> getTopCustomersBySpending() {
        return orderRepository.findTopCustomersBySpending();
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> getMonthlyRevenueReport() {
        return orderRepository.getMonthlyRevenueReport();
    }
    
    // Validation helper
    private void validateOrder(Order order) {
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }
        
        if (order.getTotalAmount() < 0) {
            throw new IllegalArgumentException("Order total amount cannot be negative");
        }
        
        if (order.getDiscount() < 0) {
            throw new IllegalArgumentException("Order discount cannot be negative");
        }
        
        if (order.getDiscount() > order.getSubtotal()) {
            throw new IllegalArgumentException("Order discount cannot exceed subtotal");
        }
    }
}