package com.phenikaa.coffeeshop.controller;

import com.phenikaa.coffeeshop.service.*;
import com.phenikaa.coffeeshop.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

/**
 * Dashboard Controller - Main dashboard for the coffee shop management system
 * Provides overview and navigation to all features
 */
@Controller
public class DashboardController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/")
    public String dashboard(Model model) {
        // Get dashboard statistics
        long totalProducts = productService.getAllActiveProducts().size();
        long totalCustomers = customerService.getActiveCustomerCount();
        long totalEmployees = employeeService.getAllActiveEmployees().size();
        
        // Order statistics
        long pendingOrders = orderService.countOrdersByStatus(OrderStatus.PENDING);
        long completedOrdersToday = orderService.getOrdersBetweenDates(
            LocalDateTime.now().withHour(0).withMinute(0).withSecond(0),
            LocalDateTime.now()
        ).stream().filter(order -> order.getStatus() == OrderStatus.COMPLETED).count();
        
        // Revenue statistics
        Double dailyRevenue = orderService.calculateDailyRevenue(LocalDateTime.now());
        Double weeklyRevenue = orderService.calculateRevenueForPeriod(
            LocalDateTime.now().minusDays(7),
            LocalDateTime.now()
        );
        
        // Low stock products
        int lowStockCount = productService.getLowStockProducts(10).size();
        
        // Add to model
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("pendingOrders", pendingOrders);
        model.addAttribute("completedOrdersToday", completedOrdersToday);
        model.addAttribute("dailyRevenue", dailyRevenue != null ? dailyRevenue : 0.0);
        model.addAttribute("weeklyRevenue", weeklyRevenue != null ? weeklyRevenue : 0.0);
        model.addAttribute("lowStockCount", lowStockCount);
        
        return "dashboard";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("version", "1.0.0");
        model.addAttribute("team", "Group A - K18 - Term 3 - 2025");
        return "about";
    }
}