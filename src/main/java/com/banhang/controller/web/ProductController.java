package com.banhang.controller.web;

import com.banhang.model.dto.ProductDTO;
import com.banhang.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public String listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            Model model) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<ProductDTO> products;
        if (search != null && !search.trim().isEmpty()) {
            products = productService.searchProductsByName(search.trim(), pageable);
        } else if (category != null && !category.trim().isEmpty()) {
            products = productService.getProductsByCategory(category.trim(), pageable);
        } else {
            products = productService.getActiveProductsPageable(pageable);
        }
        
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("totalElements", products.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Products Management");
        model.addAttribute("activeMenu", "products");
        
        return "product/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("pageTitle", "Add New Product");
        model.addAttribute("activeMenu", "products");
        return "product/form";
    }
    
    @PostMapping
    public String createProduct(@Valid @ModelAttribute("product") ProductDTO productDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Add New Product");
            model.addAttribute("activeMenu", "products");
            return "product/form";
        }
        
        // Check if barcode already exists
        if (productDTO.getBarcode() != null && !productDTO.getBarcode().trim().isEmpty()) {
            if (productService.existsByBarcode(productDTO.getBarcode())) {
                bindingResult.rejectValue("barcode", "error.product", "Barcode already exists");
                model.addAttribute("pageTitle", "Add New Product");
                model.addAttribute("activeMenu", "products");
                return "product/form";
            }
        }
        
        try {
            ProductDTO savedProduct = productService.createProduct(productDTO);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Product '" + savedProduct.getProductName() + "' created successfully!");
            return "redirect:/products";
        } catch (Exception e) {
            bindingResult.rejectValue("productName", "error.product", "Error creating product: " + e.getMessage());
            model.addAttribute("pageTitle", "Add New Product");
            model.addAttribute("activeMenu", "products");
            return "product/form";
        }
    }
    
    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ProductDTO> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("pageTitle", "Product Details");
            model.addAttribute("activeMenu", "products");
            return "product/view";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
            return "redirect:/products";
        }
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ProductDTO> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("pageTitle", "Edit Product");
            model.addAttribute("activeMenu", "products");
            return "product/form";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
            return "redirect:/products";
        }
    }
    
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id,
                               @Valid @ModelAttribute("product") ProductDTO productDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Edit Product");
            model.addAttribute("activeMenu", "products");
            return "product/form";
        }
        
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Product '" + updatedProduct.getProductName() + "' updated successfully!");
            return "redirect:/products";
        } catch (Exception e) {
            bindingResult.rejectValue("productName", "error.product", "Error updating product: " + e.getMessage());
            model.addAttribute("pageTitle", "Edit Product");
            model.addAttribute("activeMenu", "products");
            return "product/form";
        }
    }
    
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<ProductDTO> product = productService.getProductById(id);
            if (product.isPresent()) {
                productService.deleteProduct(id);
                redirectAttributes.addFlashAttribute("successMessage", 
                    "Product '" + product.get().getProductName() + "' deleted successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product: " + e.getMessage());
        }
        return "redirect:/products";
    }
    
    @GetMapping("/low-stock")
    public String lowStockProducts(Model model) {
        model.addAttribute("products", productService.getLowStockProducts(10));
        model.addAttribute("pageTitle", "Low Stock Products");
        model.addAttribute("activeMenu", "products");
        return "product/low-stock";
    }
}