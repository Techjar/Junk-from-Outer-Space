/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import static org.lwjgl.opengl.GL11.*;

import com.spacejunk.SoundManager;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.TickCounter;
import java.util.List;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.geom.*;
import com.spacejunk.particles.*;

/**
 * 
 * @author Techjar
 */
public class Sprite5Explosion implements WeaponSprite {
    private List<Sprite> sprites;
    private List<Particle> particles;
    private int id, x, y, radius, damage, type;
    private long radiusTime;
    private boolean visible, reduceRadius;
    private SoundManager sm;
    private Shape bounds;
    private SpaceJunk sj;
    private TickCounter tc;
    private Particle sys;


    public Sprite5Explosion(SpaceJunk sj, List sprites, List particles, SoundManager sm, int x, int y, int type) {
        try {
            this.sj = sj;
            this.tc = sj.getTickCounter();
            this.sprites = sprites; this.particles = particles;
            this.id = 5; this.x = x; this.y = y; this.damage = 50; this.type = type; this.visible = true; this.reduceRadius = false;
            this.radiusTime = 0; this.sm = sm;
            this.sys = new Particle0Explosion(this.sj, this.x, this.y, 1200, 2); particles.add(sys);
            this.bounds = new Circle(this.x, this.y, this.radius);
            sm.playSoundEffect("ambient.explode.2", false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.setVisible(sys.isVisible());
        if(tc.getTickMillis() - this.radiusTime >= 20 && this.radius < 150 && !this.reduceRadius) {
            this.radius += 5;
            this.radiusTime = tc.getTickMillis();
            if(this.radius >= 150) this.reduceRadius = true;
        }
        if(tc.getTickMillis() - this.radiusTime >= 20 && this.radius > 0 && this.reduceRadius) {
            this.radius -= 20;
            this.radiusTime = tc.getTickMillis();
        }
        ((Circle)bounds).setRadius(radius);
        bounds.setCenterX(this.x); bounds.setCenterY(this.y);
    }

    public void render() {
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

    public void impact() {
    }

    public int getDamage() {
        return this.damage;
    }
}
