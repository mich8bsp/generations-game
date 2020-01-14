package com.github.generations;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Scoreboard extends Actor {
    private IScoreSupplier scoreSupplier;

    private String scoreText = "Score: 0";
    private Label scoreLabel;

    public Scoreboard(){
        scoreLabel = new Label(scoreText, new Label.LabelStyle(GameSkin.getFont(80), Color.TEAL));
        scoreLabel.setPosition(300, 800);
        scoreLabel.setWidth(500);
        scoreLabel.setHeight(100);
        scoreLabel.setWrap(false);
    }

    public void setScoreSupplier(IScoreSupplier scoreSupplier) {
        this.scoreSupplier = scoreSupplier;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        scoreLabel.setText(scoreText);
        scoreLabel.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.scoreText = "Score: " + scoreSupplier.getScore();
    }
}
