package com.globallabs.spring.web.mvc.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<Map<String, Object>> indexOutofBounds( ){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Index not found");
        body.put("Retorne para :: ", "localhost8080/jedi");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
