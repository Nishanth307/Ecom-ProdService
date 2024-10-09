package com.productservice.ProductService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.productservice.ProductService.dtos.ExceptionDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

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
}
