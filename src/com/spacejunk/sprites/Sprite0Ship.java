/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import com.spacejunk.SoundManager;
import com.spacejunk.particles.*;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.*;

/**
 * 
 * @author Techjar
 */
public class Sprite0Ship implements Sprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y;
    private long lastFire, hitTime, invTime, invForTime, invFrameTime;
    private boolean visible, hit, invincible, invState, respawning;
    private List<String> powerups;
    private Texture tex, guntex;
    private SoundManager sm;
    private SpaceJunk sj;
    private Particle1Jet jet;
    private Bounds bounds;
    private TickCounter tc;


    public Sprite0Ship(List sprites, List particles, int x, int y, SoundManager sm, SpaceJunk sj) {
        try {
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.sprites = sprites; this.particles = particles;
            this.id = 0; this.x = x; this.y = y;
            this.lastFire = 0; this.hitTime = 0; this.invFrameTime = 0; this.invTime = 0; this.invForTime = 0;
            this.visible = true; this.hit = false; this.invincible = false; this.invState = true; this.respawning = false;
            this.powerups = new ArrayList<String>();
            this.tex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/ship.png"), GL_NEAREST);
            this.guntex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/gunfire.png"), GL_LINEAR);
            this.sm = sm;
            this.jet = new Particle1Jet(sj, this.x - 26, this.y + 16, 0);
            this.bounds = new Bounds(this.x - 28, this.y + 2, tex.getImageWidth(), tex.getImageHeight());
            particles.add(jet);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public void update() {
        invFrameTime++;
        if(this.invincible) {
            if(invFrameTime >= 2) {
                invState = !invState;
                invFrameTime = 0;
            }

            if((tc.getTickMillis() - invTime) >= invForTime) {
                this.setInvincible(false);
                this.respawning = false;
            }
        }
        else if(!invState) {
            invState = true;
        }

        jet.setVisible(this.visible);
        if(!this.hit) {
            this.x = Mouse.getX() + (tex.getImageWidth() / 2);
            this.y = (Display.getDisplayMode().getHeight() - Mouse.getY()) - (tex.getImageHeight() / 2);
            jet.setX(this.x - 26); jet.setY(this.y + 16);
            if(!this.respawning) {
                if(Mouse.isButtonDown(0)) {
                    if((powerups.contains(Powerup.FASTSHOT) && tc.getTickMillis() - lastFire >= 40) || tc.getTickMillis() - lastFire >= 200) {
                        lastFire = tc.getTickMillis();
                        sprites.add(new Sprite1Gunfire(this.sj, sprites, particles, sm, this.x, this.y - 3, this.guntex));
                        sprites.add(new Sprite1Gunfire(this.sj, sprites, particles, sm, this.x, this.y + 19, this.guntex));
                        sm.playSoundEffect("ship.gunfire", false);
                    }
                }
                if(Mouse.isButtonDown(1)) {
                    
                }
            }
        }
        else {
            if((tc.getTickMillis() - hitTime) >= 2000) {
                this.setVisible(true);
                this.setInvincible(true); invTime = tc.getTickMillis(); invForTime = 1000;
                this.hit = false;
                this.respawning = true;
                sj.clearAsteroids();
            }
        }
        bounds.setX(this.x - 28); bounds.setY(this.y + 2);
    }

    public void render() {
        if(this.visible & invState) {
            // store the current model matrix
            glPushMatrix();

            // bind to the appropriate texture for this sprite
            tex.bind();

            // translate to the right location and prepare to draw
            glTranslatef(x, y, 0);
            glRotatef(90, 0, 0, 1);
            if(this.invincible) glColor4f(1, 1, 1, 0.5F);

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

    public void hit() {
        try {
            particles.add(new Particle0Explosion(sj, this.x - 16, this.y + 16, 1500, 0));
            sm.playSoundEffect("ambient.explode.0", false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        this.setVisible(false);
        this.hit = true;
        hitTime = tc.getTickMillis();
    }

    public boolean isHit() {
        return this.hit;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public boolean isInvincible() {
        return this.invincible;
    }

    public Bounds getBounds() {
        return this.bounds;
    }
}
