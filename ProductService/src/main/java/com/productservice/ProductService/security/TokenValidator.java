package com.productservice.ProductService.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.ProductService.client.UserServiceClient;
import com.productservice.ProductService.models.dtos.JwtPayloadDto;
import com.productservice.ProductService.models.dtos.SessionStatus;
import com.productservice.ProductService.models.dtos.ValidateTokenDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
public class TokenValidator {
    private final RestTemplateBuilder restTemplateBuilder;
    private final UserServiceClient userServiceClient;
    
    public TokenValidator(RestTemplateBuilder restTemplateBuilder, UserServiceClient userServiceClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.userServiceClient = userServiceClient;
    }
    
    public Optional<JwtObject> validateToken(String token){
        try {
            if (token == null || token.isEmpty()) {
                return Optional.empty();
            }
            
            // Decode JWT token
            String[] chunks = token.split("\\.");
            if (chunks.length != 3) {
                return Optional.empty();
            }
            
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper mapper = new ObjectMapper();
            JwtPayloadDto jwtPayloadDto = mapper.readValue(payload, JwtPayloadDto.class);
            
            // Validate token with UserService
            ValidateTokenDto validateTokenDto = new ValidateTokenDto(token, jwtPayloadDto.getUserId());
            String result = userServiceClient.validate(validateTokenDto);
            
            if (result == null || !result.contains(SessionStatus.ACTIVE.name())) {
                return Optional.empty();
            }
            
            // Check if token is expired
            long currentTime = System.currentTimeMillis();
            if (jwtPayloadDto.getExpiredAt() > 0 && currentTime > jwtPayloadDto.getExpiredAt()) {
                return Optional.empty();
            }
            
            // Build JwtObject
            JwtObject jwtObject = new JwtObject();
            jwtObject.setUserId(jwtPayloadDto.getUserId());
            jwtObject.setCreatedAt(jwtPayloadDto.getCreatedAt());
            jwtObject.setExpiryAt(jwtPayloadDto.getExpiredAt());
            
            // Convert roles from String to Role objects
            List<Role> roles = new ArrayList<>();
            if (jwtPayloadDto.getRoles() != null) {
                for (String roleName : jwtPayloadDto.getRoles()) {
                    Role role = new Role();
                    role.setRole(roleName);
                    roles.add(role);
                }
            }
            jwtObject.setRoles(roles);
            
            return Optional.of(jwtObject);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
