package com.mygdx.game.sprites;

import java.io.Serializable;

public class Jugador  implements Serializable {

    //TODO: Ver si puede extener de Mario para que tenga los atributos y metodos. Y se guarda esta clase en el .json
    //TODO: Agregar un ID Auto Incremental.

    private String nombre;
    private int score;

    public Jugador(String nombre, int score) {
        this.nombre = nombre;
        this.score = score;
    }

    public Jugador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return  "Nombre='" + nombre + '\n' +
                "Score=" + score + '\n';
    }
}
