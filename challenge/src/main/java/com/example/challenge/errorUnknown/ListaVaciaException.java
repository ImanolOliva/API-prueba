package com.example.challenge.errorUnknown;

public class ListaVaciaException extends ErrorUnknown {

    public ListaVaciaException() {
        super("La lista de empleados no se encuentra cargada");
    }
}
