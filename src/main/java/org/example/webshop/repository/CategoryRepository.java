package org.example.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.webshop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
