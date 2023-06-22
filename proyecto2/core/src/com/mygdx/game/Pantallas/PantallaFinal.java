package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MarioBros;
import com.mygdx.game.sprites.Jugador;
import javax.swing.*;
import java.awt.*;

import static java.awt.Color.RED;


public abstract class PantallaFinal extends ScreenAdapter {


    protected MarioBros game;
    protected Jugador jugador;
    protected Stage stage;
    protected final float TAMANIO_ANCHO_IMG = 160;
    protected final float TAMANIO_ALTO_IMG = 70f;
    protected Texture textureBoton;
    protected Sprite spriteBoton;
    protected Image imgBoton;
    protected final float posicionBotonX = 320;
    protected final float posicionBotonY = 100;

    private BitmapFont font;

    protected Texture background;
    private OrthographicCamera cameraBackground;
    protected Sound sound;
    protected Sound sound2;

    private final ScoreBoard scoreBoard = new ScoreBoard(game);


    public PantallaFinal(MarioBros game, Jugador jugador) {
        this.game = game;
        this.jugador = jugador;
        this.stage = new Stage();
        this.font = new BitmapFont();
        this.font.setColor(5f,0f,0f,1f);

        this.cameraBackground = new OrthographicCamera();
        this.cameraBackground.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.cameraBackground.update();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        this.imgBoton.setSize(this.TAMANIO_ANCHO_IMG,this.TAMANIO_ALTO_IMG);

        this.imgBoton.setPosition(posicionBotonX,posicionBotonY);
        this.imgBoton.addListener(tocarBoton());

        this.stage.addActor(imgBoton);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraBackground.update();

        game.batch.setProjectionMatrix(cameraBackground.combined);

        game.batch.begin();

        game.batch.draw(background,0,0,cameraBackground.viewportWidth,cameraBackground.viewportHeight);

        font.draw(game.batch,Float.toString(jugador.getScore()),374,191f );

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            game.setScreen(new MenuPrincipal(game));
            dispose();
        }

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
        background.dispose();
        stage.dispose();
        font.dispose();
        sound.dispose();
        sound2.dispose();
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
