/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.Calendar;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import com.spacejunk.SoundManager;
import com.spacejunk.util.*;
import com.spacejunk.SpaceJunk;

/**
 * 
 * @author Techjar
 */
public class Sprite2Asteroid implements Sprite {
    private List<Sprite> sprites;
    private int id, x, y, z, hits;
    private long flashTime;
    private boolean visible, rotDir, flash;
    private Texture tex;
    private SoundManager sm;
    private SpaceJunk sj;


    public Sprite2Asteroid(List sprites, int x, int y, SpaceJunk sj) {
        try {
            Random random = new Random();
            this.rotDir = random.nextBoolean();
            this.sprites = sprites;
            this.id = 2; this.x = x + 64; this.y = y; this.z = 0; this.hits = 3; this.visible = true; this.flash = false;
            this.tex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/asteroid" + random.nextInt(3) + ".png"), GL_NEAREST);
            //this.sm = new SoundManager();
            this.sj = sj;
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update() {
        this.x -= 2;
        this.z = new MathHelper().loop((rotDir ? this.z + 2 : this.z - 2), 0, 360);
        if(this.x + 64 <= 0 || this.hits <= 0) {
            this.setVisible(false);
            if(this.hits <= 0) sj.incScore(1);
        }
        if((Calendar.getInstance().getTimeInMillis() - flashTime) >= 100 && this.flash) this.flash = false;
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

    public void hit() {
        this.flash = true;
        this.hits--;
        flashTime = Calendar.getInstance().getTimeInMillis();
    }
}
