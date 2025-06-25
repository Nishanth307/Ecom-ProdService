package com.productservice.ProductService.exceptions;

import org.springframework.boot.context.properties.bind.validation.BindValidationException;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException(){}
    public InvalidNameException(String message){
        super(message);
    }
}
