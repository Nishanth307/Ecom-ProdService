package com.productservice.ProductService.services.interfaces;

import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.models.datamodels.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategories();
    void createCategory(Category category);
    Category findCategoryByName(String categoryName) throws ProductNotFoundException;
}
