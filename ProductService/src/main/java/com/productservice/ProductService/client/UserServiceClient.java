package com.productservice.ProductService.client;

import com.productservice.ProductService.models.dtos.ValidateTokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${userservice.api.url}")
    private String userServiceApiUrl;

    @Value("${userservice.api.path.validate}")
    private String userServiceValidatePath;

    public UserServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public String validate(ValidateTokenDto validateTokenDto){
        String validTokenUrl = userServiceApiUrl + userServiceValidatePath;
        RestTemplate restTemplate  = restTemplateBuilder.build();
        ResponseEntity<String> response =
                restTemplate.postForEntity(validTokenUrl, validateTokenDto, String.class);
        return response.getBody();
    }
}
