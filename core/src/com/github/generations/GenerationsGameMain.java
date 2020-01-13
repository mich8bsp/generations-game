package com.github.generations;

import com.badlogic.gdx.Gdx;

public class GenerationsGameMain extends GameBeta {
	GameLevel[] levels = new GameLevel[1];


	@Override
	public void initialize() {
		levels[0] = new GameLevel("level2.txt", mainStage);
		Gdx.input.setInputProcessor(mainStage);
	}



	@Override
	public void update(float dt) {

	}



}
