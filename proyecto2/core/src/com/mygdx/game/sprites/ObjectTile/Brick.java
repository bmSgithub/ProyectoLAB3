package com.mygdx.game.sprites.ObjectTile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.DeliveryBros;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.Scenes.Hud;

public class Brick extends  InteractiveTileObject{
    public Brick(PlayScreen screen, MapObject object) {
        super(screen, object );
        fixture.setUserData(this);
        setCategoryFilter(DeliveryBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick","Collision");
        setCategoryFilter(DeliveryBros.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);

    }
}
