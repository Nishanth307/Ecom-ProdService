package com.productservice.ProductService.repositories;

import com.productservice.ProductService.models.datamodels.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Category findByName(String name);
}
