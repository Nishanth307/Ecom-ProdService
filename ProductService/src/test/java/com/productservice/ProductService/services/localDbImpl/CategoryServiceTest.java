package com.productservice.ProductService.services.localDbImpl;

import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.repositories.CategoryRepository;
import com.productservice.ProductService.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CategoryServiceTest {
    @MockBean
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    public void testSaveCategory(){
        // arrange or create
        Category category = new Category();
        category.setName("Clothes");
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);


        // act or call
        Category newStudent = categoryRepository.save(category);
        Assertions.assertEquals(category.getName(),newStudent.getName());

    }

}


