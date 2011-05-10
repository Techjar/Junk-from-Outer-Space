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

/**
 * 
 * @author Techjar
 */
public class Sprite0Ship implements Sprite {
    private List<Sprite> sprites;
    private int id, x, y;
    private long lastFire, hitTime;
    private boolean visible, hit;
    private Texture tex;
    private SoundManager sm;


    public Sprite0Ship(List sprites, int x, int y, SoundManager sm) {
        try {
            this.sprites = sprites;
            this.id = 0; this.x = x; this.y = y; this.lastFire = 0; this.hitTime = 0; this.visible = true; this.hit = false;
            this.tex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/ship.png"), GL_NEAREST);
            this.sm = sm;
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public void update() {
        Calendar cal = Calendar.getInstance();
        if(!this.hit) {
            this.y = (Display.getDisplayMode().getHeight() - Mouse.getY()) - (tex.getImageHeight() / 2);
            if(Mouse.isButtonDown(0) && (cal.getTimeInMillis() - lastFire) >= 200) {
                lastFire = cal.getTimeInMillis();
                sprites.add(new Sprite1Gunfire(sprites, this.x, this.y - 3));
                sprites.add(new Sprite1Gunfire(sprites, this.x, this.y + 19));
                sm.playSoundEffect("ship.gunfire");
            }
        }
        else {
            if((cal.getTimeInMillis() - hitTime) >= 2000) this.hit = false;
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

    public void hit() {
        this.hit = true;
        hitTime = Calendar.getInstance().getTimeInMillis();
    }

    public boolean isHit() {
        return this.hit;
    }
}
