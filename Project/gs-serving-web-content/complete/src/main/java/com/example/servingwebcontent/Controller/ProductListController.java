package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.ProductList;
import com.example.servingwebcontent.database.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductListController {

    @Autowired
    private ProductListService productListService;

    // Hiển thị toàn bộ danh mục sản phẩm
    @GetMapping("/productlists")
    public String viewAllProductLists(Model model) {
        model.addAttribute("productlists", productListService.getAllProductLists());
        return "productlist";
    }

    // Form thêm mới danh mục
    @GetMapping("/addProductListForm")
    public String addProductListForm(Model model) {
        model.addAttribute("productlist", new ProductList());
        return "productlistform";
    }

    // Xử lý lưu danh mục mới
    @PostMapping("/saveProductList")
    public String saveProductList(@ModelAttribute("productlist") ProductList productList) {
        productListService.saveProductList(productList);
        return "redirect:/productlists";
    }

    // Form chỉnh sửa danh mục
    @GetMapping("/editProductList/{id}")
    public String editProductListForm(@PathVariable("id") String id, Model model) {
        ProductList productList = productListService.getProductListById(id);
        model.addAttribute("productlist", productList);
        return "productlistform";
    }

    // Xóa danh mục
    @GetMapping("/deleteProductList/{id}")
    public String deleteProductList(@PathVariable("id") String id) {
        productListService.deleteProductList(id);
        return "redirect:/productlists";
    }
}
