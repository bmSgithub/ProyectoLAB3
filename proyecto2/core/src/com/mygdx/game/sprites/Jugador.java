package com.mygdx.game.sprites;

import java.io.Serializable;
import java.util.Objects;
/**
 * @author Brian Chiastellino
 * @version 1.0
 */
public class Jugador implements Serializable {


    private int id;
    private String nombre;
    private int score;

    public Jugador(int score) {
        this.score = score;
    }

    public Jugador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return id == jugador.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", score=" + score +
                '}';
    }
}
