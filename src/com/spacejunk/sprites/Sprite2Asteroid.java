/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import java.util.Random;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.geom.*;
import com.spacejunk.SoundManager;
import com.spacejunk.util.*;
import com.spacejunk.SpaceJunk;
import com.spacejunk.particles.*;

/**
 * 
 * @author Techjar
 */
public class Sprite2Asteroid implements Sprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y, z, hits, yDir, yTimes, yNextTime, rotSpeed, texnum, ySpeed;
    private long flashTime;
    private boolean visible, rotDir, flash, useFullPoly;
    private Random random;
    private Texture tex;
    private SoundManager sm;
    private SpaceJunk sj;
    private Shape hitbox, bounds;
    private TickCounter tc;


    public Sprite2Asteroid(List sprites, List particles, SoundManager sm, int x, int y, SpaceJunk sj, Texture tex, int texnum) {
        try {
            random = new Random();
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.rotDir = random.nextBoolean();
            this.rotSpeed = random.nextInt(5) + 1;
            this.sprites = sprites; this.particles = particles;
            this.id = 2; this.x = x + 64; this.y = y; this.z = 0; this.hits = 2; this.yTimes = 0; this.yNextTime = 0; this.yDir = random.nextBoolean() ? 1 : -1;
            this.visible = true; this.flash = false;
            this.useFullPoly = false; // Should we use full polygonal hitboxes? (WARNING: VERY LAGGY!!!)
            this.tex = tex;
            this.texnum = texnum;
            this.ySpeed = random.nextInt(4) + 1;
            this.sm = sm;
            this.hitbox = new Polygon(this.getHitbox(this.texnum));
            hitbox.setCenterX(this.x + (this.useFullPoly ? this.getHitboxOffset(this.texnum).getX() : 0));
            hitbox.setCenterY(this.y + (this.useFullPoly ? this.getHitboxOffset(this.texnum).getY() : 0));
            if(!this.useFullPoly) this.hitbox = new Rectangle(hitbox.getMinX(), hitbox.getMinY(), hitbox.getWidth(), hitbox.getHeight());
            this.bounds = this.hitbox;
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update() {
        this.y = this.y + yDir; yTimes++;
        this.x -= this.ySpeed;
        this.z = MathHelper.loop(rotDir ? this.z + this.rotSpeed : this.z - this.rotSpeed, 0, 360);
        /*if(yTimesSmooth < 30 && yDir != 0 && nextY <= 1) {
            nextY += 0.03F;
            yTimesSmooth++;
        }*/
        if(yTimes >= yNextTime) {
            yDir = random.nextInt(5) - 2;
            yNextTime = (yDir == 0 ? random.nextInt(60) : MathHelper.clamp(random.nextInt(300), 60, 300));
            yTimes = 0;
        }
        if(this.x + 64 <= 0 || this.hits <= 0) {
            this.setVisible(false);
            if(this.hits <= 0) {
                try {
                    particles.add(new Particle0Explosion(sj, this.x, this.y, 1500, 1));
                    sm.playSoundEffect("ambient.explode.1", false);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                sj.incScore(1);
            }
        }
        if((tc.getTickMillis() - flashTime) >= 100 && this.flash) this.flash = false;
        hitbox.setCenterX(this.x + (this.useFullPoly ? this.getHitboxOffset(this.texnum).getX() : 0));
        hitbox.setCenterY(this.y + (this.useFullPoly ? this.getHitboxOffset(this.texnum).getY() : 0));
        this.bounds = hitbox.transform(Transform.createRotateTransform((float)Math.toRadians(this.z), this.x, this.y));
    }

    public void render() {
        if(this.visible) {
            // store the current model matrix
            glPushMatrix();

            // bind to the appropriate texture for this sprite
            tex.bind();

            // translate to the right location and prepare to draw
            glTranslatef(x, y, 0);
            glRotatef(z, 0, 0, 1);
            glTranslatef(-(tex.getImageWidth() >> 1), -(tex.getImageHeight() >> 1), 0);
            glColor3f(1, 1, 1);

            if(this.flash) {
                // Red hit tint
                glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
                glColor3f(1, 0.2F, 0.2F);
            }

            // draw a quad textured to match the sprite
            glBegin(GL_QUADS);
                glTexCoord2f(0, 0); glVertex2f(0, 0);
                glTexCoord2f(0, tex.getHeight()); glVertex2f(0, tex.getImageHeight());
                glTexCoord2f(tex.getWidth(), tex.getHeight()); glVertex2f(tex.getImageWidth(), tex.getImageHeight());
                glTexCoord2f(tex.getWidth(), 0); glVertex2f(tex.getImageWidth(), 0);
            glEnd();

            // Red tint
            //glColorMaterial(GL_FRONT, GL_COLOR_MATERIAL_FACE);

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

    public void hit(int damage) {
        this.flash = true;
        this.hits -= damage;
        flashTime = tc.getTickMillis();
    }

    public Shape getBounds() {
        return this.bounds;
    }

    private float[] getHitbox(int i) {
        switch(i) {
            case 0: return PolygonHitbox.ASTEROID_0;
            case 1: return PolygonHitbox.ASTEROID_1;
            case 2: return PolygonHitbox.ASTEROID_2;
            case 3: return PolygonHitbox.ASTEROID_3;
            case 4: return PolygonHitbox.ASTEROID_4;
            default: return PolygonHitbox.ASTEROID_0;
        }
    }

    private Vector2f getHitboxOffset(int i) {
        switch(i) {
            case 0: return new Vector2f(-1, 3);
            case 1: return new Vector2f(1, -1);
            case 2: return new Vector2f(-1, 1);
            case 3: return new Vector2f(-2, -5);
            case 4: return new Vector2f(-4, -4);
            default: return new Vector2f(0, 0);
        }
    }
}
