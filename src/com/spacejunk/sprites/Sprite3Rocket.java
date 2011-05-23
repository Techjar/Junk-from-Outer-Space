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
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.SoundStore;

/**
 * 
 * @author Techjar
 */
public class Sprite3Rocket implements PowerupWeaponSprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, ySpeed, soundID, damage;
    private float x, y;
    private long ySpeedTime;
    private boolean visible;
    private Texture tex;
    private SpaceJunk sj;
    private TickCounter tc;
    private Particle1Jet jet;
    private SoundManager sm;
    private Shape bounds;
    public static final long SHOT_DELAY = 1000, POWERUP_LIFE = 10;
    public static final String KEY_NAME = Powerup.ROCKET;


    public Sprite3Rocket(SpaceJunk sj, List sprites, List particles, SoundManager sm, float x, float y, Texture tex) {
        try {
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.sprites = sprites; this.particles = particles;
            this.id = 3; this.x = x; this.y = y; this.damage = 5; this.ySpeed = 1; this.visible = true;
            this.ySpeedTime = tc.getTickMillis();
            this.tex = tex;
            this.sm = sm;
            this.bounds = new Rectangle(this.x, this.y, 48, 16);
            this.jet = new Particle1Jet(sj, this.x, this.y + 8, 1, false);
            particles.add(jet);
            this.soundID = sm.playSoundEffect("weapon.rocket", true);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update() {
        this.x += this.ySpeed;
        if(tc.getTickMillis() - this.ySpeedTime >= 50 && this.ySpeed < 20) {
            this.ySpeed++;
            this.ySpeedTime = tc.getTickMillis();
        }
        if((this.x - 100) > Display.getDisplayMode().getWidth()) {
            this.setVisible(false);
            sm.stopSoundEffect(this.soundID);
        }
        bounds.setX(this.x + 1); bounds.setY(this.y);
        jet.setX(this.x); jet.setY(this.y + 8);
        jet.setVisible(this.visible);
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

    public void impact() {
        sprites.add(new Sprite5Explosion(this.sj, this.sprites, this.particles, this.sm, this.x + 24, this.y + 8, 2));
        this.setVisible(false);
        jet.setVisible(false);
        sm.stopSoundEffect(this.soundID);
    }

    public int getDamage() {
        return this.damage;
    }
}
