package com.productservice.ProductService.controllers;

import com.productservice.ProductService.models.dtos.GenericProductRequestDto;
import com.productservice.ProductService.models.dtos.GenericDto;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductResponseDto getProductById(@PathVariable String id){
        return  productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductResponseDto> getProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public GenericDto updateProductById(@PathVariable String id, @RequestBody GenericProductRequestDto dto){
        return productService.updateProductById(id, dto);
    }

    @PostMapping
    public GenericDto createProduct(@RequestBody GenericProductRequestDto fakeStoreProduct){
        return  productService.createProduct(fakeStoreProduct);
    }

    @DeleteMapping("/{id}")
    public GenericProductResponseDto deleteProductById(@PathVariable String id){
        return productService.deleteProductById(id);
    }
}

//@ExceptionHandler(ProductNotFoundException.class)
//private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//    ExceptionDto exceptionDto  = new ExceptionDto();
//
//    exceptionDto.setMessage(productNotFoundException.getMessage());
//    //return exceptionDto;
//    exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
//    ResponseEntity responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
//    return responseEntity;
//}
