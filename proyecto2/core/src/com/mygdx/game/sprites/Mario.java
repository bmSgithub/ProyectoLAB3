package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;

public class Mario extends Sprite {

    public World world; //Mundo en el que mario va a estar situado
    public Body b2body;


    public Mario (World world){
        this.world = world;

        defineMario();
    }

    public void defineMario(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(32,32); // definimos la posicion del comienzo
        //bdef.position.set(32,32);
        bdef.type = BodyDef.BodyType.DynamicBody; // Crea un cuerpo de tipo dinamico (Cuerpo que interactua con el entorno)
        b2body = world.createBody(bdef);

        // CREACION DE UNA NUEVA FORMA

        FixtureDef fdef =  new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.09f);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

}
