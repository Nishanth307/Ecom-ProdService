package com.productservice.ProductService.controllers;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericProductDto;
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
      public GenericProductDto getProductById(@PathVariable Long id){
            //return "Scalar Product fetched with id: "+id;
            return productService.getProductById(id);
      }

      @GetMapping 
      public void getAllProducts(){
            //return productService.getAllProducts();
      }

      @DeleteMapping("/{id}")
      public void deleteProduct(){
            //return productService.deleteProduct();
      }

      @PostMapping
      public void createProduct(){
            //return productService.createProduct();
      }

      @PatchMapping
      public void updateProduct(){
            //return productService.updateProduct();
      }
}
