package com.productservice.ProductService.models.dtos;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDto {
      private HttpStatus httpStatus;
      private String message;
      private Integer messageCode;
}
