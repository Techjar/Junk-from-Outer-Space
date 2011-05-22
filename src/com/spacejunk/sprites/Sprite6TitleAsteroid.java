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
public class Sprite6TitleAsteroid implements Sprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y, z, rotSpeed;
    private boolean visible, rotDir;
    private Random random;
    private Texture tex;
    private SoundManager sm;
    private SpaceJunk sj;
    private Shape bounds;
    private TickCounter tc;


    public Sprite6TitleAsteroid(List sprites, List particles, SoundManager sm, int x, int y, SpaceJunk sj, Texture tex, int texnum) {
        try {
            random = new Random();
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.rotDir = random.nextBoolean();
            this.rotSpeed = random.nextInt(5) + 1;
            this.sprites = sprites; this.particles = particles;
            this.id = 6; this.x = x; this.y = y; this.z = 0;
            this.visible = true;
            this.tex = tex;
            this.sm = sm;
            this.bounds = new Rectangle(this.x, this.y, 1, 1);
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

    public Shape getBounds() {
        return this.bounds;
    }
}
