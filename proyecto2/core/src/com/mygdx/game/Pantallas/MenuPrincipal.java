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

    private float alphaStage = 0f;



//    private Texture background;
//    private Sprite backgroundSprite;
//    private Viewport viewportBackground;

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




//        background = new Texture(Direcciones.MENU_BACKGOUNRD.getFilePath());
//        background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
//        backgroundSprite = new Sprite(background);
//        viewportBackground = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        imgStartBoton.setSize(TAMANIO_ANCHO_START, TAMANIO_ALTO_START);
        imgQuitBoton.setSize(TAMANIO_ANCHO_QUIT,TAMANIO_ALTO_QUIT);

        float posicionStartX = (float) Gdx.graphics.getWidth() / 2 - imgStartBoton.getWidth() / 2;
        float posicionStartY = (float) Gdx.graphics.getHeight() / 2 - imgStartBoton.getHeight() / 2 + 35;

        imgStartBoton.setPosition(posicionStartX,posicionStartY);

        float posicionQuitX = posicionStartX + 3;
        float posicionQuitY = posicionStartY - 60;

        imgQuitBoton.setPosition(posicionQuitX, posicionQuitY);

        imgStartBoton.addListener(createStartButtonListener());
        imgQuitBoton.addListener(createQuitButtonListener());

        stage.addActor(imgStartBoton);
        stage.addActor(imgQuitBoton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(210, 210, 210, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (alphaStage < 1){
            stage.getRoot().getColor().a = alphaStage +=0.007;
        }

        stage.act(delta);
        stage.draw();


//      font.draw(game.batch,"PEDIDOS YA GAME", (float) Gdx.graphics.getWidth() / 2 - 45,Gdx.graphics.getHeight() - 45);
//      backgroundSprite.draw(game.batch);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
//        viewportBackground.update(width,height);
//        backgroundSprite.setSize(viewportBackground.getWorldWidth(), viewportBackground.getWorldHeight());
    }

    @Override
    public void dispose() {
//        background.dispose();
        stage.dispose();
    }

    private ClickListener createStartButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        };
    }

    private ClickListener createQuitButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        };
    }
}
