package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.DeliveryBros;
import com.mygdx.game.sprites.Jugador;

/**
 *@author Brian Chiastellino
 * @version 1.0
 */
public class GameOver extends BaseScreen {

    private Sound sound;
    public GameOver(DeliveryBros game, Jugador jugador) {
        this.game = game;
        this.jugador = jugador;

        this.font = new BitmapFont();
        this.font.setColor(1,0,0,1);
        this.textureBackground = new Texture(Direcciones.BACKGROUND_GAMEOVER.getFilePath());
        this.textureBackground.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        this.cameraBackground = new OrthographicCamera();
        this.cameraBackground.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.cameraBackground.update();
        this.sound = DeliveryBros.manager.get("Musica/wasted.wav", Sound.class);
        sound.play();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraBackground.update();
        game.batch.setProjectionMatrix(cameraBackground.combined);

        game.batch.begin();

        game.batch.draw(textureBackground,0,0,cameraBackground.viewportWidth,cameraBackground.viewportHeight);

        font.draw(game.batch,Float.toString(jugador.getScore()),660,247);

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.setScreen(new MenuPrincipal(game));
            dispose();
        }


    }

    @Override
    public void dispose() {
        font.dispose();
        textureBackground.dispose();

        sound.stop();
        isSoundPlaying = false;
    }
    @Override
    public void show() {
        // ...

        if (!isSoundPlaying) {
            sound.play();
            isSoundPlaying = true;
        }

        // ...
    }



}
