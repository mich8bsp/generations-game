package com.github.generations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GenerationsGameMain extends GameBeta {
	SpriteBatch batch;
	GameLevel[] levels = new GameLevel[1];


	@Override
	public void initialize() {
		levels[0] = new GameLevel("level1.txt");
		mainStage.addActor(levels[0].getWorld());
		batch = new SpriteBatch();
	}



	@Override
	public void update(float dt) {

	}



}
