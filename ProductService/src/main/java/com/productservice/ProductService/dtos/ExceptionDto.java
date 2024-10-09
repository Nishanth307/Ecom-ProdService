package com.productservice.ProductService.dtos;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDto {
      private HttpStatus httpStatus;
      private String message;
}
