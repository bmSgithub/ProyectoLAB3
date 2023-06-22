package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;

public class MenuPrincipal extends ScreenAdapter {

    private Stage stage;
    private MarioBros game;

    private final float TAMANIO_ANCHO_START = 200f;
    private final float TAMANIO_ALTO_START = 60f;
    private Image imgStartBoton;
    private Texture textStartBoton;
    private Sprite spriteStartBoton;

    private final float TAMANIO_ANCHO_QUIT = 200f;
    private final float TAMANIO_ALTO_QUIT = 70f;
    private Image imgQuitBoton;
    private Texture textQuitBoton;
    private Sprite spriteQuitBoton;

    private final float TAMANIO_ANCHO_SCORE = 200f;
    private final float TAMANIO_ALTO_SCORE = 60f;
    private Image imgScoreBoton;
    private Texture textScoreBoton;
    private Sprite spriteScoreBoton;

    private float alphaStage = 0f;

    private Texture background;
    private OrthographicCamera cameraBackground;



    //TODO: Agregar imagenes en vez de fuentes.
    //TODO: Ver si el menu puede aparecer con un fade.

    public MenuPrincipal(MarioBros game) {
        this.game = game;
        this.stage = new Stage();

        textStartBoton = new Texture(Direcciones.BOTON_PLAYGAME.getFilePath());
        spriteStartBoton = new Sprite(textStartBoton);
        imgStartBoton = new Image(spriteStartBoton);

        textQuitBoton = new Texture(Direcciones.BOTON_QUITGAME.getFilePath());
        spriteQuitBoton = new Sprite(textQuitBoton);
        imgQuitBoton = new Image(spriteQuitBoton);

        textScoreBoton = new Texture(Direcciones.BOTON_SOCRE.getFilePath());
        spriteScoreBoton = new Sprite(textScoreBoton);
        imgScoreBoton = new Image(spriteScoreBoton);

        background = new Texture(Direcciones.BACKGROUND_MENU.getFilePath());
        background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);

        cameraBackground = new OrthographicCamera();
        cameraBackground.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        cameraBackground.update();

    }


    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        imgStartBoton.setSize(TAMANIO_ANCHO_START, TAMANIO_ALTO_START);
        imgScoreBoton.setSize(TAMANIO_ANCHO_SCORE,TAMANIO_ALTO_SCORE);
        imgQuitBoton.setSize(TAMANIO_ANCHO_QUIT,TAMANIO_ALTO_QUIT);

        float posicionStartX = (float) Gdx.graphics.getWidth() / 2 - imgStartBoton.getWidth() / 2;
        float posicionStartY = (float) Gdx.graphics.getHeight() / 2 - imgStartBoton.getHeight() / 2 + 35;

        imgStartBoton.setPosition(posicionStartX,posicionStartY);

        float posicionScoreX = posicionStartX + 3;
        float posicionScoreY = posicionStartY - 60;

        imgScoreBoton.setPosition(posicionScoreX,posicionScoreY);

        float posicionQuitX = posicionScoreX + 2;
        float posicionQuitY = posicionScoreY - 60;

        imgQuitBoton.setPosition(posicionQuitX, posicionQuitY);

        imgStartBoton.addListener(createStartButtonListener());
        imgScoreBoton.addListener(createScoreButtonListener());
        imgQuitBoton.addListener(createQuitButtonListener());


        stage.addActor(imgStartBoton);
        stage.addActor(imgScoreBoton);
        stage.addActor(imgQuitBoton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraBackground.update();

        game.batch.setProjectionMatrix(cameraBackground.combined);

        if (alphaStage < 1){
            stage.getRoot().getColor().a = alphaStage +=0.007;
        }

        game.batch.begin();

        game.batch.draw(background,0,0,cameraBackground.viewportWidth,cameraBackground.viewportHeight);

        game.batch.end();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    //TODO: Cambiar nombre al metodo Listener
    private ClickListener createStartButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        };
    }
    //TODO: Cambiar nombre al metodo Listener
    private ClickListener createScoreButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScoreBoard(game));
                dispose();
            }
        };
    }

    //TODO: Cambiar nombre al metodo Listener
    private ClickListener createQuitButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                dispose();
            }
        };
    }


}
