package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DeliveryBros;

public class Hud {

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private static Integer score;

    Label countDownLabel ;
    static Label  scoreLabel;
    Label timeLabel;
    Label leveLabel;


    @SuppressWarnings("DefaultLocale")
    public Hud (SpriteBatch sb) {

        worldTimer = 0;
        timeCount = 0;
        score = 0000;

        viewport = new FitViewport(DeliveryBros.V_WIDHT, DeliveryBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d",worldTimer),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        scoreLabel = new Label(String.format("%06d",score),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        leveLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(leveLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);


    }
    @SuppressWarnings("DefaultLocale")
    public  void  update(float dt){
        timeCount +=dt;
        if(timeCount >=1 ){
            worldTimer++;
            countDownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }
    @SuppressWarnings("DefaultLocale")
    public static void addScore (int value){
        score += value;
        scoreLabel.setText(String.format("%06d",score));
    }

    public Integer getWorldTimer() {
        return worldTimer;
    }

    public void setWorldTimer(Integer worldTimer) {
        this.worldTimer = worldTimer;
    }
}
