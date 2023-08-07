package com.example.tradingapp.controller;

import com.example.tradingapp.exception.ProcessorException;
import com.example.tradingapp.exception.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<String> handleException(RepositoryException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ProcessorException.class)
    public ResponseEntity<String> handleException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error. Try again later.");
    }
}
