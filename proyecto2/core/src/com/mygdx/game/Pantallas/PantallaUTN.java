package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Enum.Direcciones;
import com.mygdx.game.DeliveryBros;

public class PantallaUTN extends PantallaInicial {
    public PantallaUTN(DeliveryBros game) {
        super(game);
        this.direccionLogo = Direcciones.LOGO_UTN.getFilePath();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        sprite.setPosition(posicionX, posicionY);
        sprite.draw(game.batch);

        game.batch.end();

        sprite.setAlpha(transicionAlpha());

        if (transicionAlpha() == 0f || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new PantallaPedidosYA(game));
            dispose();
        }
    }


}
