package com.mygdx.game.Exceptions;

public class OutOfRangeException extends RuntimeException {
    public OutOfRangeException(int min, int max){
        super("El numero ingresado debe ser entre" + min + " y " + max);
    }
}
