package com.mygdx.game.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String model, String username) {
        super("El " + username + " no se encontro en la lista de " + model + " o la contraseña es erronea" );
    }
}
