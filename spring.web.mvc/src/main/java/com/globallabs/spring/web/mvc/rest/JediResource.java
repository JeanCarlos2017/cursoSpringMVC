package com.globallabs.spring.web.mvc.rest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.globallabs.spring.web.mvc.model.Jedi;
import com.globallabs.spring.web.mvc.rest.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
public class JediResource {
    @Autowired
    private JediService jediService;
    private JSONPObject jsonpObject;


    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi(){
        return jediService.getAllJedi();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJediID(@PathVariable("id") int id){
       return ResponseEntity.ok(jediService.getJediId(id));
    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Jedi> createJedi (@Valid @RequestBody Jedi jedi) {
        jediService.addJedi(jedi);
        return jediService.getAllJedi();
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<List<Jedi>> updateJedi (@PathVariable("id")int id, @Valid @RequestBody Jedi jedi){
        return ResponseEntity.ok(jediService.updateJediID(id, jedi));
    }

    @DeleteMapping("/api/jedi/{id}")
    public void deleteJediID(@PathVariable("id") int id){
        jediService.deleteJediID(id);
         ResponseEntity.noContent().build();

    }
}
