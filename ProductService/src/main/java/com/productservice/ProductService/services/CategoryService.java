package com.productservice.ProductService.services;

import com.productservice.ProductService.models.FakeStore.FakeStoreCategory;
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

    public List<FakeStoreCategory> getAllCategories(){
        return categoryService.getCategories();
    }

}
