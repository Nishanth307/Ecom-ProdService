package com.productservice.ProductService.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.services.ProductService;

import jakarta.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//It won't initialize the unnecessary dependencies.
@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {
        @MockBean(name = "fakeStoreProductService")
        private ProductService productService;

        @Autowired
        private MockMvc mockMvc;//framework object to make end to end calls

        @Autowired
        private ObjectMapper objectMapper;

        @Captor
        private ArgumentCaptor<Long> argumentCaptor;

        @Autowired 
        private ProductController productController;


        @Test
        void testGetAllProductsReturnsEmptyList() throws Exception {
                when(productService.getAllProducts())
                        .thenReturn(new ArrayList<>());

                mockMvc.perform(get("/products")) //  output we expect  ,it makes http
                        .andExpect(status().is(200))
                        .andExpect(content().string("[]"));    
        }
        @Test
        void testGetAllProductsValidList() throws Exception {
                List<GenericProductDto> genericProductDtos = new ArrayList<>();
                genericProductDtos.add(new GenericProductDto());
                genericProductDtos.add(new GenericProductDto());
                genericProductDtos.add(new GenericProductDto());
                when(productService.getAllProducts())
                        .thenReturn(genericProductDtos);

                mockMvc.perform(get("/products")) //  output we expect  ,it makes http
                        .andExpect(status().is(200))
                        .andExpect(content().string(objectMapper.writeValueAsString(genericProductDtos)));
                //Object Mapper ->JackSon library to convert java object to JSON it converts string of object value into json
        }
        @Test
        void testCreateValidNewProduct() throws Exception {
                
        GenericProductDto productToCreateDto = new GenericProductDto();
        productToCreateDto.setTitle("Macbook");
        productToCreateDto.setDescription("Fastest Mac ever");
        productToCreateDto.setPrice(500);
        productToCreateDto.setImage("Mac photo");
        productToCreateDto.setCategory("Laptop");

        GenericProductDto outputProductDto = new GenericProductDto();
        outputProductDto.setId(1000L);
        outputProductDto.setTitle(productToCreateDto.getTitle());
        outputProductDto.setDescription(productToCreateDto.getDescription());
        outputProductDto.setPrice(productToCreateDto.getPrice());
        outputProductDto.setImage(productToCreateDto.getImage());
        outputProductDto.setCategory(productToCreateDto.getCategory());

        when(productService.createProduct(any())).thenReturn(outputProductDto);
        //when(productService.createProduct(productToCreateDto)).thenReturn(outputProductDto);
        //for objects we can not hardcode things

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON) //pass an input
                        .content(objectMapper.writeValueAsString(productToCreateDto))
                )
                .andExpect(content().string(objectMapper.writeValueAsString(outputProductDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.title",is("Macbook")))
                .andExpect(jsonPath("$.price",is(500)));
        }
        @Test
        @DisplayName("testProductControllerCallsProductServiceWithSameProductAsIdAsInput")
        void testIfSameInput()  throws ProductNotFoundException{
                //This is a test case to check if productController is passing the same products
                //to product service as the input
                Long id = 100L;  
                when(productService.getProductById(id)).thenReturn(new GenericProductDto());
                GenericProductDto genericProductDto = productController.getProductById(id);
                verify(productService).getProductById(argumentCaptor.capture() );//if there is another parameter than create another argumentCaptor2<Type>
                assertEquals(id, argumentCaptor.getValue());
        }
    
}
/*

{ $
    student: {
        id:"1",
        "name":"Deepak",
        psp:
    }
}

 */
