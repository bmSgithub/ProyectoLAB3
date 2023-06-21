package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.MarioBros;
import com.mygdx.game.sprites.Jugador;


import javax.swing.*;


public class GameOver2 extends PantallaFinal {

    public GameOver2(MarioBros game, Jugador jugador) {
        super(game, jugador);


        this.textureBoton = new Texture(Direcciones.BOTON_GAMEOVER.getFilePath());
        this.textureBoton.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        this.spriteBoton = new Sprite(textureBoton);
        this.imgBoton = new Image(spriteBoton);

    }

}
