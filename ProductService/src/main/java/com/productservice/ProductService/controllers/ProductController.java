package com.productservice.ProductService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.ProductService.client.UserServiceClient;
import com.productservice.ProductService.exceptions.InvalidTokenException;
import com.productservice.ProductService.models.dtos.*;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;
    private final UserServiceClient userServiceClient;
    public ProductController(IProductService productService, UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
    }

    @GetMapping("/{id}")
    public GenericProductResponseDto getProductById(@PathVariable String id){
        return  productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductResponseDto> getAllProducts(@RequestHeader("token") String token) throws JsonProcessingException {
        String Access = validateToken(token);
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public GenericDto updateProductById(@PathVariable String id, @RequestBody GenericProductRequestDto dto){
        return productService.updateProductById(id, dto);
    }

    @PostMapping
    public GenericDto createProduct(@RequestBody GenericProductRequestDto fakeStoreProduct){
        return  productService.createProduct(fakeStoreProduct);
    }

    @DeleteMapping("/{id}")
    public GenericProductResponseDto deleteProductById(@PathVariable String id){
        return productService.deleteProductById(id);
    }

    private String validateToken(String token) throws JsonProcessingException {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper mapper = new ObjectMapper();
        JwtPayloadDto jwtPayloadDto = mapper.readValue(payload, JwtPayloadDto.class);
        ValidateTokenDto validateTokenDto = new ValidateTokenDto(token,jwtPayloadDto.getUserId());
        String result = userServiceClient.validate(validateTokenDto);
        if (!result.contains(SessionStatus.ACTIVE.name())){
            throw new InvalidTokenException("Token is not valid");
        }
        return result;
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
