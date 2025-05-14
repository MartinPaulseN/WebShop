package org.example.webshop.service;

import org.example.webshop.entity.Category;
import org.example.webshop.entity.Product;
import org.example.webshop.repository.CategoryRepository;
import org.example.webshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        if (category != null) {
            return category.getProducts();
        }
        return List.of();
    }
}
