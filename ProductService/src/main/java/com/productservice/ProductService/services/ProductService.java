package com.productservice.ProductService.services;

import java.util.List;

import com.productservice.ProductService.dtos.GenericProductDto;

public interface ProductService {
      public GenericProductDto getProductById(Long id);
      public List<GenericProductDto> getAllProducts();
      public GenericProductDto deleteProduct(Long id);
      public GenericProductDto createProduct(GenericProductDto genericProductDto);
      public GenericProductDto updateProduct(Long id,GenericProductDto genericProductDto);
}
