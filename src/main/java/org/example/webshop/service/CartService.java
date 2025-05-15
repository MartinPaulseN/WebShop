package org.example.webshop.service;


import org.example.webshop.model.CartItem;
import org.example.webshop.entity.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final Map<Long, CartItem> cartItems = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        CartItem item = cartItems.get(product.getId());
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            cartItems.put(product.getId(), new CartItem(product, quantity));
        }
    }

    public Collection<CartItem> getItems() {
        return cartItems.values();
    }

    public void clearCart() {
        cartItems.clear();
    }
}
