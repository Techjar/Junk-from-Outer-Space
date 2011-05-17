/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.pause;

import org.newdawn.slick.Color;
import com.spacejunk.util.Bounds;

/**
 * 
 * @author Techjar
 */
public interface Pause {
    void render();
    void renderScreen();
    Bounds getBounds();
    Color getColor();
    void setColor(Color color);
    void setTextY(int y);
    boolean isActive();
    void setActive(boolean active);
    String getText();
    void setMouseClicked(boolean clicked);
}
