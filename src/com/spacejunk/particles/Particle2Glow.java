/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.particles;

import java.io.IOException;
import org.newdawn.slick.particles.*;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.TickCounter;

/**
 * 
 * @author Techjar
 */
public class Particle2Glow implements Particle {
    private SpaceJunk sj;
    private ParticleSystem sys;
    private float x, y;
    private TickCounter tc;


    public Particle2Glow(SpaceJunk sj, float x, float y, int type) throws IOException {
        this.sj = sj;
        this.tc = sj.getTickCounter();
        this.sys = ParticleIO.loadConfiguredSystem("resources/particles/glow" + type + ".xml");
        this.x = x; this.y = y;
    }

    public void update() {
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

    public boolean renderFirst() {
        return true;
    }
}
