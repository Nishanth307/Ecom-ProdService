package com.productservice.ProductService.services.thirdPartyClients.fakeStoreClient;

import com.productservice.ProductService.models.FakeStore.GenericProductRequestDto;
import com.productservice.ProductService.models.dtos.GenericDto;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Primary
@Component("productFakeStore")
public class FakeStoreProductAdapter implements IProductService {
    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductAdapter(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductResponseDto getProductById(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";

        ResponseEntity<GenericProductResponseDto> responseEntity = restTemplate
                .getForEntity(url, GenericProductResponseDto.class,id);

        GenericProductResponseDto fakeStoreProduct = new GenericProductResponseDto();
        if (responseEntity.hasBody()){
            fakeStoreProduct = responseEntity.getBody();
        }
        return fakeStoreProduct;
    }

    @Override
    public List<GenericProductResponseDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products";

        ResponseEntity<GenericProductResponseDto[]> responseEntity = restTemplate
                .getForEntity(url, GenericProductResponseDto[].class);

        List<GenericProductResponseDto> products = new ArrayList<>();
        if (responseEntity.getBody()!=null){
            Collections.addAll(products, responseEntity.getBody());
        }
        return products;
    }

    @Override
    public GenericDto updateProductById(String id, GenericProductRequestDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GenericProductRequestDto> requestEntity = new HttpEntity<>(productDto,headers);
        ResponseEntity<GenericDto> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, GenericDto.class,id);

        if (responseEntity.hasBody()){
            return responseEntity.getBody();
        }
        return null;
    }

    @Override
    public GenericDto createProduct(GenericProductRequestDto fakeStoreProduct) {
        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GenericProductRequestDto> requestEntity = new HttpEntity<>(fakeStoreProduct,headers);
        ResponseEntity<GenericDto> responseEntity = restTemplate.exchange(
                url,HttpMethod.POST,requestEntity, GenericDto.class);

        if (responseEntity.hasBody()){
            return responseEntity.getBody();
        }
        return null;
    }

    @Override
    public GenericProductResponseDto deleteProductById(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GenericProductResponseDto> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<GenericProductResponseDto> responseEntity = restTemplate.
                exchange(url,HttpMethod.DELETE,requestEntity, GenericProductResponseDto.class,id);
        return responseEntity.getBody();
    }
}
