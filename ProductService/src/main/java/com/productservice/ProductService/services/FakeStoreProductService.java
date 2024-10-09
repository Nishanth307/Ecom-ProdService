package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.models.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
      private RestTemplateBuilder restTemplateBuilder ;

      private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
      private String genericProductUrl = "https://fakestoreapi.com/products";

      public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
            this.restTemplateBuilder =  restTemplateBuilder;
      }

      @Override
      public GenericProductDto getProductById(Long id) {
            //integrate fake store api
            //rest template
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> responseEntity =
                    restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class,id);
            //return "From Service Scalar Product fetched with id: "+id;

            //convert fakestoredto to generic dto before returning
            return convertToGenericProductDto(responseEntity.getBody());
      }

      @Override
      public List<GenericProductDto> getAllProducts() {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto[]> responseEntity = 
                  restTemplate.getForEntity(genericProductUrl,FakeStoreProductDto[].class);
            
            List<GenericProductDto> result = new ArrayList<>();
            List<FakeStoreProductDto> fakeStoreProductDtos = List.of(responseEntity.getBody());
            for (FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
                  result.add(convertToGenericProductDto(fakeStoreProductDto));
            }
           return result;
      }

      @Override
      public GenericProductDto createProduct(GenericProductDto genericProductDto) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> responseEntity = 
                  restTemplate.postForEntity(genericProductUrl,genericProductDto,FakeStoreProductDto.class);
            return convertToGenericProductDto(responseEntity.getBody());
      }

      @Override
      public void deleteProduct(Long id) {

      }

      @Override
      public void updateProduct(Long id) {
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
