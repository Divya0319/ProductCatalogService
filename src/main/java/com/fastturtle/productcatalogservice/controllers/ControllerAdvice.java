package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({ProductNotFoundException.class, ArrayIndexOutOfBoundsException.class})
    private ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
