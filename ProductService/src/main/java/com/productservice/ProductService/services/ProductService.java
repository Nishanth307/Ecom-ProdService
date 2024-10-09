package com.productservice.ProductService.services;

import java.util.List;

import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

public interface ProductService {
      public GenericProductDto getProductById(Long id) throws ProductNotFoundException;
      public List<GenericProductDto> getAllProducts();
      public GenericProductDto deleteProduct(Long id);
      public GenericProductDto createProduct(GenericProductDto genericProductDto);
      public GenericProductDto updateProduct(Long id,GenericProductDto genericProductDto);
}
