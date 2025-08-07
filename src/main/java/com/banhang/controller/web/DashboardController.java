package com.banhang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("activeMenu", "dashboard");
        
        // For now, add some basic data
        model.addAttribute("totalProducts", 0L);
        model.addAttribute("totalCustomers", 0L);
        model.addAttribute("totalOrders", 0L);
        model.addAttribute("totalSales", 0.0);
        
        return "dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboardAlias(Model model) {
        return dashboard(model);
    }
}