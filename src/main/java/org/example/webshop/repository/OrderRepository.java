package org.example.webshop.repository;

import org.example.webshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByShippedFalse();
    List<Order> findByShippedTrue();
}
