package com.mygdx.game.Pantallas;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.DeliveryBros;
import com.mygdx.game.sprites.Jugador;


public class PantallaWin extends PantallaFinal {

    public PantallaWin(DeliveryBros game, Jugador jugador) {
        super(game, jugador);

        this.textureBoton = new Texture(Direcciones.BOTON_SAVE.getFilePath());
        this.textureBoton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.spriteBoton = new Sprite(textureBoton);
        this.imgBoton = new Image(spriteBoton);
        this.textureBackground = new Texture(Direcciones.BACKGROUND_WIN.getFilePath());
        this.textureBackground.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
    }
}
