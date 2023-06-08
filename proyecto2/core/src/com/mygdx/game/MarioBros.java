package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MarioBros extends Game {

	public SpriteBatch batch;
	public static final int V_WIDHT = 7000;;
	public static final int V_HEIGHT = 3008;;
	public static final int PPM = 100;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

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
