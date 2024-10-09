package com.productservice.ProductService.controllers;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericProductDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.ProductService.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
      public List<GenericProductDto> getAllProducts(){
            //return productService.getAllProducts();
            return productService.getAllProducts();
      }

      @PostMapping
      public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
            return productService.createProduct(genericProductDto);
      }

      @DeleteMapping("/{id}")
      public GenericProductDto deleteProduct(@PathVariable("id") Long id){
            return productService.deleteProduct(id);
      }


      @PutMapping("/{id}")
      public GenericProductDto updateProduct(@RequestBody GenericProductDto genericProductDto,@PathVariable("id") Long id){
            return productService.updateProduct(id,genericProductDto);
      }
}
