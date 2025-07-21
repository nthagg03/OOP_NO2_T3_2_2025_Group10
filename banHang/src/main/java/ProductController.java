package main.java;

public class ProductController {
    private ProductService productService;

    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    public String save(Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    public String editForm(Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/form";
    }

    public String delete(Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}