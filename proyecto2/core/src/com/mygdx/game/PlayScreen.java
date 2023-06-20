package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Tools.WorldContactListener;
import com.mygdx.game.sprites.Mario;

public class PlayScreen implements Screen {

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





    public PlayScreen(MarioBros game) {
        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        this.game = game;

        gamecam = new OrthographicCamera();

        gamePort = new FitViewport((float) MarioBros.V_WIDHT / MarioBros.PPM, (float) MarioBros.V_HEIGHT / MarioBros.PPM, gamecam);

        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MapaV2/PedidosYaMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, (float) 1 / MarioBros.PPM);

        gamecam.position.set((float) gamePort.getWorldWidth() / 2,
                (float) gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        player = new Mario(world,this);
        world.setContactListener(new WorldContactListener());








//        Codigo para el suelo
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM,rect.getHeight() / 2 / MarioBros.PPM);

            fdef.shape = shape;

            body.createFixture(fdef);
        }

//        Codigo para la tuberia
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 /MarioBros.PPM,rect.getHeight() / 2/MarioBros.PPM);

            fdef.shape = shape;
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;


            body.createFixture(fdef);
        }

//        Codigo para las coins
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM,rect.getHeight() / 2 / MarioBros.PPM);

            fdef.shape = shape;

            body.createFixture(fdef);
        }


//        Codigo para los bricks
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM,rect.getHeight() / 2 / MarioBros.PPM);

            fdef.shape = shape;

            body.createFixture(fdef);
        }

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.b2body.applyLinearImpulse(new Vector2(0,4f),player.b2body.getWorldCenter(),true);

            // aplica un impulso al cuerpo que se le aplique se aplica en el sentro ya que sino cambiaria
            //de angulo
        }if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& player.b2body.getLinearVelocity().x <= 2){
            // Se fija que si apretamos solo una vez o mantenemos apretado
            // la segunda parte controla que el movimieto no sea de una velocidad mayor a la deseada
            // se pueden hacer variables globales o enum
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0),player.b2body.getWorldCenter(),true);


        }if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& player.b2body.getLinearVelocity().x >= -2){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(),true);

        }


    }

    public void update(float dt) {

        handleInput(dt);

        world.step(1/60f,6,2); // afecta la reaccion de dos cuerpos durante una colision
        gamecam.position.x = player.b2body.getPosition().x; // Rastreamos el player
        player.update(dt);
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

        b2dr.render(world,gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();




        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();

    }
    public World getWorld(){
        return world;
    }
    public TextureAtlas getAtlas (){
        return atlas;
    }


}
