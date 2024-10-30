package com.productservice.ProductService.repositories;

import java.util.UUID;
import com.productservice.ProductService.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,UUID> {
}
