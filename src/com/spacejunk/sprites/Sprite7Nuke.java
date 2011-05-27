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
import java.io.FileInputStream;
import java.util.Random;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * 
 * @author Techjar
 */
public class Sprite7Nuke implements Sprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id;
    private long placeTime;
    private float x, y, randRed;
    private boolean visible;
    private Texture tex;
    private SpaceJunk sj;
    private TickCounter tc;
    private SoundManager sm;
    private Shape bounds;
    public static final long POWERUP_LIFE = 1;
    public static final String KEY_NAME = Powerup.NUKE;


    public Sprite7Nuke(SpaceJunk sj, List sprites, List particles, SoundManager sm, float x, float y) {
        try {
            this.sj = sj; this.tc = sj.getTickCounter();
            this.sprites = sprites; this.particles = particles;
            this.placeTime = tc.getTickMillis();
            this.id = 7; this.x = x; this.y = y; this.visible = true;
            this.tex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/nuke.png"), GL_LINEAR);
            this.sm = sm; this.bounds = new Circle(this.x + 16, this.y + 16, 16);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update() {
        if(tc.getTickMillis() - this.placeTime >= 5000) {
            try {
                sm.playSoundEffect("ambient.explode.3");
                particles.add(new Particle0Explosion(this.sj, this.x + 16, this.y + 16, 5000, 3));
                Sprite sp = null;
                for(int i = 0; i < sprites.size(); i++) {
                    sp = sprites.get(i);
                    if(sp instanceof HostileSprite) ((HostileSprite)sp).hit(1000);
                }
                this.setVisible(false);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
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
}
