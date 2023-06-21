package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.sprites.Jugador;
import com.mygdx.game.MarioBros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//TODO: Ver si podemos sacar los metodos de Jackson de esta clase.

public class ScoreBoard extends ScreenAdapter implements IJackson {

    private MarioBros game;
    private BitmapFont font;


    private List<Jugador> listaJugadores;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File fileScore = new File(Direcciones.FILE_SCORE.getFilePath());

    public ScoreBoard(MarioBros game) {
        this.game = game;
        font = new BitmapFont();

        cargarJugadores();


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        ordenarJugadoresPorPuntuacion();

        mostrarJugadoresScores();

        game.batch.end();

    }

    @Override
    public void dispose() {
        font.dispose();
    }

    private void ordenarJugadoresPorPuntuacion() {

        Collections.sort(listaJugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return Integer.compare(jugador2.getScore(), jugador1.getScore());
            }
        });
    }

    private void mostrarJugadoresScores() {

        float y = Gdx.graphics.getHeight() - 50;

        for (Jugador jugador : listaJugadores) {
            font.draw(game.batch, jugador.getNombre() + ": " + jugador.getScore(), 50, y);
            y -= 40;
        }
    }

    @Override
    public void cargarJugadores() {
        try {
            CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Jugador.class);
            this.listaJugadores = objectMapper.readValue(fileScore, type);
        } catch (IOException e) {
            this.listaJugadores = new ArrayList<>();
        }
    }

    @Override
    public void guardarJugadores() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileScore, this.listaJugadores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregarJugador(Jugador jugador) {
        cargarJugadores();
        this.listaJugadores.add(jugador);
        guardarJugadores();
    }

    @Override
    public List<Jugador> listarJugadores() {
        cargarJugadores();
        return this.listaJugadores;
    }
}
