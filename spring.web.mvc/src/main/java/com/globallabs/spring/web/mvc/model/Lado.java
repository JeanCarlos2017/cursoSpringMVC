package com.globallabs.spring.web.mvc.model;

import java.util.stream.Stream;

public enum Lado{
    JEDI("jedi"),
    NEUTRO("figurante"),
    SITH("sith");

    private String value;

    Lado(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Lado of(String value){
        return Stream.of(Lado.values()).
                filter(it-> it.getValue().equals(value))
                .findFirst()
                .orElseThrow();
    }
}
