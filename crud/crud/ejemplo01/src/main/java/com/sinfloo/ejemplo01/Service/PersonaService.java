package com.sinfloo.ejemplo01.Service;

import com.sinfloo.ejemplo01.model.Persona;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonaService {
    List<Persona> listar();

    Iterable<Persona> findByName(String name);
    Persona listarId(int id);

    void add(Persona p);

    Persona edit(Persona p);

    void delete(int id);

}
