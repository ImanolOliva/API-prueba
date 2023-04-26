package com.example.challenge.controller;


import com.example.challenge.errorUnknown.ErrorUnknown;
import com.example.challenge.errorUnknown.ListaVaciaException;
import com.example.challenge.errorUnknown.PersonaNoExisteException;
import com.example.challenge.model.Persona;
import com.example.challenge.service.PersonaService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api")
public class PersonaController{

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(PersonaController.class);


    @Autowired
     private PersonaService personaService;

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/persona",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Persona> cargarPersona(@Valid @RequestBody Persona persona){
         String leyenda = this.personaService.postPersona(persona);
         logger.info("Ver que me devuelve el metodo" + leyenda);
         if(leyenda.isEmpty()){
             return new ResponseEntity<>(persona,HttpStatusCode.valueOf(200));
         }
        return new ResponseEntity(leyenda,HttpStatusCode.valueOf(400));
    }



    @RequestMapping(
            method = RequestMethod.GET,
            path = "/persona",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Persona> obtenerPersonas(Persona persona){
            List<Persona> list = this.personaService.getAllPersonas();
            if(list.isEmpty()){
                return new ResponseEntity(list,HttpStatusCode.valueOf(400));
            }
            return new ResponseEntity(list,HttpStatusCode.valueOf(200));

    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/persona/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Persona> obtenerPersona(@PathVariable("id") Long id){

        Persona persona =  this.personaService.getPersonaPorId(id);
        logger.info("Ver que me devuelve el metodo");
        if(this.personaService.getPersonaPorId(id)== null){
            return new ResponseEntity("Id no se encuentra cargado",HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(persona,HttpStatusCode.valueOf(200));
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/persona/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizarPersona(@PathVariable("id") Long id,@RequestBody Persona persona){

        persona = this.personaService.updatePersona(persona,id);
        if(persona != null){
            return  new ResponseEntity<>(persona,HttpStatusCode.valueOf(200));
        }
        return  new ResponseEntity("no se pudo actualizar la persona",HttpStatusCode.valueOf(400));
    }



    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/persona/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<Persona> eliminarPersona(@PathVariable("id") Long id){

        Persona persona = this.personaService.deletePersona(id);
        logger.info("Veo que me devuelve el metodo" + persona);
        if(persona != null){
            return  new ResponseEntity(persona,HttpStatusCode.valueOf(200));
        }
        return  new ResponseEntity("Id no encontrado",HttpStatusCode.valueOf(400));
    }



    @RequestMapping(
            method = RequestMethod.GET,
            path = "/personaPorPais",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Persona> obtenerPersonaPorPais(){
        List<Persona> personaList = (List<Persona>) this.personaService.personasPorPais();
        if(personaList.isEmpty()){
            return new ResponseEntity("La lista esta vacia",HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity(personaList,HttpStatusCode.valueOf(200));
    }

}



