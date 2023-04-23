package com.example.challenge.service;


import com.example.challenge.errorUnknown.*;
import com.example.challenge.model.Persona;
import com.example.challenge.repository.PersonaRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;


    public String postPersona(Persona persona){
        try{
            if(validacion(persona) == 0){
            throw new SoloLetrasException();
        }
        if(validacion(persona) == 1){
            throw new InputsVaciosException();
        }
        this.personaRepository.save(persona);
        return "";
        }catch(ErrorUnknown errorUnknown){
           return  errorUnknown.getMessage();
        }catch(Exception e){
            return e.getMessage();
        }
    }


        public List<Persona> getAllPersonas(){
            List<Persona> list = this.personaRepository.findAll();
            return  list;
        }

    public Persona getPersonaPorId(Long id){
        try{
            Persona persona = this.personaRepository.encontrarPersonaPorId(id);
            if(persona.getId()== null){
                throw  new Throwable();
            }
            this.personaRepository.findById(id).get();
            return persona;
        }catch(Throwable throwable){
            return null;
        }
    }


    public String updatePersona(Persona persona,Long id){
        try{
            Persona updatePersona = this.personaRepository.findById(id).get();

            updatePersona.setNombre(persona.getNombre());
            updatePersona.setApellido(persona.getApellido());
            updatePersona.setDireccion(persona.getDireccion());
            updatePersona.setPais(persona.getPais());
            updatePersona.setTelefono(persona.getTelefono());
            updatePersona.setFechaNacimiento(persona.getFechaNacimiento());

            if(validacion(updatePersona)==0){
                throw new SoloLetrasException();
            }
            if(validacion(updatePersona) == 1){
                throw  new InputsVaciosException();
            }
            this.personaRepository.save(updatePersona);
            return "";
        }catch(ErrorUnknown errorUnknown){
            return errorUnknown.getMessage();
        }catch(Exception ex){
            return ex.getMessage();
        }

}
    public Persona deletePersona(Long id){
        try{
            Persona persona = this.personaRepository.encontrarPersonaPorId(id);
            if(persona.getId() == null){
                throw new Throwable();
            }
             this.personaRepository.deleteById(id);
            return persona;
        }catch(Throwable throwable){
           return null;
        }
    }

    public Integer validacion(Persona persona){
        if (soloLetras(persona.getApellido()) == false ||soloLetras(persona.getPais())== false
                || soloLetras(persona.getNombre()) == false){
            return 0;
        }
        if(persona.getFechaNacimiento() == null || persona.getDireccion() == null ||
        persona.getTelefono() == null || persona.getPais() == null || persona.getNombre() == null
        || persona.getApellido() == null){
            return 1;
        }
        return 2;
    }
    public boolean soloLetras(String cadena){
        for (int i = 0; i < cadena.length(); i++)
        {
            char caracter = cadena.toUpperCase().charAt(i);
            int valorASCII = (int)caracter;
            if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90) && valorASCII != 32)
                return false;
        }
        return true;
    }
}
