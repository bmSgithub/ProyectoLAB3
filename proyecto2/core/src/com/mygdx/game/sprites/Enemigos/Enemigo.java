package com.mygdx.game.sprites.Enemigos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PlayScreen;
/**
 *@author Tomas Alberto
 * @version 1.0
 */
public abstract class Enemigo extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Enemigo(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemigo();
    }
    protected abstract void defineEnemigo();
}
