package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Enum.State;
import com.mygdx.game.Pantallas.GameOver;
import com.mygdx.game.Pantallas.PantallaWin;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.WorldContactListener;
import com.mygdx.game.sprites.Jugador;
import com.mygdx.game.sprites.Mario;

public class PlayScreen extends ScreenAdapter {
   private int jumpCount =0;
    private  boolean onGround = true;

    private static final int MAX_JUMP_COUNT = 2;

    private final MarioBros game;

    private final OrthographicCamera gamecam;
    private final Viewport gamePort;
    private final Hud hud;

    private final TmxMapLoader mapLoader;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    private final Box2DDebugRenderer b2dr;
    private Mario player;


    private TextureAtlas atlas;
    private Music musica;
    private Sound sound;


    public PlayScreen(MarioBros game) {
        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        this.game = game;

        gamecam = new OrthographicCamera();

        gamePort = new FitViewport((float) MarioBros.V_WIDHT / MarioBros.PPM, (float) MarioBros.V_HEIGHT / MarioBros.PPM, gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level1/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, (float) 1 / MarioBros.PPM);

        gamecam.position.set((float) gamePort.getWorldWidth() / 2,
                (float) gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(this);


        player = new Mario(this);
        world.setContactListener(new WorldContactListener());
        musica = MarioBros.manager.get("Musica/music.ogg", Music.class);
        musica.setLooping(true);
        musica.play();
        musica.setVolume(0.20f);



    }

    public void handleInput(float dt) {

        boolean jumpKeyPressed = Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W);
        boolean rightKeyPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
        boolean leftKeyPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);

        if (jumpKeyPressed && jumpCount <= 2 && onGround) {
            player.b2body.applyLinearImpulse(new Vector2(0, 3f), player.b2body.getWorldCenter(), true);
            jumpCount++;
            if (jumpCount == 2){

            onGround = false;
            }
        }

        if (rightKeyPressed && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }

        if (leftKeyPressed && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }if (player.b2body.getLinearVelocity().y==0){
            onGroundCollision();
        }
    }

    public void onGroundCollision() {
        onGround = true;
        jumpCount = 0;
    }

    public void update(float dt) {

        handleInput(dt);

        world.step(1 / 60f, 6, 2); // afecta la reaccion de dos cuerpos durante una colision

        if (player.b2body.getPosition().x >= 2f ){
            if (player.b2body.getPosition().x <= 36.30f){
                gamecam.position.x = player.b2body.getPosition().x; // Rastreamos el player
            }
        }

        player.update(dt);

        hud.update(dt);
        gamecam.update();
        renderer.setView(gamecam);

        renderer.render();



    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);


        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        win();
        gameOver();

    }

    public void win()  {
        if (player.b2body.getPosition().x >= 37.9) {
            musica.pause();
            game.setScreen(new PantallaWin(game, new Jugador(hud.getWorldTimer())));
        }
    }

    public void gameOver() {
        if (player.b2body.getPosition().y <= -0) {
            musica.pause();
            game.setScreen(new GameOver(game,new Jugador(hud.getWorldTimer())));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void dispose() {

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        musica.dispose();

    }

    public World getWorld() {
        return world;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TiledMap getMap() {
        return map;
    }


}
