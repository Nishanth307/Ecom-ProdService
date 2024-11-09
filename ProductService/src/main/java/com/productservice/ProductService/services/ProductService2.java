package com.productservice.ProductService.services;

import java.util.List;
import java.util.UUID;

import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

public interface ProductService2 {
      public GenericProductDto getProductById(UUID id) throws ProductNotFoundException;
      public List<GenericProductDto> getAllProducts();
      public GenericProductDto deleteProduct(UUID id) throws ProductNotFoundException ;
      public GenericProductDto createProduct(GenericProductDto genericProductDto);
      public GenericProductDto updateProduct(UUID id, GenericProductDto genericProductDto) throws ProductNotFoundException ;
}
