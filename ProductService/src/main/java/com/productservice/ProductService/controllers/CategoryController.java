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

    @GetMapping("categories/{name}")
    public Category getCategoryByName(@PathVariable String name) throws CategoryNotFoundException {
        return categoryService.findCategoryByName(name);
    }

    @PostMapping
    public void createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
    }



}
