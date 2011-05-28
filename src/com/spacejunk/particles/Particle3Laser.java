/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.particles;

import java.io.IOException;
import org.newdawn.slick.particles.*;
import com.spacejunk.SpaceJunk;
import org.newdawn.slick.geom.Vector2f;

/**
 * 
 * @author Techjar
 */
public class Particle3Laser implements Particle {
    private SpaceJunk sj;
    private ParticleSystem sys;
    private int type;
    private float x, y;


    public Particle3Laser(SpaceJunk sj, float x, float y, int type) throws IOException {
        this.sj = sj;
        this.type = type;
        this.sys = ParticleIO.loadConfiguredSystem("resources/particles/laser" + type + ".xml");
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

    public int getType() {
        return this.type;
    }

    public int getRenderLayer() {
        return 2;
    }

    public void setLocation(Vector2f location) {
        this.x = location.getX();
        this.y = location.getY();
    }
}
