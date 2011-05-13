/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.particles;

/**
 * 
 * @author Techjar
 */
public interface Particle {
    void update();
    void render();
    void setVisible(boolean visible);
    boolean isVisible();
    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
}
