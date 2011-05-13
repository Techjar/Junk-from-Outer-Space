/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.particles;

import java.io.IOException;
import java.util.Calendar;
import org.newdawn.slick.particles.*;
import com.spacejunk.SpaceJunk;

/**
 * 
 * @author Techjar
 */
public class Particle0Explosion implements Particle {
    private ParticleSystem sys;
    private int life;
    private long time;
    private float x, y;


    public Particle0Explosion(SpaceJunk sj, float x, float y, int life, int mode) throws IOException {
        this.sys = ParticleIO.loadConfiguredSystem("resources/particles/explosion" + mode + ".xml");
        this.life = life;
        this.time = Calendar.getInstance().getTimeInMillis();
        this.x = x; this.y = y;
    }

    public void update() {
        if(Calendar.getInstance().getTimeInMillis() - this.time >= this.life) sys.setVisible(false);
        sys.update(17);
    }

    public void render() {
        sys.render(this.x, this.y);
    }

    public void setVisible(boolean visible) {
        sys.setVisible(visible);
    }

    public boolean isVisible() {
        return sys.isVisible();
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
}
