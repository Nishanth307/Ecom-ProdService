package com.productservice.ProductService.thirdPartyClients.fakeStoreClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

@Component
public class FakeStoreClientAdapter {
      private RestTemplateBuilder restTemplateBuilder ;
      

      //private String genericProductUrl = "https://fakestoreapi.com/products";

      //private String specificProductUrl = fakeStoreURL+pathForProducts+{id};

      private String genericProductUrl ;
      public FakeStoreClientAdapter(RestTemplateBuilder restTemplateBuilder,
                                    @Value("${fakestore.api.paths.url}") String fakeStoreURL,
                                    @Value("${fakestore.api.paths.products}") String pathForProducts){
            this.restTemplateBuilder =  restTemplateBuilder;
            this.genericProductUrl = fakeStoreURL + pathForProducts;
      }
      private String specificProductUrl = "https://fakestoreapi.com/products/{id}";

      
      public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
            //integrate fake store api
            //rest template
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> responseEntity =
                    restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class,id);
            //return "From Service Scalar Product fetched with id: "+id;
            FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
            if (fakeStoreProductDto==null){
                  throw new ProductNotFoundException("Product with id "+id+ " is not found!");
            }
            //convert fakestoredto to generic dto before returning
            return fakeStoreProductDto;
      }

      
      public List<FakeStoreProductDto> getAllProducts() {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto[]> responseEntity = 
                  restTemplate.getForEntity(genericProductUrl,FakeStoreProductDto[].class);
            
            List<FakeStoreProductDto> fakeStoreProductDtos = List.of(responseEntity.getBody());
            /*
            List<GenericProductDto> result = new ArrayList<>();
            for (FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
                  result.add(convertToGenericProductDto(fakeStoreProductDto));
            }
            return result;
            */
            return fakeStoreProductDtos;
      }

      
      public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> responseEntity = 
                  restTemplate.postForEntity(genericProductUrl,genericProductDto,FakeStoreProductDto.class);
            return responseEntity.getBody();
            //return convertToGenericProductDto(responseEntity.getBody());
      }

      //Generally dont return something when deleting dont return something
      // changing getForEntity to delete it
      
      public FakeStoreProductDto deleteProduct(Long id) throws ProductNotFoundException {
            RestTemplate restTemplate = restTemplateBuilder.build();
            
            RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

            ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
            //return convertToGenericProductDto(responseEntity.getBody());

            
            @SuppressWarnings("null")
            FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
            if (fakeStoreProductDto==null){
                  throw new ProductNotFoundException("Product with id "+id+ " is not found!");
            }
            //convert fakestoredto to generic dto before returning
            return fakeStoreProductDto;
            
      }

      
      public FakeStoreProductDto updateProduct(Long id,GenericProductDto genericProductDto) throws ProductNotFoundException {
            RestTemplate restTemplate = restTemplateBuilder.build();
            HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(genericProductDto);
            ResponseEntity<FakeStoreProductDto> responseEntity = 
                        restTemplate.exchange(specificProductUrl, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class, id);
            //Simple PUT method will not return anything
            //return convertToGenericProductDto(responseEntity.getBody());
            FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
            if (fakeStoreProductDto==null){
                  throw new ProductNotFoundException("Product with id "+id+ " is not found!");
            }
            //convert fakestoredto to generic dto before returning
            return fakeStoreProductDto;
      }
}
//Removing interface because returns can be different
//How does api's impact your resume