package com.mygdx.game.sprites.ObjectTile;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;

public abstract class InteractiveTileObject {
        protected World world;
        protected TiledMap map;
        protected Rectangle bounds;
        protected Body body;

        protected Fixture fixture;

        public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
            this.world = world;
            this.map = map;
            this.bounds = bounds;
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((bounds.getX() + bounds.getWidth()/2 / MarioBros.PPM), (bounds.getY() + bounds.getWidth()/2 / MarioBros.PPM));
            body = world.createBody(bdef);
            shape.setAsBox(bounds.getWidth()/2/ MarioBros.PPM , bounds.getHeight()/2/MarioBros.PPM);
            fdef.shape = shape;
            fixture = body.createFixture(fdef);

        }
        public  abstract void onHeadHit();

    }
