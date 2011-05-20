/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.particles;

import java.io.IOException;
import org.newdawn.slick.particles.*;
import com.spacejunk.SpaceJunk;

/**
 * 
 * @author Techjar
 */
public class Particle1Jet implements Particle {
    private SpaceJunk sj;
    private ParticleSystem sys;
    private int type;
    private float x, y;
    private boolean shipJet;


    public Particle1Jet(SpaceJunk sj, float x, float y, int type, boolean shipJet) throws IOException {
        this.sj = sj;
        this.type = type; this.shipJet = shipJet;
        this.sys = ParticleIO.loadConfiguredSystem("resources/particles/jet" + type + ".xml");
        this.x = x; this.y = y;
    }

    public void update() {
        sys.update(5);
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

    public int getType() {
        return this.type;
    }

    public boolean isShipJet() {
        return this.shipJet;
    }

    public boolean renderFirst() {
        return this.type == 0;
    }
}
