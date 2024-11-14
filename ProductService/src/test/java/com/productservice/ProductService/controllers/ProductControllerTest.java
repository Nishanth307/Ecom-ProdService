package com.productservice.ProductService.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.services.ProductService;

import jakarta.inject.Inject;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class ProductControllerTest { 
   // Only Test cases for controller //
      @MockBean
      private ProductService productService;//it will not actually store product service ,this is hardcoded local,only for test
      @Inject
      private ProductController productController;

      @Test
      @DisplayName("Testing addition")
      void checkAddition(){
         //assert(5 == 2+3);
         assertEquals(5,2+3,"both values should be equal");
      }
      /*
      
      @Test
      void testGetProductByIdNegative(){
         //assert(5 == 2+3);
         assertThrows(ProductNotFoundException.class, ()->productController.getProductById(1000L));//()->exectuable
      }
      */
      
   //   @Test
   //   void testgetProductByIdMocking() throws ProductNotFoundException{
   //    GenericProductDto genericProductDto = new GenericProductDto();
   //    when(productService.getProductById(100L)).thenReturn(genericProductDto);//hardcoding to null instead of exception
   //    GenericProductDto genericProductDto2 = productController.getProductById(100L);
   //    assertEquals(genericProductDto,genericProductDto2);
   //    //assertEquals(genericProductDto,productService.getProductById(100L));
   //   }

   //    @Test
   //    void testGetProductByIdMockingException() throws ProductNotFoundException {
   //       when(productService.getProductById(1L))
   //                .thenThrow(ProductNotFoundException.class);

   //       assertThrows(ProductNotFoundException.class,
   //                () -> productController.getProductById(1L));
   //    }
}