package com.productservice.ProductService.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder().build();
    }
}
