/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

/**
 * 
 * @author Techjar
 */
public interface Sprite {
    void update();
    void render();
    int getID();
    void setVisible(boolean visible);
    boolean isVisible();
    int getX();
    int getY();
}
