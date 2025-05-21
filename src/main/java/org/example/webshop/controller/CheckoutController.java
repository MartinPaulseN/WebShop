package org.example.webshop.controller;


import org.example.webshop.entity.Order;
import org.example.webshop.entity.OrderItem;
import org.example.webshop.entity.User;
import org.example.webshop.model.CartItem;
import org.example.webshop.service.CartService;
import org.example.webshop.service.OrderService;
import org.example.webshop.service.UserService;
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
    private final OrderService orderService;
    private final UserService userService;

    public CheckoutController(CartService cartService, OrderRepository orderRepository, OrderService orderService, UserService userService) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public String checkout(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();

        if (cartItems.isEmpty()) {
            model.addAttribute("message", "Din varukorg är tom.");
            return "cart";
        }

        User user = userService.getLoggedInUser();

        Order order = new Order();
        order.setUsername(user.getUsername());
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
