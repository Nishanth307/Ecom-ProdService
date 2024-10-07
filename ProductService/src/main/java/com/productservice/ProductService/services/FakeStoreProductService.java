package com.productservice.ProductService.services;

import org.springframework.stereotype.Service;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

      @Override
      public String getProductById(Long id) {
            //integrate fake store api
            return "From Service Scalar Product fetched with id: "+id;
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
