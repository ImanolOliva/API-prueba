package com.example.challenge.controller;


import com.example.challenge.errorUnknown.ErrorUnknown;
import com.example.challenge.errorUnknown.PersonaNoExisteException;
import com.example.challenge.model.Persona;
import com.example.challenge.service.PersonaService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api")
public class PersonaController{

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(PersonaController.class);
    @Autowired
    PersonaService personaService;

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/persona"
    )
    public ResponseEntity<Persona> cargarPersona(@Valid @RequestBody Persona persona){
         String leyenda = personaService.postPersona(persona);
         logger.info("Ver que me devuelve el metodo" + leyenda);
         if(leyenda.isEmpty()){
             return new ResponseEntity<>(persona,HttpStatusCode.valueOf(200));
         }
        return new ResponseEntity(leyenda,HttpStatusCode.valueOf(400));
    }
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/persona/{id}"
    )
    public ResponseEntity<Persona> cargarPersona(@PathVariable("id") Long id){

           Persona persona =  this.personaService.getPersonaPorId(id);
           return  new ResponseEntity<>(persona,HttpStatusCode.valueOf(200));
        }
    }

