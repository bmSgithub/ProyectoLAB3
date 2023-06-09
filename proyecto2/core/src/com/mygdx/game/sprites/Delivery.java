package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.DeliveryBros;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.Enum.*;

/**
 * @author Tomas Alberto
 * @version 1.0
 */
public class Delivery extends Sprite {


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


    public Delivery(PlayScreen screen){
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
       setBounds(0,0,20 / DeliveryBros.PPM,20/ DeliveryBros.PPM); //tamaño
        ///setBounds(0,0.1f,0.5f,0.6f);
        setRegion(marioStand);
    }
    /**
     *Actualiza la posicion de nuestro player
     * @param dt parametro delta time
     */
    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y - getHeight() /2);
        setRegion(getFrame(dt));
    }

    /**
     *Definimos la estructura de nuestro player generando un cuerpo circular y dynamico como tambien sus filtros y limitaciones
     */

    public void defineMario(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(20 / DeliveryBros.PPM, 20/ DeliveryBros.PPM); // definimos la posicion del comienzo
        //bdef.position.set(32,32);
        bdef.type = BodyDef.BodyType.DynamicBody; // Crea un cuerpo de tipo dinamico (Cuerpo que interactua con el entorno)
        b2body = world.createBody(bdef);

        // CREACION DE UNA NUEVA FORMA

        FixtureDef fdef =  new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/ DeliveryBros.PPM);
        fdef.filter.categoryBits = DeliveryBros.MARIO_BIT;
//      fdef.filter.maskBits = MarioBros.GROUND_BIT | MarioBros.COIN_BIT | MarioBros.BRICK_BIT ;
       fdef.shape = shape;
        b2body.createFixture(fdef);

        /// Creamos un sensor para la cabeza de mario para saber cuando
        /// Colisiona
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / DeliveryBros.PPM , 8/ DeliveryBros.PPM), new Vector2(2/ DeliveryBros.PPM, 8/ DeliveryBros.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head");

    }
    /**
     *Obtenemos la fraccion de textura de nuestro player segun el estado en el que se encuentra
     * @param dt parametro deltatime
     * @return region
     */
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
    /**
     *Obtenemos el estado de nuestro player
     * @return state
     */
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
