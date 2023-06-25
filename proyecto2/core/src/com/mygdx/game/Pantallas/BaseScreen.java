package com.mygdx.game.Pantallas;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.DeliveryBros;
import com.mygdx.game.sprites.Jugador;
/**
 * Se utiliza como base abstracta para las demas screens
 * @author Blas Machado Susseret
 * @version 1.0
 */
public abstract class BaseScreen extends ScreenAdapter {
    protected DeliveryBros game;
    protected Jugador jugador;
    protected BitmapFont font;
    protected OrthographicCamera cameraBackground;
    protected Music musica;
    protected boolean isSoundPlaying = false;
    protected Stage stage;
    protected Texture textureBackground;
    protected Sprite sprite;
}
