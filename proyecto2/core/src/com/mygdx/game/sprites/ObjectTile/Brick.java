package com.mygdx.game.sprites.ObjectTile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.Scenes.Hud;

public class Brick extends  InteractiveTileObject{
    public Brick(PlayScreen screen, MapObject object) {
        super(screen, object );
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick","Collision");
        setCategoryFilter(MarioBros.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);

    }
}
