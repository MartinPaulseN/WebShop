package org.example.webshop.controller;

import org.example.webshop.entity.Product;
import org.example.webshop.repository.ProductRepository;
import org.example.webshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;
    private final ProductRepository productRepository;

    public AdminController(OrderService orderService, ProductRepository productRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
    }

    @GetMapping("/unshipped")
    public String viewUnshippedOrders(Model model) {
        model.addAttribute("orders", orderService.getUnshippedOrders());
        return "admin/unshipped-orders";
    }

    @GetMapping("/shipped")
    public String viewShippedOrders(Model model) {
        model.addAttribute("orders", orderService.getShippedOrders());
        return "admin/shipped-orders";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/add-product";
    }

    @GetMapping("")
    public String adminPanel(Model model) {
        model.addAttribute("unshippedOrders", orderService.getUnshippedOrders());
        model.addAttribute("shippedOrders", orderService.getShippedOrders());
        return "admin/admin-padel";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin";
    }

    @PostMapping("/ship/{id}")
    public String markAsShipped(@PathVariable Long id) {
        orderService.markAsShipped(id);
        return "redirect:/admin";
    }
}
