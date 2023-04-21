package com.example.challenge.errorUnknown;

public class PersonaNoExisteException extends   ErrorUnknown{

    public PersonaNoExisteException() {
        super("La persona no se encuentra cargada");
    }
}
