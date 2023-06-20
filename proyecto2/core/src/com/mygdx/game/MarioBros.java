package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.Pantallas.MenuPrincipal;
import com.mygdx.game.Pantallas.PantallaCarga;
import com.mygdx.game.Pantallas.PantallaUTN;

public class MarioBros extends Game {

	public SpriteBatch batch;
	public static final int V_WIDHT = 400;;
	public static final int V_HEIGHT = 208;;
	public static final float PPM = 100;
	public static final short OBJECT_BIT = 32;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PantallaUTN(this,Direcciones.LOGO_UTN.getFilePath()));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
