package com.productservice.ProductService.security;

import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenValidator {
      //this method should call the UserService to validate the token
      //if token is validthen corresponding object else return empty
      RestTemplateBuilder restTemplateBuilder ;
      private TokenValidator(RestTemplateBuilder restTemplateBuilder){
            this.restTemplateBuilder =  restTemplateBuilder ; 
      }
      
      public Optional<JwtObject> validateToken(String token){
            RestTemplate restTemplate = restTemplateBuilder.build();
            //Make an HTTP call to UserService to call the validator method 
             
            return Optional.empty();
      }
}
