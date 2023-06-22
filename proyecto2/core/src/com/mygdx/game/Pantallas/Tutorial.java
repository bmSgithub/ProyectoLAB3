package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DeliveryBros;

public class Tutorial extends ScreenAdapter {

    private DeliveryBros game;
    private Texture background;
    private OrthographicCamera cameraBackground;


    public Tutorial(DeliveryBros game) {
        this.game = game;
        this.background = new Texture("Botones/tutorialMenu.png");
        this.background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.cameraBackground = new OrthographicCamera();
        this.cameraBackground.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.cameraBackground.update();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraBackground.update();
        game.batch.setProjectionMatrix(cameraBackground.combined);

        game.batch.begin();

        game.batch.draw(background, 0, 0, cameraBackground.viewportWidth, cameraBackground.viewportHeight);


        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new MenuPrincipal(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
