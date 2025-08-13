package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // Hiển thị trang login
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // Xử lý login
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User loginUser,
                               Model model) {
        try {
            // Kiểm tra username & password
            User user = userService.findByUsername(loginUser.getUsername());
            if (user == null) {
                model.addAttribute("error", "Tài khoản không tồn tại!");
                return "login";
            }

            if (!user.getPassword().equals(loginUser.getPassword())) {
                model.addAttribute("error", "Mật khẩu không đúng!");
                return "login";
            }

            if (!user.isActive()) {
                model.addAttribute("error", "Tài khoản đã bị khóa!");
                return "login";
            }

            // Đăng nhập thành công -> phân quyền
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else if ("USER".equalsIgnoreCase(user.getRole())) {
                return "redirect:/user/home";
            } else {
                model.addAttribute("error", "Vai trò không hợp lệ!");
                return "login";
            }

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            return "login";
        }
    }

    // Đăng xuất
    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("message", "Đăng xuất thành công!");
        return "login";
    }
}
