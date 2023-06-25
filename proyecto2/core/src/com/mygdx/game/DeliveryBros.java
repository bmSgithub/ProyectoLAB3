package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Pantallas.PantallaUTN;

/**
 *@author Tomas Alberto
 * @version 1.0
 */
public class DeliveryBros extends Game {


	public SpriteBatch batch;
	public static final int V_WIDHT = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public  static  final  short GROUND_BIT =1;
	public static final  short MARIO_BIT = 2;
	public static final  short BRICK_BIT = 4;
	public static final  short COIN_BIT = 8;
	public static final  short DESTROYED_BIT = 16;
	public static final  short ENEMY_BIT = 64;
	public static final short OBJECT_BIT = 32;
	public  static AssetManager manager;

	@Override
	public void create () {

		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("Musica/menu.ogg", Music.class);
		manager.load("Musica/music.ogg", Music.class);
		manager.load("Musica/champions.ogg", Music.class);
		manager.load("Musica/dingdong.wav", Sound.class);
		manager.load("Musica/You-Win.wav", Sound.class);
		manager.load("Musica/wasted.wav", Sound.class);
		manager.load("Musica/death.wav", Sound.class);
		manager.load("Musica/jump.wav", Sound.class);
		manager.load("Musica/selection.wav", Sound.class);
		manager.finishLoading();

		setScreen(new PantallaUTN(this));

	}

	@Override
	public void render () {
		super.render();
		manager.update();
	}

	@Override
	public void dispose () {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}
