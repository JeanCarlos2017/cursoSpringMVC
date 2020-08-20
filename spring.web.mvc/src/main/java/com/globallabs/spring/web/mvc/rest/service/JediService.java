package com.globallabs.spring.web.mvc.rest.service;

import com.globallabs.spring.web.mvc.model.Jedi;
import com.globallabs.spring.web.mvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Repository
public class JediService {
    @Autowired
    private JediRepository repository;

    public List<Jedi>  getAllJedi(){
        return repository.getAll();
    }

    public void addJedi(Jedi jedi){
        repository.add(jedi);
    }

    public Jedi getJediId(int id){
        try{
            return repository.getAll().get(id);
        }catch (IndexOutOfBoundsException e){
            throw e;
        }
    }

    public List<Jedi> updateJediID(int id, Jedi dto){
        try {
            repository.getAll().get(id).setName(dto.getName());
            repository.getAll().get(id).setLastName(dto.getLastName());
            return this.getAllJedi();
        }catch (IndexOutOfBoundsException e){
            throw e;
        }
    }

    public void deleteJediID(int id){
        try {
            this.getAllJedi().remove(id);
        }catch (IndexOutOfBoundsException e){
            throw e;
        }
    }
}
