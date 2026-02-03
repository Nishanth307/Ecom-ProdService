package com.productservice.ProductService.controllers;

import com.productservice.ProductService.exceptions.CategoryNotFoundException;
import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;
    public CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/{name}")
    public Category getCategoryByName(@PathVariable String name) throws CategoryNotFoundException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        return categoryService.findCategoryByName(name);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        categoryService.createCategory(category);
        return category;
    }



}
