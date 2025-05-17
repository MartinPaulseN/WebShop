package org.example.webshop.service;

import org.example.webshop.entity.Order;
import org.example.webshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getUnshippedOrders() {
        return orderRepository.findByShippedFalse();
    }

    public List<Order> getShippedOrders() {
        return orderRepository.findByShippedTrue();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void markAsShipped(Long id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        orderOpt.ifPresent(order -> {
            order.setShipped(true);
            orderRepository.save(order);
        });
    }
}
