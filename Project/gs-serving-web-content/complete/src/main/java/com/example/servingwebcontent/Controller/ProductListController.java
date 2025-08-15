package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.productAiven;
import com.example.servingwebcontent.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ProductListController {

	@Autowired
	private productAiven pa;

	// Danh sách sản phẩm
	@GetMapping("/products")
	public String list(Model model) {
		try {
			ArrayList<Product> products = pa.listProducts();
			model.addAttribute("products", products);
		} catch (Exception e) {
			model.addAttribute("error", "Không thể tải danh sách sản phẩm: " + e.getMessage());
		}
		return "productlist";
	}

	// Form thêm mới
	@GetMapping("/products/new")
	public String createForm(Model model) {
		model.addAttribute("product", new Product());
		return "productlistform";
	}

	// Tạo mới hoặc cập nhật (dựa vào productId có/không)
	@PostMapping("/products")
	public String upsert(@ModelAttribute Product product, Model model) {
		try {
			if (product.getProductId() == null || product.getProductId().isEmpty()) {
				pa.insert(product);
			} else {
				pa.update(product);
			}
			return "redirect:/products";
		} catch (Exception e) {
			model.addAttribute("error", "Lưu sản phẩm thất bại: " + e.getMessage());
			model.addAttribute("product", product);
			return "productlistform";
		}
	}

	// Form sửa
	@GetMapping("/products/edit/{id}")
	public String editForm(@PathVariable("id") String id, Model model) {
		try {
			Product p = pa.getById(id);
			if (p == null) {
				model.addAttribute("error", "Không tìm thấy sản phẩm với ID: " + id);
				return "redirect:/products";
			}
			model.addAttribute("product", p);
			return "productlistform";
		} catch (Exception e) {
			model.addAttribute("error", "Lỗi khi tải sản phẩm: " + e.getMessage());
			return "redirect:/products";
		}
	}

	// Lưu ý: form đang submit tới /products, vì vậy không cần endpoint riêng cho /products/edit

	// Xóa
	@GetMapping("/products/delete/{id}")
	public String delete(@PathVariable("id") String id, Model model) {
		try {
			pa.delete(id);
		} catch (Exception e) {
			model.addAttribute("error", "Xóa sản phẩm thất bại: " + e.getMessage());
		}
		return "redirect:/products";
	}
}
