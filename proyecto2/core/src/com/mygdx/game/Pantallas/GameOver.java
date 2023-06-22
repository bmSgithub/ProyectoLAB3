package com.mygdx.game.Pantallas;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.MarioBros;
import com.mygdx.game.sprites.Jugador;

import javax.swing.*;


public class GameOver extends ScreenAdapter {

    private MarioBros game;
    private Jugador jugador;
    private Stage stage;


    private final float TAMANIO_ANCHO_GAMEOVER = 820f;
    private final float TAMANIO_ALTO_GAMEOVER = 314f;
    private Texture textureGameOver;
    private Sprite spriteGameOver;
    private Image imageGameOver;


    public GameOver(MarioBros game, Jugador jugador) {
        this.game = game;
        this.jugador = jugador;

        this.stage = new Stage();
        this.textureGameOver = new Texture(Direcciones.BOTON_GAMEOVER.getFilePath());
        this.textureGameOver.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        this.spriteGameOver = new Sprite(textureGameOver);
        this.imageGameOver = new Image(spriteGameOver);


    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        this.imageGameOver.setSize(TAMANIO_ANCHO_GAMEOVER, TAMANIO_ALTO_GAMEOVER);

        float posicionGameOverX = (float) Gdx.graphics.getWidth() / 2 - imageGameOver.getWidth() / 2;
        float posicionGameOverY = (float) Gdx.graphics.getHeight() / 2 - imageGameOver.getHeight() / 2 + 35;

        imageGameOver.setPosition(posicionGameOverX, posicionGameOverY);

        imageGameOver.addListener(createGameOverButtonListener());

        stage.addActor(imageGameOver);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        textureGameOver.dispose();
        stage.dispose();
    }

    //TODO: Cambiar nombre al metodo Listener
    private ClickListener createGameOverButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                jugador.setNombre(JOptionPane.showInputDialog(null, "Ingrese su nombre:", "Nombre de jugador", JOptionPane.PLAIN_MESSAGE));
                guardarScore(jugador);
                game.setScreen(new MenuPrincipal(game));
                dispose();
            }
        };
    }
    public void guardarScore(Jugador jugador){
        ScoreBoard scoreBoard = new ScoreBoard(game);
        scoreBoard.agregarJugador(jugador);
    }


}
