package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> globalHandler(RuntimeException e){
        e.printStackTrace();
        return new ResponseEntity<Map<String,String>>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
