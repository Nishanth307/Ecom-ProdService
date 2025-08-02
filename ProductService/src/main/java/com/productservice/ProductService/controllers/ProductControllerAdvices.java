package com.productservice.ProductService.controllers;

 import com.productservice.ProductService.exceptions.InvalidTokenException;
 import com.productservice.ProductService.exceptions.ProductNotFoundException;
 import com.productservice.ProductService.models.dtos.ExceptionDto;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
 public class ProductControllerAdvices {
       @ExceptionHandler(ProductNotFoundException.class)
       @ResponseStatus(HttpStatus.NOT_FOUND)
       @ResponseBody
       private ExceptionDto handleProductNotFoundException(ProductNotFoundException productNotFoundException){
             ExceptionDto exceptionDto  = new ExceptionDto();
             exceptionDto.setMessage(productNotFoundException.getMessage());
             exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
             return exceptionDto;
       }

       @ExceptionHandler(InvalidTokenException.class)
       private ResponseEntity<ExceptionDto> handleInvalidTokenException(Exception ex){
           ExceptionDto exceptionDto = new ExceptionDto();
           exceptionDto.setMessage(ex.getMessage());
           exceptionDto.setMessageCode(403);
           return new ResponseEntity<>(exceptionDto,HttpStatus.FORBIDDEN);
       }
 }
