/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import com.spacejunk.util.Bounds;

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
    void setX(int x);
    void setY(int y);
    Bounds getBounds();
}
