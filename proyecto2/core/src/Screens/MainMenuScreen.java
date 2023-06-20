package Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.mygdx.game.Assets.Assets;
import com.mygdx.game.MainLearn;
import com.mygdx.game.Utilities.Learn;

public class MainMenuScreen extends BaseScreen {

    ScrollPane scroll;
    public MainMenuScreen(MainLearn game) {
        super(game);
        Table menu = new Table();
        menu.setFillParent(true);
        menu.defaults().uniform().fillY();

        for(final Learn learn : Learn.values()){
            TextButton button = new TextButton(learn.name, Assets.textButtonStyle);
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //MainMenuScreen.this.game.setScreen(PlayScreen);
                }
            });
            menu.row().padTop(20).height(50);
            menu.add(button).fillX();
        }
        scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
        scroll.setSize(500, (float)V_HEIGHT);
        scroll.setPosition(150, 0);
        stage.addActor(scroll);
    }

    @Override
    public void draw(float delta) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
