package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Enum.*;


public class Mario extends Sprite {

    public World world; //Mundo en el que mario va a estar situado
    public Body b2body;
    private TextureRegion marioStand;

    /// Animacion y movimiento
    public State estadoActual;
    public State estadoPrevio;

    private Animation<TextureRegion> marioRun;
    private Animation<TextureRegion> marioJump;
    private float estadoTimer;
    private boolean runningRight;
    protected Fixture fixture;







    public Mario (PlayScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = screen.getWorld();
        estadoActual = State.STANDING;
        estadoPrevio = State.STANDING;
        estadoTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++){
            frames.add(new TextureRegion(getTexture(),i*16,0,16,16));
            marioRun = new Animation(0.1f,frames);

        }

            for (int i = 7;i<16;i++){
                frames.add(new TextureRegion(getTexture(),i*16,0,16,16));
                marioJump = new Animation(0.1f,frames);
            }



        defineMario();
        marioStand = new TextureRegion(getTexture(),0,0,16,16);
       setBounds(0,0,32 / MarioBros.PPM,32/MarioBros.PPM);
        ///setBounds(0,0.1f,0.5f,0.6f);
        setRegion(marioStand);
    }
    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y - getHeight() /2);
        setRegion(getFrame(dt));
    }

    public void defineMario(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(132 / MarioBros.PPM,132/ MarioBros.PPM); // definimos la posicion del comienzo
        //bdef.position.set(32,32);
        bdef.type = BodyDef.BodyType.DynamicBody; // Crea un cuerpo de tipo dinamico (Cuerpo que interactua con el entorno)
        b2body = world.createBody(bdef);

        // CREACION DE UNA NUEVA FORMA

        FixtureDef fdef =  new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/MarioBros.PPM);
        fdef.filter.categoryBits = MarioBros.MARIO_BIT;
      fdef.filter.maskBits = MarioBros.GROUND_BIT | MarioBros.COIN_BIT | MarioBros.BRICK_BIT ;
       fdef.shape = shape;
        b2body.createFixture(fdef);

        /// Creamos un sensor para la cabeza de mario para saber cuando
        /// Colisiona
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / MarioBros.PPM , 8/ MarioBros.PPM), new Vector2(2/MarioBros.PPM, 8/MarioBros.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head");

    }
    public TextureRegion getFrame(float dt){
        estadoActual = getState();
        TextureRegion region = marioStand;

        switch (estadoActual){
            case JUMPING:
                region = marioJump.getKeyFrame(estadoTimer);
                estadoActual = State.JUMPING;
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(estadoTimer,true);
                break;
            case FALLING:
                break;
            case STANDING:
                break;
            default:
                region = marioStand;
                break;
        }
        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight)&& region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        estadoTimer = estadoActual == estadoPrevio ? estadoTimer + dt : 0;
        estadoPrevio = estadoActual;
        return region;
    }
    public State getState(){
        if((b2body.getLinearVelocity().y > 0 ) || (b2body.getLinearVelocity().y <0 && estadoPrevio == State.JUMPING) ){
            return State.RUNNING;
        }else if(b2body.getLinearVelocity().y<0){
            return State.FALLING;
        }else if (b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }else {
            return State.STANDING;
        }
    }


}
