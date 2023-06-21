package com.mygdx.game.Pantallas.Interfaces;

import com.mygdx.game.sprites.Jugador;

import java.util.List;

public interface IJackson {

    void cargarJugadores();
    void guardarJugadores();

    void agregarJugador(Jugador jugador);

    List<Jugador> listarJugadores ();

}
