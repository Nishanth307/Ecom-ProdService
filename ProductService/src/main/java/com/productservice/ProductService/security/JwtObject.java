package com.productservice.ProductService.security;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtObject {//authentication at api level, Normally all models are placed at one place
      //this is kind of dto object  
      private String email;
      private Long userId; 
      private Long expiryAt; 
      private Long createdAt; 
      private List<Role> roles;//check A/B has excess
      
}
