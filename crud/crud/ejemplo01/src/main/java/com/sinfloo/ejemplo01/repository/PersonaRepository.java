package com.sinfloo.ejemplo01.repository;

import com.sinfloo.ejemplo01.model.Persona;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Iterable<Persona> findAllByName(String name);
}
