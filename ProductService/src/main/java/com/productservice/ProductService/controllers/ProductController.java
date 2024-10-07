package com.productservice.ProductService.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.ProductService.services.ProductService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController //To show rest apis - @RestController 
@RequestMapping("/products")
public class ProductController {
      private ProductService productService;

      ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
            this.productService = productService;
      }

      @GetMapping("/{id}") 
      public String getProductById(@PathVariable Long id){
            //return "Scalar Product fetched with id: "+id;
            return productService.getProductById(id);
      }

      @GetMapping 
      public void getAllProducts(){

      }

      @DeleteMapping("/{id}")
      public void deleteProduct(){}

      @PostMapping
      public void createProduct(){}

      @PatchMapping
      public void updateProduct(){}
}
