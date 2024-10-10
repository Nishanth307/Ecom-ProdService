package com.productservice.ProductService.thirdPartyClients.flipkartClient;

import java.util.List;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

public class FlipKartClient {

      public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
      }

      
      public List<FakeStoreProductDto> getAllProducts() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
      }

      
      public FakeStoreProductDto deleteProduct(Long id) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
      }

      
      public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
      }

      
      public FakeStoreProductDto updateProduct(Long id, GenericProductDto genericProductDto) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
      }

}
