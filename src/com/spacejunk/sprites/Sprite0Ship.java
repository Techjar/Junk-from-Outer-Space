/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import com.spacejunk.SoundManager;
import com.spacejunk.particles.*;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.*;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

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
    private Map<String, Long> powerupTime, powerupLife;
    private Texture tex, guntex, rocketTex;
    private SoundManager sm;
    private SpaceJunk sj;
    private Particle1Jet jet;
    private Shape bounds;
    private TickCounter tc;
    private UnicodeFont font;
    private Texture[] powerupTex;


    public Sprite0Ship(List sprites, List particles, int x, int y, SoundManager sm, SpaceJunk sj) {
        try {
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.sprites = sprites; this.particles = particles;
            this.id = 0; this.x = x; this.y = y;
            this.lastFire = 0; this.hitTime = 0; this.invFrameTime = 0; this.invTime = 0; this.invForTime = 0;
            this.visible = true; this.hit = false; this.invincible = false; this.invState = true; this.respawning = false;
            this.powerups = new ArrayList<String>();
            this.powerupTime = new HashMap<String, Long>(); this.powerupLife = new HashMap<String, Long>();
            this.tex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/ship.png"), GL_NEAREST);
            this.guntex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/gunfire.png"), GL_LINEAR);
            this.rocketTex = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/rocket.png"), GL_NEAREST);
            this.sm = sm;
            this.jet = new Particle1Jet(sj, this.x - 26, this.y + 16, 0, true);
            this.bounds = new Rectangle(this.x - 28, this.y + 2, 22, 28);
            //this.bounds = new Polygon(PolygonHitbox.SHIP);
            particles.add(jet);

            // Setup map stuff
            powerupTime.put(Sprite3Rocket.KEY_NAME, 0L);

            // Powerup textures
            this.powerupTex = new Texture[Powerup.TOTAL_POWERUPS];
            this.powerupTex[0] = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/powerups/fastshot.png"), GL_LINEAR);
            this.powerupTex[1] = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/powerups/bigshot.png"), GL_LINEAR);
            this.powerupTex[2] = TextureLoader.getTexture("PNG", new FileInputStream("resources/textures/powerups/rocket.png"), GL_LINEAR);

            // Load font
            this.font = new UnicodeFont("resources/fonts/batmfa_.ttf", 10, false, false);
            font.addAsciiGlyphs();
            font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
            font.loadGlyphs();
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
                        sprites.add(new Sprite1Gunfire(this.sj, sprites, particles, sm, this.x - 16, this.y - 3, this.guntex, powerups.contains(Powerup.BIGSHOT)));
                        sprites.add(new Sprite1Gunfire(this.sj, sprites, particles, sm, this.x - 16, this.y + 19, this.guntex, powerups.contains(Powerup.BIGSHOT)));
                        sm.playSoundEffect("ship.gunfire", false);
                    }
                }
                else if(Mouse.isButtonDown(1)) {
                    if(tc.getTickMillis() - powerupTime.get(Sprite3Rocket.KEY_NAME) >= Sprite3Rocket.SHOT_DELAY && powerups.contains(Powerup.ROCKET)) {
                        powerupTime.put(Sprite3Rocket.KEY_NAME, tc.getTickMillis());
                        sprites.add(new Sprite3Rocket(this.sj, sprites, particles, sm, this.x - 7, this.y + 8, this.rocketTex));
                    }
                }
            }
        }
        else {
            if((tc.getTickMillis() - hitTime) >= 2000) {
                this.setVisible(true);
                this.setInvincible(true); invTime = tc.getTickMillis(); invForTime = 1000;
                this.hit = false;
                this.respawning = true;
                sj.clearScreen();
            }
        }
        bounds.setCenterX(this.x - 17); bounds.setCenterY(this.y + 16);
        //(Sprite3Rocket.POWERUP_LIFE - (tc.getTickMillis() - this.taters)) / 1000;
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

        glPushMatrix();
        DisplayMode dm = Display.getDisplayMode(); long ptime = 0, pstime = 0;
        if(powerups.contains(Powerup.ROCKET) && powerupLife.containsKey(Powerup.ROCKET)) {
            ptime = tc.getTickMillis() - powerupLife.get(Powerup.ROCKET);
            if(ptime >= Sprite3Rocket.POWERUP_LIFE) {
                this.removePowerup(Powerup.ROCKET);
            }
            else {
                pstime = ((Sprite3Rocket.POWERUP_LIFE - ptime) / 1000) + 1;
                drawPowerupIcon(this.powerupTex[2], dm.getWidth() - 18, 2);
                glPushMatrix(); font.drawString((dm.getWidth() - (font.getWidth(Long.toString(pstime)) / 2)) - 10, 18, Long.toString(pstime), Color.white); glPopMatrix();
            }
        }

        if(powerups.contains(Powerup.FASTSHOT) && powerupLife.containsKey(Powerup.FASTSHOT)) {
            ptime = tc.getTickMillis() - powerupLife.get(Powerup.FASTSHOT);
            if(ptime >= 30000) {
                this.removePowerup(Powerup.FASTSHOT);
            }
            else {
                pstime = ((30000 - ptime) / 1000) + 1;
                drawPowerupIcon(this.powerupTex[0], dm.getWidth() - 36, 2);
                glPushMatrix(); font.drawString((dm.getWidth() - (font.getWidth(Long.toString(pstime)) / 2)) - 28, 18, Long.toString(pstime), Color.white); glPopMatrix();
            }
        }

        if(powerups.contains(Powerup.BIGSHOT) && powerupLife.containsKey(Powerup.BIGSHOT)) {
            ptime = tc.getTickMillis() - powerupLife.get(Powerup.BIGSHOT);
            if(ptime >= Sprite1Gunfire.POWERUP_LIFE) {
                this.removePowerup(Powerup.BIGSHOT);
            }
            else {
                pstime = ((Sprite1Gunfire.POWERUP_LIFE - ptime) / 1000) + 1;
                drawPowerupIcon(this.powerupTex[1], dm.getWidth() - 54, 2);
                glPushMatrix(); font.drawString((dm.getWidth() - (font.getWidth(Long.toString(pstime)) / 2)) - 46, 18, Long.toString(pstime), Color.white); glPopMatrix();
            }
        }
        glPopMatrix();
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
            powerups.clear();
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

    public boolean isRespawning() {
        return this.respawning;
    }

    public Shape getBounds() {
        return this.bounds;
    }

    public void addPowerup(String powerup) {
        if(powerup.equals(Powerup.ROCKET)) {
            if(powerups.contains(Powerup.ROCKET)) powerups.remove(Powerup.ROCKET);
        }
        powerups.add(powerup);
        powerupLife.put(powerup, tc.getTickMillis());
    }

    public void removePowerup(String powerup) {
        if(powerups.contains(powerup)) {
            powerups.remove(powerup);
            powerupLife.remove(powerup);
        }
    }

    private void drawPowerupIcon(Texture tex, int x, int y) {
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
            glTexCoord2f(0, tex.getHeight()); glVertex2f(0, tex.getImageHeight() / 2);
            glTexCoord2f(tex.getWidth(), tex.getHeight()); glVertex2f(tex.getImageWidth() / 2, tex.getImageHeight() / 2);
            glTexCoord2f(tex.getWidth(), 0); glVertex2f(tex.getImageWidth() / 2, 0);
        glEnd();

        // restore the model view matrix to prevent contamination
        glPopMatrix();
    }
}
