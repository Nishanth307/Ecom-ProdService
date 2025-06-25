package com.productservice.ProductService.services.thirdPartyClients.fakeStoreClientAdapter;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RequestCallback;
//import org.springframework.web.client.ResponseExtractor;
//import org.springframework.web.client.RestTemplate;
//
//import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
//import com.productservice.ProductService.exceptions.ProductNotFoundException;
//
//@Component("fakeStoreAbc")
//public class FakeStoreClientAdapter {
//      private RestTemplateBuilder restTemplateBuilder ;
//
//
////      private String genericProductUrl = "https://fakestoreapi.com/products";
//
//      //private String specificProductUrl = fakeStoreURL+pathForProducts+{id};
//
//      private String genericProductUrl ;
//      public FakeStoreClientAdapter(RestTemplateBuilder restTemplateBuilder,
//                                    @Value("${fakestore.api.paths.url}") String fakeStoreURL,
//                                    @Value("${fakestore.api.paths.products}") String pathForProducts){
//            this.restTemplateBuilder =  restTemplateBuilder;
//            this.genericProductUrl = fakeStoreURL + pathForProducts;
//      }
//      private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
//
//      public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
//            RestTemplate restTemplate = restTemplateBuilder.build();
//            ResponseEntity<FakeStoreProductDto> responseEntity =
//                    restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class,id);
//            FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
//            if (fakeStoreProductDto==null){
//                  throw new ProductNotFoundException("Product with id "+id+ " is not found!");
//            }
//            return fakeStoreProductDto;
//      }
//
//      public List<FakeStoreProductDto> getAllProducts() {
//            RestTemplate restTemplate = restTemplateBuilder.build();
//            ResponseEntity<FakeStoreProductDto[]> responseEntity =
//                  restTemplate.getForEntity(genericProductUrl,FakeStoreProductDto[].class);
//
//            List<FakeStoreProductDto> fakeStoreProductDtos = List.of(responseEntity.getBody());
//            return fakeStoreProductDtos;
//      }
//
//
//      public FakeStoreProductDto createProduct(GenericProductResponseDto genericProductResponseDto) {
//            RestTemplate restTemplate = restTemplateBuilder.build();
//            ResponseEntity<FakeStoreProductDto> responseEntity =
//                  restTemplate.postForEntity(genericProductUrl, genericProductResponseDto,FakeStoreProductDto.class);
//            return responseEntity.getBody();
//      }
//
//      public FakeStoreProductDto deleteProduct(Long id) throws ProductNotFoundException {
//            RestTemplate restTemplate = restTemplateBuilder.build();
//
//            RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
//		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
//
//            ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
//            @SuppressWarnings("null")
//            FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
//            if (fakeStoreProductDto==null){
//                  throw new ProductNotFoundException("Product with id "+id+ " is not found!");
//            }
//            return fakeStoreProductDto;
//
//      }
//
//      public FakeStoreProductDto updateProduct(Long id, GenericProductResponseDto genericProductResponseDto) throws ProductNotFoundException {
//            RestTemplate restTemplate = restTemplateBuilder.build();
//            HttpEntity<GenericProductResponseDto> requestEntity = new HttpEntity<>(genericProductResponseDto);
//            ResponseEntity<FakeStoreProductDto> responseEntity =
//                        restTemplate.exchange(specificProductUrl, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class, id);
//            FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
//            if (fakeStoreProductDto==null){
//                  throw new ProductNotFoundException("Product with id "+id+ " is not found!");
//            }
//            return fakeStoreProductDto;
//      }
//}
