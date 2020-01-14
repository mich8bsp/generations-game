package com.github.generations;

import com.badlogic.gdx.Gdx;


public class GenerationsGameMain extends GameBeta implements ILevelSelector {
	GameLevel[] levels = new GameLevel[2];

	LevelSelector levelSelector;

	@Override
	public void initialize() {
		Gdx.input.setInputProcessor(mainStage);
		levels[0] = new GameLevel("level1.txt");
		levels[1] = new GameLevel("level2.txt");
		this.levelSelector = new LevelSelector(this, levels.length);
		for(GameLevel level : levels){
			level.setBackCallback(this);
		}
		this.levelSelector.addToStage(mainStage);
	}



	@Override
	public void update(float dt) {

	}


	@Override
	public void onLevelSelected(int levelNum) {
		clear();
		levels[levelNum-1].addToStage(mainStage);
	}

	@Override
	public void onLevelQuit() {
		clear();
		this.levelSelector.addToStage(mainStage);
	}
}
