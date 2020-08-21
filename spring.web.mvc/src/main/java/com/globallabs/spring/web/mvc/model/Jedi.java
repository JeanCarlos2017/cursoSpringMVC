package com.globallabs.spring.web.mvc.model;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Jedi {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 20, message = "Name must have between 3 and 20 letters")
    private String name;
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 20, message = "Last Name must not have more than 20 letters")
    private String lastName;
    private Long id;
    private Lado lado;
    private static Long qntJedis = 0L;

    public Jedi (final String name, final String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.id = qntJedis;
        qntJedis++;
    }

    public Jedi(String name, String lastName, Lado lado) {
        this.name = name;
        this.lastName = lastName;
        this.lado = lado;
        this.id = qntJedis;
        qntJedis++;
    }

    public Jedi () {
        this.id = qntJedis;
        qntJedis++;
    }
    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(Long id){
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (final String name) {
        this.name = name;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (final String lastName) {
        this.lastName = lastName;
    }

    public Lado getLado() {
        return lado;
    }

    public void setLado(Lado lado) {
        this.lado = lado;
    }
}