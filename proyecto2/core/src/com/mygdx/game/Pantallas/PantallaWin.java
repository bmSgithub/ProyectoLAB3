package com.mygdx.game.Pantallas;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.MarioBros;
import com.mygdx.game.sprites.Jugador;


public class PantallaWin extends PantallaFinal {

    public PantallaWin(MarioBros game, Jugador jugador) {
        super(game, jugador);

        this.textureBoton = new Texture(Direcciones.BOTON_YOUWIN.getFilePath());
        this.textureBoton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.spriteBoton = new Sprite(textureBoton);
        this.imgBoton = new Image(spriteBoton);
        this.background = new Texture(Direcciones.BACKGROUND_WIN.getFilePath());
        this.background.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        this.sound = MarioBros.manager.get("Musica/dingdong.wav", Sound.class);
        sound.play();
        this.sound = MarioBros.manager.get("Musica/You-Win.wav", Sound.class);
        sound2.play();
    }

}
