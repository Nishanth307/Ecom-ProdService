package com.productservice.ProductService.controllers;

import com.productservice.ProductService.models.FakeStore.FakeStoreCategory;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;
    public CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<FakeStoreCategory> getCategories(){
        return categoryService.getCategories();
    }

}
