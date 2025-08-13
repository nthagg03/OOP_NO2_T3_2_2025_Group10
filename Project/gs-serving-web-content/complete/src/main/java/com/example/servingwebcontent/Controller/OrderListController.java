package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Order;
import com.example.servingwebcontent.database.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderListController {

    @Autowired
    private OrderService orderService;

    // Hiển thị danh sách đơn hàng
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orderlist";
    }

    // Hiển thị form thêm mới đơn hàng
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderlist";
    }

    // Xử lý thêm mới đơn hàng
    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {
        orderService.addOrder(order);
        return "redirect:/orders";
    }

    // Hiển thị form sửa đơn hàng
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Order existingOrder = orderService.getOrderById(id);
        model.addAttribute("order", existingOrder);
        return "orderlist";
    }

    // Xử lý cập nhật đơn hàng
    @PostMapping("/edit")
    public String updateOrder(@ModelAttribute Order order) {
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    // Xóa đơn hàng
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
