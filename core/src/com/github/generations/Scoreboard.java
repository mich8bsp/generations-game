package com.github.generations;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Scoreboard extends Actor {
    private IScoreSupplier scoreSupplier;

    private String scoreText = "Score: 0";

    public void setScoreSupplier(IScoreSupplier scoreSupplier) {
        this.scoreSupplier = scoreSupplier;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Label label = new Label(scoreText, new Label.LabelStyle(GameSkin.getFont(80), Color.TEAL));
        label.setPosition(300, 800);
        label.setWidth(500);
        label.setHeight(100);
        label.setWrap(false);
        label.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.scoreText = "Score: " + scoreSupplier.getScore();
    }
}
