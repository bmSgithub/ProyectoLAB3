package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.sprites.ObjectTile.InteractiveTileObject;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
/** CONTACT LISTENER Es la clase que se llama cuando dos elementos tienen contacto/ chocan o colicionan
 * en BOX2d. Nos permite cambiar la forma o comportamiento de un objeto al colicionar
 *
 * */
public class WorldContactListener implements ContactListener {
    public WorldContactListener() {
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData()== "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA? fixB : fixA;
            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }

        }


    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
