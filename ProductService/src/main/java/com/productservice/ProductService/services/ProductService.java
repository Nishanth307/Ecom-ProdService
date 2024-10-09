package com.productservice.ProductService.services;

import java.util.List;

import com.productservice.ProductService.dtos.GenericProductDto;

public interface ProductService {
      public GenericProductDto getProductById(Long id);
      public List<GenericProductDto> getAllProducts();
      public void deleteProduct(Long id);
      public GenericProductDto createProduct(GenericProductDto genericProductDto);
      public void updateProduct(Long id);
}
