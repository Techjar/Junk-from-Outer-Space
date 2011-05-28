/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.particles;

import java.io.IOException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.*;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.TickCounter;

/**
 * 
 * @author Techjar
 */
public class Particle0Explosion implements Particle {
    private SpaceJunk sj;
    private ParticleSystem sys;
    private int life;
    private long time;
    private float x, y;
    private TickCounter tc;


    public Particle0Explosion(SpaceJunk sj, float x, float y, int life, int type) throws IOException {
        this.sj = sj;
        this.tc = sj.getTickCounter();
        this.sys = ParticleIO.loadConfiguredSystem("resources/particles/explosion" + type + ".xml", type == 3 ? 10000 : 2000);
        this.life = life;
        this.time = tc.getTickMillis();
        this.x = x; this.y = y;
    }

    public void update() {
        if(tc.getTickMillis() - this.time >= this.life) sys.setVisible(false);
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

    public int getRenderLayer() {
        return 1;
    }

    public void setLocation(Vector2f location) {
        this.x = location.getX();
        this.y = location.getY();
    }
}
