package com.mygdx.game;

import Screens.MainMenuScreen;
import Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets.Assets;

public class MainLearn extends Game {

	public SpriteBatch batch;
	public static final float V_WIDHT = 800;;
	public static final float V_HEIGHT = 480;;
	public static final float PPM = 100;
	public static final short OBJECT_BIT = 32;

	@Override
	public void create () {
		Assets.load();
		setScreen(new MainMenuScreen(this));
		//setScreen(new PlayScreen(this));

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
