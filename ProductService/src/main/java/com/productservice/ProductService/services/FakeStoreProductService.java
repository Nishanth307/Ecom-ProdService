package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.models.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
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

      //Generally dont return something when deleting dont return something
      // changing getForEntity to delete it
      @Override
      public GenericProductDto deleteProduct(Long id) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            
            RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

            ResponseEntity<FakeStoreProductDto> responseEntity = 
                        restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
            return convertToGenericProductDto(responseEntity.getBody());
      }

      @Override
      public GenericProductDto updateProduct(Long id,GenericProductDto genericProductDto) {
            RestTemplate restTemplate = restTemplateBuilder.build();

            HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(genericProductDto);
            ResponseEntity<FakeStoreProductDto> responseEntity = 
                        restTemplate.exchange(specificProductUrl, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class, id);
            //Simple PUT method will not return anything
            return convertToGenericProductDto(responseEntity.getBody());
      }



      private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(fakeStoreProductDto.getId());
            genericProductDto.setImage(fakeStoreProductDto.getImage());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
            genericProductDto.setTitle(fakeStoreProductDto.getTitle());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());
            return genericProductDto;
      }
      
      /*
      @Override
      public GenericProductDto deleteProduct(Long id) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> responseEntity = 
                        restTemplate.exchange(specificProductUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
            FakeStoreProductDto productDto = responseEntity.getBody();
            if (productDto == null) {
                  throw new RuntimeException("Failed to delete product or product not found.");
            }
            return convertToGenericProductDto(productDto);
      }
       */
}
