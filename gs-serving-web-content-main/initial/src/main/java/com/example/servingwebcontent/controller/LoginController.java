package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.database.userAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    
    private final userAiven userService = new userAiven();
    
    // Trang chủ
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userCount", userService.getAllUsers().size());
        return "home";
    }
    
    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    // Xử lý đăng ký
    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công!");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:/register";
        }
    }
    
    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    // Xử lý đăng nhập
    @PostMapping("/login")
    public String processLogin(@RequestParam String soDienThoai, 
                             @RequestParam String matKhau,
                             RedirectAttributes redirectAttributes) {
        User user = userService.findBySoDienThoaiAndMatKhau(soDienThoai, matKhau);
        if (user != null) {
            return "redirect:/users";
        } else {
            redirectAttributes.addFlashAttribute("error", "Số điện thoại hoặc mật khẩu không đúng");
            return "redirect:/login";
        }
    }
}
