package com.github.generations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.BufferedReader;

public class GameLevel {
    private GameWorld world;

    private Skin uiSkin;
    private Button runButton;
    private Button backButton;
    private Scoreboard scoreboard;
    private ILevelSelector levelSelector;

    public GameLevel(String layoutPath){
        this.world = createWorldFromLayout(layoutPath);
        this.uiSkin = GameSkin.getSkin();
        this.runButton = createRunButton();
        this.backButton = createBackButton();
        this.scoreboard = new Scoreboard();
        this.scoreboard.setScoreSupplier(world);
    }

    public void addToStage(Stage stage){
        stage.addActor(runButton);
        stage.addActor(world);
        stage.addActor(scoreboard);
        stage.addActor(backButton);
    }

    public void setBackCallback(ILevelSelector levelSelector){
        this.levelSelector = levelSelector;
    }

    private Button createRunButton(){
        final TextButton button2 = new TextButton("Start", uiSkin, "default");
        button2.getLabel().setFontScale(2);
        button2.setSize(150, 100);
        button2.setPosition(100, 800);
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

    private Button createBackButton(){
        Texture backBtnTexture = new Texture(Gdx.files.internal("back.png"));
        TextureRegion region = new TextureRegion(backBtnTexture);
        TextureRegionDrawable drwbl = new TextureRegionDrawable(region);
        final ImageButton button2 = new ImageButton(drwbl, drwbl, drwbl);
        button2.setSize(150, 100);
        button2.setPosition(100, 950);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                levelSelector.onLevelQuit();
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
