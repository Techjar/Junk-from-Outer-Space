/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.geom.*;
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
    private int id;
    private float x, y;
    private long lastfire;
    private boolean visible, big;
    private Texture tex;
    private SoundManager sm;
    private Shape bounds;
    private SpaceJunk sj;
    private TickCounter tc;
    public static final long POWERUP_LIFE = 30000;
    public static final String KEY_NAME = Powerup.BIGSHOT;


    public Sprite1Gunfire(SpaceJunk sj, List sprites, List particles, SoundManager sm, float x, float y, Texture tex, boolean big) {
        this.sj = sj;
        this.tc = sj.getTickCounter();
        this.sprites = sprites; this.particles = particles; this.big = big;
        this.id = 1; this.x = x - (this.big ? 8 : 0); this.y = y - (this.big ? 8 : 0); this.lastfire = 0; this.visible = true;
        this.tex = tex;
        this.sm = sm;
        this.bounds = this.big ? new Circle(this.x, this.y, 16) : new Rectangle(this.x, this.y, 16, 16);
    }

    public void update() {
        this.x += 10;
        if((this.x - 16) > Display.getDisplayMode().getWidth()) this.setVisible(false);
        bounds.setCenterX(this.x + (this.big ? 16 : 8)); bounds.setCenterY(this.y + (this.big ? 16 : 8));
    }

    public void render() {
        if(this.visible) {
            // store the current model matrix
            glPushMatrix();

            // bind to the appropriate texture for this sprite
            tex.bind();

            // translate to the right location and prepare to draw
            glTranslatef(x, y, 0);
            glColor3f(1, 1, 1);

            // draw a quad textured to match the sprite
            glBegin(GL_QUADS);
                glTexCoord2f(0, 0); glVertex2f(0, 0);
                glTexCoord2f(0, tex.getHeight()); glVertex2f(0, tex.getImageHeight() * (this.big ? 2 : 1));
                glTexCoord2f(tex.getWidth(), tex.getHeight()); glVertex2f(tex.getImageWidth() * (this.big ? 2 : 1), tex.getImageHeight() * (this.big ? 2 : 1));
                glTexCoord2f(tex.getWidth(), 0); glVertex2f(tex.getImageWidth() * (this.big ? 2 : 1), 0);
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

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Shape getBounds() {
        return this.bounds;
    }

    public Vector2f getLocation() {
        return new Vector2f(this.x, this.y);
    }

    public boolean isBig() {
        return this.big;
    }
}
