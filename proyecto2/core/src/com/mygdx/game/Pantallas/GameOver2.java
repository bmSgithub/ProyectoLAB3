package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.MarioBros;
import com.mygdx.game.sprites.Jugador;
import com.mygdx.game.sprites.Mario;


public class GameOver2 extends PantallaFinal {

    private Sound sound;
    public GameOver2(MarioBros game, Jugador jugador) {
        super(game, jugador);

        this.textureBoton = new Texture(Direcciones.BOTON_GAMEOVER.getFilePath());
        this.textureBoton.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        this.spriteBoton = new Sprite(textureBoton);
        this.imgBoton = new Image(spriteBoton);
        this.background = new Texture(Direcciones.BACKGROUND_GAMEOVER.getFilePath());
        this.background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
    }

}
