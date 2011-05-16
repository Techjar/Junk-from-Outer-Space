/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

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
public class Sprite1Gunfire implements Sprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y;
    private long lastfire;
    private boolean visible;
    private Texture tex;
    private SoundManager sm;
    private Bounds bounds;
    private SpaceJunk sj;
    private TickCounter tc;


    public Sprite1Gunfire(SpaceJunk sj, List sprites, List particles, SoundManager sm, int x, int y, Texture tex) {
        this.sj = sj;
        this.tc = sj.getTickCounter();
        this.sprites = sprites; this.particles = particles;
        this.id = 1; this.x = x; this.y = y; this.lastfire = 0; this.visible = true;
        this.tex = tex;
        this.sm = sm;
        this.bounds = new Bounds(this.x - 16, this.y, 16, 16);
    }

    public void update() {
        this.x += 10;
        if((this.x - 16) > Display.getDisplayMode().getWidth()) this.setVisible(false);
        bounds.setX(this.x - 16); bounds.setY(this.y);
    }

    public void render() {
        if(this.visible) {
            // store the current model matrix
            glPushMatrix();

            // bind to the appropriate texture for this sprite
            tex.bind();

            // translate to the right location and prepare to draw
            glTranslatef(x, y, 0);
            glRotatef(90, 0, 0, 1);
            glColor3f(1, 1, 1);

            // draw a quad textured to match the sprite
            glBegin(GL_QUADS);
                glTexCoord2f(0, 0); glVertex2f(0, 0);
                glTexCoord2f(0, tex.getHeight()); glVertex2f(0, tex.getImageHeight());
                glTexCoord2f(tex.getWidth(), tex.getHeight()); glVertex2f(tex.getImageWidth(), tex.getImageHeight());
                glTexCoord2f(tex.getWidth(), 0); glVertex2f(tex.getImageWidth(), 0);
            glEnd();

            // restore the model view matrix to prevent contamination
            glPopMatrix();
        }
    }

    public int getID() {
        return this.id;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bounds getBounds() {
        return this.bounds;
    }
}
