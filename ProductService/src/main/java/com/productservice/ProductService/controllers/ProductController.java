package com.productservice.ProductService.controllers;

import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

import org.springframework.web.bind.annotation.*;

import com.productservice.ProductService.services.ProductService;
import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;


@RestController //To show rest apis - @RestController 
@RequestMapping("/products")
public class ProductController {
      private ProductService productService;

      ProductController( @Qualifier("fakeStoreProductService") ProductService productService){
            this.productService = productService;
      }
 
      @GetMapping("/{id}") 
      public GenericProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,@PathVariable Long id) throws ProductNotFoundException{
            //return "Scalar Product fetched with id: "+id;
            return productService.getProductById(authToken,id);
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
      public GenericProductDto deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException{
            return productService.deleteProduct(id);
      }


      @PutMapping("/{id}")
      public GenericProductDto updateProduct(@RequestBody GenericProductDto genericProductDto,@PathVariable("id") Long id) throws ProductNotFoundException{
            return productService.updateProduct(id,genericProductDto);
      }
      /*
      
      @ExceptionHandler(ProductNotFoundException.class)
            private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
                  ExceptionDto exceptionDto  = new ExceptionDto();
            
            exceptionDto.setMessage(productNotFoundException.getMessage());
            //return exceptionDto;
            exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
            ResponseEntity responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
            return responseEntity;
      }
      */
}
