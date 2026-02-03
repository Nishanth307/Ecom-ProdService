package com.productservice.ProductService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler({IllegalArgumentException.class,NullPointerException.class})
    public ResponseEntity<String> handleBadRequest(Exception exception, WebRequest webRequest){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexErrors(Exception exception,WebRequest WebRequest){
        return new ResponseEntity<String>("Something went bad at our side", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class,NumberFormatException.class})
    public ResponseEntity<String> handleIncorrectRequestFormat(Exception exception,WebRequest WebRequest){
        return new ResponseEntity<String>("Please pass userId or cartId in correct format", HttpStatus.CONFLICT);
    }
}
