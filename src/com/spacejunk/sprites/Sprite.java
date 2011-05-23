/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import org.newdawn.slick.geom.*;

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
    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
    Shape getBounds();
    Vector2f getLocation();
}
