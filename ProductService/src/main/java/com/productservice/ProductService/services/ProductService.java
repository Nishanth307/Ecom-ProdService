package com.productservice.ProductService.services;

public interface ProductService {
      public String getProductById(Long id);
      public void getAllProducts();
      public void deleteProduct(Long id);
      public void createProduct();
      public void updateProduct();
}
