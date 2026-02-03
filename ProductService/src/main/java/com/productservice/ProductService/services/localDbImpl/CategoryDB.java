package com.productservice.ProductService.services.localDbImpl;

import com.productservice.ProductService.exceptions.InvalidNameException;
import com.productservice.ProductService.exceptions.CategoryNotFoundException;
import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.repositories.CategoryRepository;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component("categoryLocalDb")
public class CategoryDB implements ICategoryService {
    private final CategoryRepository categoryRepository;
    public  CategoryDB(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String categoryName) throws CategoryNotFoundException {
        if (categoryName==null || categoryName.isEmpty()){
            throw new InvalidNameException("invalid title");
        }
        Category category= categoryRepository.findByName(categoryName);
        if (category==null){
            throw new CategoryNotFoundException("Product with given name is not found");
        }
        return category;
    }

}
