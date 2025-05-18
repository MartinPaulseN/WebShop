package org.example.webshop.controller;

import org.example.webshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
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

    @PostMapping("/ship/{id}")
    public String markAsShipped(@PathVariable Long id) {
        orderService.markAsShipped(id);
        return "redirect:/admin/orders/unshipped";
    }
}
