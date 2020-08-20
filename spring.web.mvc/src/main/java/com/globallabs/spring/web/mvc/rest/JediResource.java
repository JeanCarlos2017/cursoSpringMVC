package com.globallabs.spring.web.mvc.rest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.globallabs.spring.web.mvc.model.Jedi;
import com.globallabs.spring.web.mvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
public class JediResource {
    @Autowired
    private JediRepository repository;
    private JSONPObject jsonpObject;


    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi(){
        return repository.getAll();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJediID(@PathVariable("id") int id){

        if (repository.getAll().size() <= id){
            jsonpObject = new JSONPObject("Erro", "Indice nao encontrado");
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(repository.getAll().get(id));
        }
    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Jedi> createJedi (@Valid @RequestBody Jedi jedi) {
        repository.add(jedi);
        return repository.getAll();
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<List<Jedi>> updateJedi (@PathVariable("id")int id, @Valid @RequestBody Jedi jedi){
        if (repository.getAll().size() <= id){
            jsonpObject = new JSONPObject("Erro", "Indice nao encontrado");
            return ResponseEntity.notFound().build();
        }else {
            repository.getAll().get(id).setName(jedi.getName());
            repository.getAll().get(id).setLastName(jedi.getLastName());
            return ResponseEntity.ok(repository.getAll());
        }
    }

    @DeleteMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> deleteJediID(@PathVariable("id") int id){
        if (repository.getAll().size() <= id){
            return ResponseEntity.notFound().build();
        }else {
            repository.getAll().remove(id);
            return ResponseEntity.noContent().build();
        }
    }
}
