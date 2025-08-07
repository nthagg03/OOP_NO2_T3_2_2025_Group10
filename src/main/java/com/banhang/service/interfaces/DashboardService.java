package com.banhang.service.interfaces;

import com.banhang.model.dto.DashboardDTO;

public interface DashboardService {
    
    /**
     * Generate complete dashboard data with all statistics
     * @return DashboardDTO containing all dashboard information
     */
    DashboardDTO getDashboardData();
    
    /**
     * Get sales statistics for dashboard
     * @return DashboardDTO with sales data only
     */
    DashboardDTO getSalesStatistics();
    
    /**
     * Get order statistics for dashboard
     * @return DashboardDTO with order data only
     */
    DashboardDTO getOrderStatistics();
    
    /**
     * Get inventory statistics for dashboard
     * @return DashboardDTO with inventory data only
     */
    DashboardDTO getInventoryStatistics();
    
    /**
     * Get payment statistics for dashboard
     * @return DashboardDTO with payment data only
     */
    DashboardDTO getPaymentStatistics();
    
    /**
     * Get recent activities for dashboard
     * @return DashboardDTO with recent orders and invoices
     */
    DashboardDTO getRecentActivities();
    
    /**
     * Get chart data for dashboard
     * @return DashboardDTO with chart data (monthly sales, top products, etc.)
     */
    DashboardDTO getChartData();
    
    /**
     * Refresh dashboard cache
     */
    void refreshDashboardCache();
}