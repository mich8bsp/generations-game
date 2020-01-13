package com.github.generations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.io.BufferedReader;

public class GameLevel {

    private GameWorld world;

    private Skin uiSkin;
    private Button runButton;
    private Scoreboard scoreboard;


    public GameLevel(String layoutPath, Stage mainStage){
        this.world = createWorldFromLayout(layoutPath);
        uiSkin = new Skin(Gdx.files.internal("clean-crispy-ui.json"));
        runButton = createRunButton();
        this.scoreboard = new Scoreboard(uiSkin);
        this.scoreboard.setScoreSupplier(world);
        mainStage.addActor(runButton);
        mainStage.addActor(world);
        mainStage.addActor(scoreboard);
    }

    private Button createRunButton(){
        final TextButton button2 = new TextButton("Start", uiSkin, "toggle");
        button2.getLabel().setFontScale(2);
        button2.setSize(150, 100);
        button2.setPosition(100, 1000);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.toggleRunning();
                if(button2.getLabel().getText().toString().equals("Pause")){
                    button2.getLabel().setText("Resume");
                }else{
                    button2.getLabel().setText("Pause");
                }
                return true;
            }
        });
        return button2;
    }

    private GameWorld createWorldFromLayout(String layoutPath) {
        FileHandle levelLayout = Gdx.files.internal(layoutPath);
        BufferedReader reader = new BufferedReader(levelLayout.reader());
        String line;
        GameWorld world = null;
        try{
            while((line = reader.readLine())!=null){
                String[] lineSpl = line.split(",");
                if(world == null){
                    world = new GameWorld(Integer.parseInt(lineSpl[0]), Integer.parseInt(lineSpl[1]));
                }else{
                    int row = Integer.parseInt(lineSpl[0]);
                    int col = Integer.parseInt(lineSpl[1]);
                    if(lineSpl[2].equals("X")){
                        world.populateCell(row, col, ECellType.BLOCKER, null);
                    }else{
                        int colR = Integer.parseInt(lineSpl[2]);
                        int colG = Integer.parseInt(lineSpl[3]);
                        int colB = Integer.parseInt(lineSpl[4]);
                        world.populateCell(row, col, ECellType.GAME_UNIT, new GameUnit(colR, colG, colB));
                    }
                }
            }
        }catch (Exception e){
            System.out.println("shit");
        }

        return world;
    }

    public GameWorld getWorld(){
        return world;
    }

    public int getScore(){
        return world.getScore();
    }
}
