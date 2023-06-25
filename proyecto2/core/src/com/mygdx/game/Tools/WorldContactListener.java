package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
/** CONTACT LISTENER Es la clase que se llama cuando dos elementos tienen contacto/ chocan o colicionan
 * en BOX2d. Nos permite cambiar la forma o comportamiento de un objeto al colicionar
 *
 * */
public class WorldContactListener implements ContactListener {
    /**
     * @author Tomas Alberto
     * @version 1.0
     */
    public WorldContactListener() {
    }

    @Override
    public void beginContact(Contact contact) {



        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if ("head".equals(fixA.getUserData()) || "head".equals(fixB.getUserData())) {
            Fixture head = "head".equals(fixA.getUserData()) ? fixA : fixB;
            Fixture object = (head == fixA) ? fixB : fixA;


        }



    }

    @Override
    public void endContact(Contact contact) {




    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
