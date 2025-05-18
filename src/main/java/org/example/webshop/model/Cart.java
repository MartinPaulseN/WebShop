package org.example.webshop.model;

import java.util.*;

import org.example.webshop.entity.Product;

public class Cart {
    private final Map<Long, CartItem> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        CartItem existing = items.get(product.getId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    public void updateProduct(Product product, int quantity) {
        if (items.containsKey(product.getId())) {
            if (quantity <= 0) {
                items.remove(product.getId());
            } else {
                items.get(product.getId()).setQuantity(quantity);
            }
        }
    }

    public void removeProduct(Long productId) {
        items.remove(productId);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public double getTotalPrice() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clear() {
        items.clear();
    }
}
