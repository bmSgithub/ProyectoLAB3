package Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MainLearn;

public abstract class BaseScreen extends InputAdapter implements Screen {
    public static final float V_WIDHT = 800;
    public static final float V_HEIGHT = 480;
    public static final float WORLD_WIDHT = 8f;
    public static final float WORLD_HEIGHT = 4.8f;
    public MainLearn game;
    public OrthographicCamera oCamUI;
    public OrthographicCamera oCamBox2D;
    public SpriteBatch spriteBatch;
    public Stage stage;

    public BaseScreen(MainLearn game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(V_WIDHT, WORLD_HEIGHT));
        oCamUI = new OrthographicCamera(V_WIDHT, V_HEIGHT);
        oCamUI.position.set(V_WIDHT/2f, V_HEIGHT/2f, 0);
        oCamBox2D = new OrthographicCamera(WORLD_WIDHT, WORLD_HEIGHT);
        oCamBox2D.position.set(WORLD_WIDHT/2f, WORLD_HEIGHT/2f, 0);
        InputMultiplexer input = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(input);
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        update(delta);
        stage.act(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        draw(delta);
        stage.draw();
    }

    public abstract void draw(float delta);
    public abstract void update(float delta);

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK){
            if(this instanceof MainMenuScreen){
                Gdx.app.exit();
            }else{
                game.setScreen(new MainMenuScreen(game));
            }

        }
        return super.keyDown(keycode);
    }
}
