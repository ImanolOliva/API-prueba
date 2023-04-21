package com.example.challenge.errorUnknown;

public class InputsVaciosException extends  ErrorUnknown {

    public InputsVaciosException() {
        super("No pueden existir campos vacios");
    }
}
