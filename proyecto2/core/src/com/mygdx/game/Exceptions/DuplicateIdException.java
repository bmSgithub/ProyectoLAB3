package com.mygdx.game.Exceptions;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(String model, int id) {
        super("La id " + id + "ya existe dentro de la lista de " + model);
    }
}
