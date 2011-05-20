/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import org.newdawn.slick.opengl.Texture;
import com.spacejunk.particles.*;
import com.spacejunk.SoundManager;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.*;
import java.io.FileInputStream;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * 
 * @author Techjar
 */
public class Sprite4Powerup implements PowerupSprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y;
    private boolean visible;
    private String type;
    private Texture tex;
    private SpaceJunk sj;
    private TickCounter tc;
    private SoundManager sm;
    private Shape bounds;
    private Particle2Glow glow;


    public Sprite4Powerup(SpaceJunk sj, List sprites, List particles, SoundManager sm, int x, int y, String type) {
        try {
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.sprites = sprites; this.particles = particles;
            this.id = 4; this.x = x; this.y = y; this.visible = true; this.type = type;
            this.tex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/powerups/" + type.toLowerCase() + ".png"), GL_LINEAR);
            this.sm = sm;
            this.bounds = new Circle(this.x + 16, this.y + 16, 16);
            this.glow = new Particle2Glow(this.sj, this.x + 16, this.y + 16, 0);
            particles.add(this.glow);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update() {
        this.x -= 3;
        if(this.x + 32 < 0) this.setVisible(false);
        bounds.setCenterX(this.x + 16); bounds.setCenterY(this.y + 16);
        glow.setX(this.x + 16); glow.setY(this.y + 16);
        glow.setVisible(this.visible);
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

    public String getPowerupType() {
        return this.type;
    }

}
