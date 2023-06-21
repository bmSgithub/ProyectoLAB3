package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MarioBros;
import com.mygdx.game.sprites.Jugador;

import javax.swing.*;

public abstract class PantallaFinal extends ScreenAdapter {


    protected MarioBros game;
    protected Jugador jugador;
    protected Stage stage;

    protected final float TAMANIO_ANCHO_IMG = 200f;
    protected final float TAMANIO_ALTO_IMG = 70f;
    protected Texture textureBoton;
    protected Sprite spriteBoton;
    protected Image imgBoton;
    protected float posicionBotonX;
    protected float posicionBotonY;

    private final ScoreBoard scoreBoard = new ScoreBoard(game);

    public PantallaFinal(MarioBros game, Jugador jugador) {
        this.game = game;
        this.jugador = jugador;
        this.stage = new Stage();


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        this.imgBoton.setSize(this.TAMANIO_ANCHO_IMG,this.TAMANIO_ALTO_IMG);

        this.posicionBotonX = (float) Gdx.graphics.getWidth() /2 - imgBoton.getWidth() / 2;
        this.posicionBotonY = (float) Gdx.graphics.getHeight() / 2 - imgBoton.getHeight() / 2 + 35;

        this.imgBoton.setPosition(posicionBotonX,posicionBotonY);
        this.imgBoton.addListener(tocarBoton());

        this.stage.addActor(imgBoton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width,height,true);
    }

    @Override
    public void dispose() {
        textureBoton.dispose();
        stage.dispose();
    }

    public void guardarScore (Jugador jugador){
        this.scoreBoard.agregarJugador(jugador);
    }

    public ClickListener tocarBoton() {
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
}
