/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.Calendar;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import com.spacejunk.SoundManager;
import com.spacejunk.particles.*;
import com.spacejunk.SpaceJunk;

/**
 * 
 * @author Techjar
 */
public class Sprite0Ship implements Sprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y;
    private long lastFire, hitTime, invTime, invForTime, invFrameTime;
    private boolean visible, hit, invincible, invState;
    private Texture tex;
    private SoundManager sm;
    private SpaceJunk sj;
    private Particle1Jet jet;


    public Sprite0Ship(List sprites, List particles, int x, int y, SoundManager sm, SpaceJunk sj) {
        try {
            this.sprites = sprites; this.particles = particles;
            this.id = 0; this.x = x; this.y = y; this.lastFire = 0; this.hitTime = 0; this.invFrameTime = 0; this.invTime = 0; this.invForTime = 0;
            this.visible = true; this.hit = false; this.invincible = false; this.invState = true;
            this.tex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/ship.png"), GL_NEAREST);
            this.sm = sm;
            this.jet = new Particle1Jet(sj, this.x - 26, this.y + 16);
            particles.add(jet);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public void update() {
        Calendar cal = Calendar.getInstance();
        invFrameTime++;
        if(this.invincible) {
            if(invFrameTime >= 2) {
                invState = !invState;
                invFrameTime = 0;
            }

            if((cal.getTimeInMillis() - invTime) >= invForTime) {
                this.setInvincible(false);
            }
        }
        else if(!invState) {
            invState = true;
        }

        jet.setVisible(this.visible);
        if(!this.hit) {
            this.y = (Display.getDisplayMode().getHeight() - Mouse.getY()) - (tex.getImageHeight() / 2);
            jet.setY(this.y + 16);
            if(Mouse.isButtonDown(0) && (cal.getTimeInMillis() - lastFire) >= 200) {
                lastFire = cal.getTimeInMillis();
                sprites.add(new Sprite1Gunfire(sprites, particles, this.x, this.y - 3));
                sprites.add(new Sprite1Gunfire(sprites, particles, this.x, this.y + 19));
                sm.playSoundEffect("ship.gunfire");
            }
        }
        else {
            if((cal.getTimeInMillis() - hitTime) >= 2000) {
                this.setVisible(true);
                this.setInvincible(true); invTime = cal.getTimeInMillis(); invForTime = 1000;
                this.hit = false;
                Sprite sprite;
                for(int i = 0; i < sprites.size(); i++) {
                    sprite = sprites.get(i);
                    if(sprite instanceof Sprite2Asteroid) sprite.setVisible(false);
                }
            }
        }
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
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        this.setVisible(false);
        this.hit = true;
        hitTime = Calendar.getInstance().getTimeInMillis();
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
}
