package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;

public class B2WorldCreator {
    public B2WorldCreator (PlayScreen screen){

        World world =screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;



//        Codigo para el suelo
        for (RectangleMapObject object : map.getLayers().get("Ground").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = object.getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM,rect.getHeight() / 2 / MarioBros.PPM);

            fdef.shape = shape;

            body.createFixture(fdef);
        }

        //       Codigo para building
        for (RectangleMapObject object : map.getLayers().get("Buildings").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = object.getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM,rect.getHeight() / 2 / MarioBros.PPM);

            fdef.shape = shape;

            body.createFixture(fdef);
        }

//        Codigo para los autos
        for (PolygonMapObject object : map.getLayers().get("Cars").getObjects().getByType(PolygonMapObject.class)){
            Rectangle rect = object.getPolygon().getBoundingRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 /MarioBros.PPM,rect.getHeight() / 2/MarioBros.PPM);

            fdef.shape = shape;
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;


            body.createFixture(fdef);
        }


//        Codigo para los limites
        for (RectangleMapObject object : map.getLayers().get("Limits").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = object.getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM ,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM,rect.getHeight() / 2 / MarioBros.PPM);

            fdef.shape = shape;

            body.createFixture(fdef);
        }



    }
}
