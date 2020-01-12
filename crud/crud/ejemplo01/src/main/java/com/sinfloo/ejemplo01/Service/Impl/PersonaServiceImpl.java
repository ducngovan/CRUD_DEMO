package com.sinfloo.ejemplo01.Service.Impl;


import com.sinfloo.ejemplo01.Service.PersonaService;
import com.sinfloo.ejemplo01.model.Persona;
import com.sinfloo.ejemplo01.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonaServiceImpl implements PersonaService {
@Autowired private PersonaRepository repository;

    @Override
    public List<Persona> listar() {
        return (List<Persona>) repository.findAll();
    }

    @Override
    public Iterable<Persona> findByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public Persona listarId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(Persona p) {
         repository.save(p);
    }

    @Override
    public Persona edit(Persona p) {
        return repository.save(p);
    }

    @Override
    public void delete(int id) {
       repository.deleteById(id);
    }
}
