package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.DeliveryBros;
import com.mygdx.game.PlayScreen;

public class MenuPrincipal extends ScreenAdapter {

    private Stage stage;
    private DeliveryBros game;

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

    private final float TAMANIO_ANCHO_TUTORIAL = 200f;
    private final float TAMANIO_ALTO_TUTORIAL = 70f;
    private Image imgTutorialBoton;
    private Texture textTutorialBoton;
    private Sprite spriteTutorialBoton;

    private float alphaStage = 0f;

    private Texture background;
    private OrthographicCamera cameraBackground;
    private Music musica;
    private Sound sound;
    private boolean isSoundPlaying = false;




    //TODO: Agregar imagenes en vez de fuentes.
    //TODO: Ver si el menu puede aparecer con un fade.

    public MenuPrincipal(DeliveryBros game) {
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

        textTutorialBoton = new Texture("Botones/tutorialButton.png");
        spriteTutorialBoton = new Sprite(textTutorialBoton);
        imgTutorialBoton = new Image(spriteTutorialBoton);

        background = new Texture(Direcciones.BACKGROUND_MENU.getFilePath());
        background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);

        cameraBackground = new OrthographicCamera();
        cameraBackground.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        cameraBackground.update();

        sound = DeliveryBros.manager.get("Musica/selection.wav", Sound.class);
        sound.play();
        musica = DeliveryBros.manager.get("Musica/menu.ogg", Music.class);
        musica.setLooping(true);
        musica.play();
        musica.setVolume(0.20f);

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

        float posicionTutorialX = posicionQuitX - 80;
        float posicionTutorialY = posicionQuitY - 70;

        imgTutorialBoton.setPosition(posicionTutorialX,posicionTutorialY);

        try {
            imgStartBoton.addListener(createStartButtonListener());
            imgScoreBoton.addListener(createScoreButtonListener());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        imgQuitBoton.addListener(createQuitButtonListener());
        imgTutorialBoton.addListener(createTutorialButtonListener());



        stage.addActor(imgStartBoton);
        stage.addActor(imgScoreBoton);
        stage.addActor(imgQuitBoton);
        stage.addActor(imgTutorialBoton);

        if (!isSoundPlaying) {
            sound.play();
            isSoundPlaying = true;
        }
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

        musica.dispose();
        sound.stop();
        isSoundPlaying = false;
    }

    //TODO: Cambiar nombre al metodo Listener
    private ClickListener createStartButtonListener() throws InterruptedException {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                musica.pause();
                sound.play();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        };
    }
    //TODO: Cambiar nombre al metodo Listener
    private ClickListener createScoreButtonListener() throws InterruptedException {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                musica.pause();
                sound.play();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.setScreen(new ScoreBoard(game));
                dispose();
            }
        };
    }
    private ClickListener createTutorialButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Tutorial(game));
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
