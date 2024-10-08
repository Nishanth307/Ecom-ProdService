package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
      private RestTemplateBuilder restTemplateBuilder ;

      private String getProductUrl = "https://fakestoreapi.com/products/2";

      public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
            this.restTemplateBuilder =  restTemplateBuilder;
      }

      @Override
      public GenericProductDto getProductById(Long id) {
            
            //integrate fake store api
            //rest template
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> responseEntity =
                    restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class);
            //return "From Service Scalar Product fetched with id: "+id;

            //convert fakestoredto to generic dto before returning
            return convertToGenericProductDto(responseEntity.getBody());
            
                                                
            
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

      private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
            GenericProductDto genericProductDto = GenericProductDto.builder()
                                                      .id(fakeStoreProductDto.getId())
                                                      .image(fakeStoreProductDto.getImage())
                                                      .description(fakeStoreProductDto.getDescription())
                                                      .category(fakeStoreProductDto.getCategory())
                                                      .title(fakeStoreProductDto.getTitle())
                                                      .price(fakeStoreProductDto.getPrice())
                                                      .build();
            return genericProductDto;
      }
      
}
