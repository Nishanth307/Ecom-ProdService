package com.productservice.ProductService.services;

import java.util.List;
import java.util.UUID;

import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

public interface ProductService {
      public GenericProductDto getProductById(String authToken,Long id) throws ProductNotFoundException;
      public List<GenericProductDto> getAllProducts();
      public GenericProductDto deleteProduct(Long id) throws ProductNotFoundException ;
      public GenericProductDto createProduct(GenericProductDto genericProductDto);
      public GenericProductDto updateProduct(Long id, GenericProductDto genericProductDto) throws ProductNotFoundException ;
}
