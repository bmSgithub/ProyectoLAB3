package com.mygdx.game.sprites.Enemigos;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;

public class Goomba extends Enemigo{
    private float stateTime;
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;
    public Goomba(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 2; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"),i * 16,0,16,16));
            walkAnimation = new Animation<TextureRegion>(0.4f, frames);
            stateTime = 0;
            setBounds(getX(),getY(),16 / MarioBros.PPM,16/MarioBros.PPM);
        }
    }
    public void update (float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2 );
        setRegion(walkAnimation.getKeyFrame(stateTime,true));

    }

    @Override
    protected void defineEnemigo() {
        BodyDef bdef= new BodyDef();
        bdef.position.set(132 / MarioBros.PPM,132/ MarioBros.PPM); // definimos la posicion del comienzo
        //bdef.position.set(32,32);
        bdef.type = BodyDef.BodyType.DynamicBody; // Crea un cuerpo de tipo dinamico (Cuerpo que interactua con el entorno)
        b2body = world.createBody(bdef);

        // CREACION DE UNA NUEVA FORMA

        FixtureDef fdef =  new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/MarioBros.PPM);
        fdef.filter.categoryBits = MarioBros.ENEMY_BIT;
        //fdef.filter.maskBits = MarioBros.GROUND_BIT | MarioBros.COIN_BIT | MarioBros.BRICK_BIT | MarioBros.ENEMY_BIT | MarioBros.OBJECT_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
