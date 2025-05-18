package org.example.webshop.controller;


import org.example.webshop.entity.Order;
import org.example.webshop.entity.OrderItem;
import org.example.webshop.model.CartItem;
import org.example.webshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.example.webshop.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;
    private final OrderRepository orderRepository;

    public CheckoutController(CartService cartService, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public String ceckout(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();

        if (cartItems.isEmpty()) {
            model.addAttribute("message", "Din varukorg är tom.");
            return "cart";
        }

        Order order = new Order();
        order.setTotalPrice(cartService.getTotalPrice());

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(order, item.getProduct(), item.getQuantity(), item.getProduct().getPrice()))
                .collect(Collectors.toList());

        order.setItems(orderItems);

        orderRepository.save(order);

        cartService.clearCart();

        model.addAttribute("order", order);

        return "order-confirmation";
    }
}
