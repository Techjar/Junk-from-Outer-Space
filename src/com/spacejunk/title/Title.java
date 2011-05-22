/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.title;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.*;

/**
 * 
 * @author Techjar
 */
public interface Title {
    void render();
    void renderScreen();
    Shape getBounds();
    Color getColor();
    void setColor(Color color);
    void setTextY(int y);
    boolean isActive();
    void setActive(boolean active);
    String getText();
    void setMouseClicked(boolean clicked);
}
