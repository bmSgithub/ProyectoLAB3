package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.Interfaces.IRepository;
import com.mygdx.game.sprites.Jugador;
import com.mygdx.game.DeliveryBros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//TODO: Ver si podemos sacar los metodos de Jackson de esta clase.

public class ScoreBoard extends BaseScreen implements IRepository<Jugador>{
    /**
     * @autor Brian Chastellino
     * @version 1.0
     */
    private List<Jugador> listaJugadores;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File fileScore = new File(Direcciones.FILE_SCORE.getFilePath());

    public ScoreBoard(DeliveryBros game) {
        this.game = game;
        this.font = new BitmapFont();

        this.textureBackground = new Texture(Direcciones.BACKGROUND_SCOREBOARD.getFilePath());
        this.textureBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        this.cameraBackground = new OrthographicCamera();
        this.cameraBackground.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.cameraBackground.update();
        cargar();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraBackground.update();
        game.batch.setProjectionMatrix(cameraBackground.combined);

        game.batch.begin();

        game.batch.draw(textureBackground, 0, 0, cameraBackground.viewportWidth, cameraBackground.viewportHeight);

        ordenarJugadoresPorPuntuacion();

        mostrarTop10Jugadores();

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new MenuPrincipal(game));
            dispose();
        }

    }

    @Override
    public void dispose() {
        font.dispose();
        textureBackground.dispose();
    }
    /**
     *Ordena nuestra tabla de campeones por los 10 mejores puntajes
     * @retur jugador
     */
    private void ordenarJugadoresPorPuntuacion() {
        Collections.sort(listaJugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return Float.compare(jugador1.getScore(), jugador2.getScore());
            }
        });
    }

    //TODO: Cambiar posicion y agregar imagen de fondo.
    /**
     * cambiar posicion y agregar imagen al fondo
     *
     */
    private void mostrarTop10Jugadores() {

        float y = 430;
        int cont = listaJugadores.size();
        int topeMax = cont <= 10 ? cont : 10;


        for (int i = 0; i < topeMax; i++) {

            font.draw(game.batch, listaJugadores.get(i).getNombre(), 150f, y);
            font.draw(game.batch, Float.toString(listaJugadores.get(i).getScore()), 280f, y);

            y -= 33f;

        }
    }

    @Override
    public void cargar() {
        try {
            CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Jugador.class);
            this.listaJugadores = objectMapper.readValue(fileScore, type);
        } catch (IOException e) {
            this.listaJugadores = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileScore, this.listaJugadores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Jugador jugador) {
        cargar();
        jugador.setId(listaJugadores.size() + 1);
        this.listaJugadores.add(jugador);
        guardar();
    }

    @Override
    public List<Jugador> listar() {
        cargar();
        return this.listaJugadores;
    }
}
