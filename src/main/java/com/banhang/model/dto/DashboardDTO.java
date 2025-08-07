package com.banhang.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DashboardDTO {
    
    // Summary Statistics
    private Long totalProducts;
    private Long totalCustomers;
    private Long totalOrders;
    private Long totalInvoices;
    
    // Sales Statistics
    private Double totalSales;
    private Double todaySales;
    private Double thisMonthSales;
    private Double thisYearSales;
    
    // Order Statistics
    private Long pendingOrders;
    private Long completedOrders;
    private Long cancelledOrders;
    
    // Inventory Statistics
    private Long lowStockItems;
    private Long overstockItems;
    private Double totalInventoryValue;
    
    // Payment Statistics
    private Long pendingInvoices;
    private Long overdueInvoices;
    private Double totalOutstanding;
    
    // Charts Data
    private List<MonthlySalesDTO> monthlySalesData;
    private List<TopProductDTO> topProducts;
    private List<TopCustomerDTO> topCustomers;
    private List<InventoryAlertDTO> inventoryAlerts;
    
    // Recent Activities
    private List<OrderDTO> recentOrders;
    private List<InvoiceDTO> recentInvoices;
    
    // Last Update
    private LocalDateTime lastUpdated;
    
    // Constructors
    public DashboardDTO() {
        this.lastUpdated = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getTotalProducts() {
        return totalProducts;
    }
    
    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }
    
    public Long getTotalCustomers() {
        return totalCustomers;
    }
    
    public void setTotalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
    
    public Long getTotalOrders() {
        return totalOrders;
    }
    
    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }
    
    public Long getTotalInvoices() {
        return totalInvoices;
    }
    
    public void setTotalInvoices(Long totalInvoices) {
        this.totalInvoices = totalInvoices;
    }
    
    public Double getTotalSales() {
        return totalSales;
    }
    
    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
    
    public Double getTodaySales() {
        return todaySales;
    }
    
    public void setTodaySales(Double todaySales) {
        this.todaySales = todaySales;
    }
    
    public Double getThisMonthSales() {
        return thisMonthSales;
    }
    
    public void setThisMonthSales(Double thisMonthSales) {
        this.thisMonthSales = thisMonthSales;
    }
    
    public Double getThisYearSales() {
        return thisYearSales;
    }
    
    public void setThisYearSales(Double thisYearSales) {
        this.thisYearSales = thisYearSales;
    }
    
    public Long getPendingOrders() {
        return pendingOrders;
    }
    
    public void setPendingOrders(Long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
    
    public Long getCompletedOrders() {
        return completedOrders;
    }
    
    public void setCompletedOrders(Long completedOrders) {
        this.completedOrders = completedOrders;
    }
    
    public Long getCancelledOrders() {
        return cancelledOrders;
    }
    
    public void setCancelledOrders(Long cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }
    
    public Long getLowStockItems() {
        return lowStockItems;
    }
    
    public void setLowStockItems(Long lowStockItems) {
        this.lowStockItems = lowStockItems;
    }
    
    public Long getOverstockItems() {
        return overstockItems;
    }
    
    public void setOverstockItems(Long overstockItems) {
        this.overstockItems = overstockItems;
    }
    
    public Double getTotalInventoryValue() {
        return totalInventoryValue;
    }
    
    public void setTotalInventoryValue(Double totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }
    
    public Long getPendingInvoices() {
        return pendingInvoices;
    }
    
    public void setPendingInvoices(Long pendingInvoices) {
        this.pendingInvoices = pendingInvoices;
    }
    
    public Long getOverdueInvoices() {
        return overdueInvoices;
    }
    
    public void setOverdueInvoices(Long overdueInvoices) {
        this.overdueInvoices = overdueInvoices;
    }
    
    public Double getTotalOutstanding() {
        return totalOutstanding;
    }
    
    public void setTotalOutstanding(Double totalOutstanding) {
        this.totalOutstanding = totalOutstanding;
    }
    
    public List<MonthlySalesDTO> getMonthlySalesData() {
        return monthlySalesData;
    }
    
    public void setMonthlySalesData(List<MonthlySalesDTO> monthlySalesData) {
        this.monthlySalesData = monthlySalesData;
    }
    
    public List<TopProductDTO> getTopProducts() {
        return topProducts;
    }
    
    public void setTopProducts(List<TopProductDTO> topProducts) {
        this.topProducts = topProducts;
    }
    
    public List<TopCustomerDTO> getTopCustomers() {
        return topCustomers;
    }
    
    public void setTopCustomers(List<TopCustomerDTO> topCustomers) {
        this.topCustomers = topCustomers;
    }
    
    public List<InventoryAlertDTO> getInventoryAlerts() {
        return inventoryAlerts;
    }
    
    public void setInventoryAlerts(List<InventoryAlertDTO> inventoryAlerts) {
        this.inventoryAlerts = inventoryAlerts;
    }
    
    public List<OrderDTO> getRecentOrders() {
        return recentOrders;
    }
    
    public void setRecentOrders(List<OrderDTO> recentOrders) {
        this.recentOrders = recentOrders;
    }
    
    public List<InvoiceDTO> getRecentInvoices() {
        return recentInvoices;
    }
    
    public void setRecentInvoices(List<InvoiceDTO> recentInvoices) {
        this.recentInvoices = recentInvoices;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    // Helper DTOs for dashboard data
    public static class MonthlySalesDTO {
        private Integer year;
        private Integer month;
        private String monthName;
        private Long orderCount;
        private Double totalSales;
        
        // Constructors, getters and setters
        public MonthlySalesDTO() {}
        
        public MonthlySalesDTO(Integer year, Integer month, String monthName, Long orderCount, Double totalSales) {
            this.year = year;
            this.month = month;
            this.monthName = monthName;
            this.orderCount = orderCount;
            this.totalSales = totalSales;
        }
        
        // Getters and setters for all fields...
        public Integer getYear() { return year; }
        public void setYear(Integer year) { this.year = year; }
        public Integer getMonth() { return month; }
        public void setMonth(Integer month) { this.month = month; }
        public String getMonthName() { return monthName; }
        public void setMonthName(String monthName) { this.monthName = monthName; }
        public Long getOrderCount() { return orderCount; }
        public void setOrderCount(Long orderCount) { this.orderCount = orderCount; }
        public Double getTotalSales() { return totalSales; }
        public void setTotalSales(Double totalSales) { this.totalSales = totalSales; }
    }
    
    public static class TopProductDTO {
        private String productName;
        private Long quantitySold;
        private Double totalRevenue;
        
        public TopProductDTO() {}
        
        public TopProductDTO(String productName, Long quantitySold, Double totalRevenue) {
            this.productName = productName;
            this.quantitySold = quantitySold;
            this.totalRevenue = totalRevenue;
        }
        
        // Getters and setters
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public Long getQuantitySold() { return quantitySold; }
        public void setQuantitySold(Long quantitySold) { this.quantitySold = quantitySold; }
        public Double getTotalRevenue() { return totalRevenue; }
        public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }
    }
    
    public static class TopCustomerDTO {
        private String customerName;
        private Long orderCount;
        private Double totalSpent;
        
        public TopCustomerDTO() {}
        
        public TopCustomerDTO(String customerName, Long orderCount, Double totalSpent) {
            this.customerName = customerName;
            this.orderCount = orderCount;
            this.totalSpent = totalSpent;
        }
        
        // Getters and setters
        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }
        public Long getOrderCount() { return orderCount; }
        public void setOrderCount(Long orderCount) { this.orderCount = orderCount; }
        public Double getTotalSpent() { return totalSpent; }
        public void setTotalSpent(Double totalSpent) { this.totalSpent = totalSpent; }
    }
    
    public static class InventoryAlertDTO {
        private String productName;
        private Integer currentStock;
        private Integer minStock;
        private String alertType; // LOW_STOCK, OVERSTOCK
        
        public InventoryAlertDTO() {}
        
        public InventoryAlertDTO(String productName, Integer currentStock, Integer minStock, String alertType) {
            this.productName = productName;
            this.currentStock = currentStock;
            this.minStock = minStock;
            this.alertType = alertType;
        }
        
        // Getters and setters
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public Integer getCurrentStock() { return currentStock; }
        public void setCurrentStock(Integer currentStock) { this.currentStock = currentStock; }
        public Integer getMinStock() { return minStock; }
        public void setMinStock(Integer minStock) { this.minStock = minStock; }
        public String getAlertType() { return alertType; }
        public void setAlertType(String alertType) { this.alertType = alertType; }
    }
}