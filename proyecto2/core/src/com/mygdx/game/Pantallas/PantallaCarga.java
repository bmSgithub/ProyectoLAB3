package com.mygdx.game.Pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MarioBros;
import com.mygdx.game.PlayScreen;

public abstract class PantallaCarga extends ScreenAdapter {

    protected MarioBros game;
    protected final String direccionLogo;

    protected final float screenWidht = Gdx.graphics.getWidth();
    protected final float screenHeight = Gdx.graphics.getHeight();
    protected Texture textureLogo;
    protected OrthographicCamera camera;
    protected Sprite sprite;
    protected Viewport viewport;

    protected float posicionX;
    protected float posicionY;
    protected final float alphaPositivo = 0.003f;
    protected final float alphaNegativo = 0.008f;

    protected float alphaLogo;
    protected boolean alphaLogoCompletado;
    protected float alphaLogoActualmente;


    public PantallaCarga(MarioBros game, String direccionLogo) {
        this.game = game;
        this.direccionLogo = direccionLogo;
    }

    @Override
    public void show() {

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false);
        this.textureLogo = new Texture(direccionLogo);
        this.textureLogo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        this.sprite = new Sprite(this.textureLogo);
        this.posicionX = (viewport.getWorldWidth() - textureLogo.getWidth()) / 2;
        this.posicionY = (viewport.getWorldHeight() - textureLogo.getHeight()) / 2;

        this.sprite.setAlpha(0f);
        this.alphaLogoActualmente = 0f;
        this.alphaLogoCompletado = false;


        game.batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    public float transicionAlpha(){

        //Aumento el alpha
        if (!alphaLogoCompletado){

            this.alphaLogoActualmente += alphaPositivo;

            if (alphaLogoActualmente > 1f){
                alphaLogoActualmente = 1;
                alphaLogoCompletado = true;
            }
        }else {

            this.alphaLogoActualmente -= alphaNegativo;

            if (alphaLogoActualmente < 0f){
                alphaLogoActualmente = 0f;
            }
        }

        return alphaLogoActualmente;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void dispose() {
        textureLogo.dispose();
    }
}