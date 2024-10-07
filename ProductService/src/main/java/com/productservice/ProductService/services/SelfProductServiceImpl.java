package com.productservice.ProductService.services;

import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {

      @Override
      public String getProductById(Long id) {
            return null;
      }

      @Override
      public void getAllProducts() {
      }

      @Override
      public void deleteProduct(Long id) {
      }

      @Override
      public void createProduct() {
      }

      @Override
      public void updateProduct() {
      }
      
}