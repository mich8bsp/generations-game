package com.github.generations;

import com.badlogic.gdx.graphics.Color;

public class GameUnit {

    private boolean isAlive = true;
    private Color color;

    public GameUnit(Color color){
        this.color = color;
    }

    public GameUnit(int colorR, int colorG, int colorB){
        setColor(colorR, colorG, colorB);
    }

    public void kill(){
        this.isAlive = false;
    }

    public void revive(){
        this.isAlive = true;
    }

    public boolean isAlive(){
        return isAlive;
    }

    private void setColor(int colorR, int colorG, int colorB) {
        this.color = new Color(((float)colorR)/255, ((float)colorG)/255, ((float)colorB)/255, 1f);
    }

    public Color getColor() {
        return color;
    }

    public GameUnit copy() {
        return new GameUnit(this.color);
    }
}
