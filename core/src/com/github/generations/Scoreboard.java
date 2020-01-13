package com.github.generations;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Scoreboard extends Actor {
    private Skin skin;
    private IScoreSupplier scoreSupplier;

    private String scoreText = "Score: 0";

    public Scoreboard(Skin uiSkin) {
        this.skin = uiSkin;
    }


    public void setScoreSupplier(IScoreSupplier scoreSupplier) {
        this.scoreSupplier = scoreSupplier;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Label label = new Label(scoreText, skin);
        label.setPosition(300, 1000);
        label.setWidth(500);
        label.setHeight(100);
        label.setFontScale(3);
        label.setWrap(true);
        label.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.scoreText = "Score: " + scoreSupplier.getScore();
    }
}
