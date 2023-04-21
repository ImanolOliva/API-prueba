package com.example.challenge.service;


import com.example.challenge.errorUnknown.ErrorUnknown;
import com.example.challenge.errorUnknown.InputsVaciosException;
import com.example.challenge.errorUnknown.SoloLetrasException;
import com.example.challenge.model.Persona;
import com.example.challenge.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;


    public String postPersona(Persona persona){
        try{
            if(validacion(persona) == 0){
            throw new SoloLetrasException();
        }
        if(validacion(persona) == -0){
            throw new InputsVaciosException();
        }
        personaRepository.save(persona);
        return "";
        }catch(ErrorUnknown errorUnknown){
           return  errorUnknown.getMessage();
        }catch(Exception e){
            return e.getMessage();
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
            return -0;
        }
        return 1;
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


    public Persona getPersonaPorId(Long id){
       try{
        Persona persona = personaRepository.encontrarPersonaPorId(id);
        if(persona.getId() == null){
            throw  new NullPointerException();
        }
        this.personaRepository.findById(id).get();
        return  persona;
    }catch(NullPointerException nullPointerException){
           return  null;
       }

    }

}
