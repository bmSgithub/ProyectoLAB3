package com.mygdx.game.sprites.ObjectTile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Scenes.Hud;

public class Coins extends InteractiveTileObject{
    private static TiledMapTileSet set;
    private final int BLANK_COIN = 28;
    public Coins(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        set = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin","Collision");
        getCell().setTile(set.getTile(BLANK_COIN));
        Hud.addScore(100);

    }
}
