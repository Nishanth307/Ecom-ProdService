package com.productservice.ProductService.services;

import com.productservice.ProductService.exceptions.CategoryNotFoundException;
import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class CategoryService {
    private final ICategoryService categoryService;
    public CategoryService(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    public List<Category> getAllCategories(){
        return categoryService.getCategories();
    }

    public void createCategory(Category category){
        categoryService.createCategory(category);
    }

    public Category findCategoryByName(String categoryName) throws CategoryNotFoundException {
        return categoryService.findCategoryByName(categoryName);
    }
}
