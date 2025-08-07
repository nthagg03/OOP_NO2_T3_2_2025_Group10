package com.phenikaa.coffeeshop.controller;

import com.phenikaa.coffeeshop.model.Product;
import com.phenikaa.coffeeshop.service.ProductService;
import com.phenikaa.coffeeshop.exception.ResourceNotFoundException;
import com.phenikaa.coffeeshop.exception.DuplicateResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Product Controller - Web interface for product management
 * Provides CRUD operations and product-related views
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private ProductService productService;
    
    // List all products
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllActiveProducts();
        List<String> categories = productService.getAllCategories();
        
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("totalProducts", products.size());
        
        return "products/list";
    }
    
    // Show product details
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        try {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            return "products/detail";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", "Product not found: " + e.getMessage());
            return "error";
        }
    }
    
    // Show create product form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllCategories());
        return "products/form";
    }
    
    // Create new product
    @PostMapping
    public String createProduct(@Valid @ModelAttribute Product product, 
                               BindingResult result, 
                               Model model, 
                               RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("categories", productService.getAllCategories());
            return "products/form";
        }
        
        try {
            productService.createProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product created successfully!");
            return "redirect:/products";
        } catch (DuplicateResourceException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", productService.getAllCategories());
            return "products/form";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating product: " + e.getMessage());
            model.addAttribute("categories", productService.getAllCategories());
            return "products/form";
        }
    }
    
    // Show edit product form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            model.addAttribute("categories", productService.getAllCategories());
            return "products/form";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", "Product not found: " + e.getMessage());
            return "error";
        }
    }
    
    // Update product
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, 
                               @Valid @ModelAttribute Product product, 
                               BindingResult result, 
                               Model model, 
                               RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("categories", productService.getAllCategories());
            return "products/form";
        }
        
        try {
            productService.updateProduct(id, product);
            redirectAttributes.addFlashAttribute("success", "Product updated successfully!");
            return "redirect:/products";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", "Product not found: " + e.getMessage());
            return "error";
        } catch (DuplicateResourceException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", productService.getAllCategories());
            return "products/form";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating product: " + e.getMessage());
            model.addAttribute("categories", productService.getAllCategories());
            return "products/form";
        }
    }
    
    // Delete product
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("success", "Product deleted successfully!");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Product not found: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting product: " + e.getMessage());
        }
        
        return "redirect:/products";
    }
    
    // Deactivate product
    @PostMapping("/{id}/deactivate")
    public String deactivateProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deactivateProduct(id);
            redirectAttributes.addFlashAttribute("success", "Product deactivated successfully!");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Product not found: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deactivating product: " + e.getMessage());
        }
        
        return "redirect:/products";
    }
    
    // Reactivate product
    @PostMapping("/{id}/reactivate")
    public String reactivateProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.reactivateProduct(id);
            redirectAttributes.addFlashAttribute("success", "Product reactivated successfully!");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Product not found: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error reactivating product: " + e.getMessage());
        }
        
        return "redirect:/products";
    }
    
    // Search products
    @GetMapping("/search")
    public String searchProducts(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String category,
                                @RequestParam(required = false) Double minPrice,
                                @RequestParam(required = false) Double maxPrice,
                                Model model) {
        
        List<Product> products;
        
        if (name != null && !name.trim().isEmpty()) {
            products = productService.searchProductsByName(name);
        } else if (category != null && !category.trim().isEmpty()) {
            products = productService.getProductsByCategory(category);
        } else if (minPrice != null && maxPrice != null) {
            products = productService.getProductsByPriceRange(minPrice, maxPrice);
        } else {
            products = productService.getAllActiveProducts();
        }
        
        model.addAttribute("products", products);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("searchName", name);
        model.addAttribute("searchCategory", category);
        model.addAttribute("searchMinPrice", minPrice);
        model.addAttribute("searchMaxPrice", maxPrice);
        
        return "products/list";
    }
    
    // Filter by category
    @GetMapping("/category/{category}")
    public String filterByCategory(@PathVariable String category, Model model) {
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("selectedCategory", category);
        return "products/list";
    }
    
    // Show low stock products
    @GetMapping("/low-stock")
    public String showLowStockProducts(@RequestParam(defaultValue = "10") Integer threshold, Model model) {
        List<Product> products = productService.getLowStockProducts(threshold);
        model.addAttribute("products", products);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("threshold", threshold);
        model.addAttribute("isLowStock", true);
        return "products/list";
    }
    
    // Update stock
    @PostMapping("/{id}/stock")
    public String updateStock(@PathVariable Long id, 
                             @RequestParam Integer newStock, 
                             RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.getProductById(id);
            productService.updateStock(product.getProductId(), newStock);
            redirectAttributes.addFlashAttribute("success", "Stock updated successfully!");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Product not found: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating stock: " + e.getMessage());
        }
        
        return "redirect:/products";
    }
}