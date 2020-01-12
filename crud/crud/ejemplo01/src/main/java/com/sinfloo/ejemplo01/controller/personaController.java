package com.sinfloo.ejemplo01.controller;

import com.sinfloo.ejemplo01.Service.PersonaService;
import com.sinfloo.ejemplo01.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
public class personaController {
    @Autowired
    PersonaService personaService;

    @RequestMapping(value = "/personas", method = RequestMethod.GET)
    public ResponseEntity<List<Persona>> showAll() {
        List<Persona> personas = personaService.listar();
        if (personas.isEmpty()) {
            return new ResponseEntity<List<Persona>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/personas", method = RequestMethod.POST)
    public ResponseEntity<Void> savaPersona(@RequestBody Persona persona, UriComponentsBuilder ucBuilder) {
         personaService.add(persona);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/personas/{id}").buildAndExpand(persona.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/personas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Persona> getPersona(@PathVariable("id") int id){
        Persona _persona = personaService.listarId(id);
        if(_persona == null){
            System.out.println("khong tim thay phan tu");
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Persona>(_persona, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/personas/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Persona> editPersona(@RequestBody Persona persona, @PathVariable int id){
        Persona _persona = personaService.listarId(id);
        if(_persona == null){
            System.out.println("khong tim thay phan tu");
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }else{
            _persona.setId(persona.getId());
            _persona.setName(persona.getName());
            _persona.setApellidos(persona.getApellidos());
            personaService.add(_persona);
            return new ResponseEntity<Persona>(_persona, HttpStatus.OK);
        }
    }
@RequestMapping(value = "/personas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Persona> deleteParsona(@PathVariable int id){
        Persona persona = personaService.listarId(id);
        if(persona == null){
            System.out.println("khong ton tai phan tu");
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }else{
            personaService.delete(id);
            return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
        }
}


    @RequestMapping(value = "/personas/search-by-name", method = RequestMethod.POST)
    public ResponseEntity<?> searchByName(@RequestParam("search") Optional<String> search){
        Iterable<Persona> persona;
        if (search.isPresent()){
            persona = personaService.findByName(search.get());
        } else {
            persona = personaService.listar();
        }
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

}
