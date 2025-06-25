package com.productservice.ProductService.services.localDbImpl;

import com.productservice.ProductService.exceptions.CategoryNotFoundException;
import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.UUID;

public class CategoryServiceTest2 {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryDB categoryService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this); //creates auto closable resources for each test method
    }

    @Test
    public void testGetCategoryByNameSuccess() throws CategoryNotFoundException {
        // arrange
        String name = "Clothes";
        Category mockCategory = new Category();
        mockCategory.setId(UUID.randomUUID());
        mockCategory.setName("Clothes");
        when(categoryRepository.findByName(any())).thenReturn(mockCategory);
        // act
        Category actualResponse = categoryService.findCategoryByName(name);
        // assert
        Assertions.assertEquals(actualResponse.getName(),mockCategory.getName());// can add more fields
    }

    @Test
    public void testFindProductByNameWithNullObject() throws CategoryNotFoundException {
        String name = "Clothes";
        when(categoryRepository.findByName(name)).thenReturn(null);
//        Category category = categoryService.findCategoryByName(name);
//        Assertions.assertEquals(category.getName(),name);
        Assertions.assertThrows(CategoryNotFoundException.class,()->categoryService.findCategoryByName(name));
    }
}
