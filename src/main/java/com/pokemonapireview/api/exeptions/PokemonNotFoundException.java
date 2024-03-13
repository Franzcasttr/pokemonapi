package com.pokemonapireview.api.exeptions;

public class PokemonNotFoundException extends RuntimeException{
    private static final  long serialCVersionUID = 1;

    public PokemonNotFoundException(String message) {
        super(message);
    }
}
