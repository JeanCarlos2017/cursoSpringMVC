package com.globallabs.spring.web.mvc.rest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public JSONPObject indexOutofBounds(){
        return new JSONPObject("Erro", "Indice nao encontrado");
    }
}
