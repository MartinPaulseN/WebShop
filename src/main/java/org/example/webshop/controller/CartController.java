package org.example.webshop.controller;


import org.example.webshop.entity.Product;
import org.example.webshop.service.CartService;
import org.example.webshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity) {
        System.out.println(">>> addToCart körs: productId=" + productId + ", quantity=" + quantity);
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addToCart(product, quantity);
        } else {
            System.out.println("!!! Produkt hittades inte");
        }

        return "redirect:/products";
    }

    @GetMapping
    public String wiewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getTotalPrice());
        return "cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam("productId") Long productId,
                                 @RequestParam("quantity") int quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam("productId") Long productId) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }
}
