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
public class Sprite6TitleAsteroid implements TitleSprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, rotSpeed, texnum;
    private float x, y, z;
    private boolean visible, rotDir, useFullPoly;
    private Random random;
    private Texture tex;
    private SoundManager sm;
    private SpaceJunk sj;
    private Shape bounds, hitbox;
    private TickCounter tc;


    public Sprite6TitleAsteroid(List sprites, List particles, SoundManager sm, float x, float y, SpaceJunk sj, Texture tex, int texnum) {
        try {
            random = new Random();
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.rotDir = random.nextBoolean();
            this.rotSpeed = random.nextInt(5) + 1;
            this.sprites = sprites; this.particles = particles;
            this.id = 6; this.x = x; this.y = y; this.z = 0;
            this.visible = true; this.texnum = texnum;
            this.tex = tex;
            this.sm = sm;
            this.useFullPoly = true; // Should we use full polygonal hitboxes? (WARNING: VERY LAGGY!!!)
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
        this.z = MathHelper.loop(rotDir ? this.z + this.rotSpeed : this.z - this.rotSpeed, 0, 360);
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

    public void setBounds(Shape b) {
        this.bounds = b;
    }

    public Vector2f getLocation() {
        return new Vector2f(this.x, this.y);
    }

    public Texture getTexture() {
        return this.tex;
    }

    private float[] getHitbox(int i) {
        return i >= PolygonHitbox.ASTEROIDS.length ? new Rectangle(this.x, this.y, tex.getImageWidth(), tex.getImageHeight()).getPoints() : PolygonHitbox.ASTEROIDS[i];
    }

    private Vector2f getHitboxOffset(int i) {
        return i >= PolygonHitbox.ASTEROID_OFFSETS.length ? new Vector2f(0, 0) : PolygonHitbox.ASTEROID_OFFSETS[i];
    }
}
