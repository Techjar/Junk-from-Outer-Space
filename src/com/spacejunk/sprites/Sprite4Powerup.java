/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import java.util.List;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import com.spacejunk.particles.*;
import com.spacejunk.SoundManager;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.*;

/**
 * 
 * @author Techjar
 */
public class Sprite4Powerup implements Sprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y;
    private long lastfire;
    private boolean visible;
    private Texture tex;
    private SpaceJunk sj;
    private TickCounter tc;
    private SoundManager sm;
    private Bounds bounds;


    public Sprite4Powerup(SpaceJunk sj, List sprites, List particles, SoundManager sm, int x, int y, Texture tex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void render() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setVisible(boolean visible) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isVisible() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setX(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setY(int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Bounds getBounds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
