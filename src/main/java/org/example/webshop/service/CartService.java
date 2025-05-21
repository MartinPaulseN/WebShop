package org.example.webshop.service;


import org.example.webshop.model.CartItem;
import org.example.webshop.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class CartService {

    private final Map<Long, CartItem> cart = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        cart.compute(product.getId(), (id, existingItem) -> {
            if (existingItem == null) {
                return new CartItem(product, quantity);
            } else {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                return existingItem;
            }
        });
    }

    public void updateQuantity(Long productId, int quantity) {
        CartItem item = cart.get(productId);
        if (item != null) {
            if (quantity <= 0) {
                cart.remove(productId);
            } else {
                item.setQuantity(quantity);
            }
        }
    }

    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cart.values());
    }

    public double getTotalPrice() {
        return cart.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clearCart() {
        cart.clear();
    }
}
