package org.example.webshop.model;

import org.example.webshop.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testAddItem() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setId(1L);
        product.setName("Testprodukt");
        product.setPrice(100.0);

        cart.addProduct(product, 2);
        assertEquals(1, cart.getItems().size());
        assertEquals(200.0, cart.getTotalPrice());
    }

    @Test
    public void testUpdateQuantity() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setId(1L);
        product.setPrice(50.0);

        cart.addProduct(product, 1);
        cart.updateProduct(product, 3);
        assertEquals(3, cart.getItems().get(0).getQuantity());
        assertEquals(150.0, cart.getTotalPrice());
    }

    @Test
    public void testRemoveProduct() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setId(1L);

        cart.addProduct(product, 1);
        cart.removeProduct(1L);
        assertTrue(cart.getItems().isEmpty());
    }
}
