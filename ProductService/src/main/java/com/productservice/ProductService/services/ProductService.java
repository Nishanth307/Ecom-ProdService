package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.GenericProductDto;

public interface ProductService {
      public GenericProductDto getProductById(Long id);
      public void getAllProducts();
      public void deleteProduct(Long id);
      public void createProduct();
      public void updateProduct();
}
