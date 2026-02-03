package com.productservice.ProductService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.ProductService.client.UserServiceClient;
import com.productservice.ProductService.exceptions.InvalidTokenException;
import com.productservice.ProductService.models.dtos.*;
import com.productservice.ProductService.security.JwtObject;
import com.productservice.ProductService.security.TokenValidator;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;
    private final UserServiceClient userServiceClient;
    private final TokenValidator tokenValidator;
    
    public ProductController(IProductService productService, UserServiceClient userServiceClient, TokenValidator tokenValidator) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
        this.tokenValidator = tokenValidator;
    }

    @GetMapping("/{id}")
    public GenericProductResponseDto getProductById(@PathVariable String id){
        if (id==null || id.isEmpty()){
            throw new IllegalArgumentException("Product Id is null or empty");
        }
        // Validate UUID format
        try {
            java.util.UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Product ID must be a valid UUID format");
        }
        return  productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductResponseDto> getAllProducts(@RequestHeader("token") String token) throws JsonProcessingException {
        validateToken(token);
        return productService.getAllProducts();
    }

    @GetMapping("all/{pageNumber}")
    public Page<GenericProductResponseDto> getAllProductsFiltered(@PathVariable("pageNumber") int pageNumber) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must be non-negative");
        }
        return productService.getAllProductsFiltered(pageNumber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericDto> updateProductById(
            @PathVariable String id, 
            @RequestBody GenericProductRequestDto dto,
            @RequestHeader(value = "token", required = false) String token){
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Product Id is null or empty");
        }
        if (dto == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }
        
        // Check authorization - only ADMIN can update products
        if (!hasAdminRole(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        // Validate UUID format
        try {
            java.util.UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Product ID must be a valid UUID format");
        }
        return ResponseEntity.ok(productService.updateProductById(id, dto));
    }

    @PostMapping
    public ResponseEntity<GenericDto> createProduct(
            @RequestBody GenericProductRequestDto productDto,
            @RequestParam(value="id",required = false) Long id,
            @RequestHeader(value = "token", required = false) String token) {
        if (productDto == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }
        if (productDto.getTitle() == null || productDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Product title is required");
        }
        
        // Check authorization - only ADMIN can create products
        if (!hasAdminRole(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductResponseDto> deleteProductById(
            @PathVariable String id,
            @RequestHeader(value = "token", required = false) String token){
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Product Id is null or empty");
        }
        
        // Check authorization - only ADMIN can delete products
        if (!hasAdminRole(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        // Validate UUID format
        try {
            java.util.UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Product ID must be a valid UUID format");
        }
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    private void validateToken(String token) throws JsonProcessingException {
        if (token == null || token.isEmpty()) {
            throw new InvalidTokenException("Token is missing");
        }
        String[] chunks = token.split("\\.");
        if (chunks.length != 3) {
            throw new InvalidTokenException("Invalid token format");
        }
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper mapper = new ObjectMapper();
        JwtPayloadDto jwtPayloadDto = mapper.readValue(payload, JwtPayloadDto.class);
        ValidateTokenDto validateTokenDto = new ValidateTokenDto(token, jwtPayloadDto.getUserId());
        String result = userServiceClient.validate(validateTokenDto);
        if (result == null || !result.contains(SessionStatus.ACTIVE.name())){
            throw new InvalidTokenException("Token is not valid");
        }
    }

    private boolean hasAdminRole(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        Optional<JwtObject> jwtObject = tokenValidator.validateToken(token);
        if (jwtObject.isEmpty()) {
            return false;
        }
        if (jwtObject.get().getRoles() == null) {
            return false;
        }
        return jwtObject.get().getRoles().stream()
            .anyMatch(role -> "ADMIN".equalsIgnoreCase(role.getRole()));
    }

}

//@ExceptionHandler(ProductNotFoundException.class)
//private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//    ExceptionDto exceptionDto  = new ExceptionDto();
//
//    exceptionDto.setMessage(productNotFoundException.getMessage());
//    //return exceptionDto;
//    exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
//    ResponseEntity responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
//    return responseEntity;
//}
