package com.productservice.ProductService.controllers;

import com.productservice.ProductService.exceptions.CategoryNotFoundException;
import com.productservice.ProductService.services.CategoryService;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerIT {
    @MockBean(name = "categoryFakeStore")
    private ICategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;
//
//    @Test
//    void findCategoryByNameFailure() throws Exception {
//        when(categoryService.findCategoryByName("Clothes")).thenThrow(new CategoryNotFoundException("Category Not Found"));
//        mockMvc.perform(get("/category/categories/Clothes"))
//                .andExpect(status().is(500))
//                .andExpect(content().string("Category Not Found"));
//    }

//    @ExceptionHandler(CategoryNotFoundException.class)
//    public ResponseEntity<String> handleCategoryNotFound(CategoryNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//    }

}
