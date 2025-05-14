package org.example.webshop.controller;


import org.example.webshop.entity.Category;
import org.example.webshop.entity.Product;
import org.example.webshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showProducts(@RequestParam(value = "category", required = false) Long categoryId,
                               @RequestParam(value = "search", required = false) String searchTerm,
                               Model model) {
        List<Category> categories = productService.getAllCategories();
        List<Product> products;

        if (searchTerm != null && !searchTerm.isBlank()) {
            products = productService.searchProducts(searchTerm);
        } else if (categoryId != null) {
            products = productService.getProductsByCategory(categoryId);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategory", categoryId);
        model.addAttribute("searchTerm", searchTerm);

        return "product-list";
    }
}
