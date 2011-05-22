/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.title;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.*;
import com.spacejunk.SoundManager;
import com.spacejunk.SpaceJunk;

/**
 *
 * @author Techjar
 */
public class Title2StartGame implements Title {
    private int y;
    private boolean mouseClicked, active, hovered;
    private String text;
    private UnicodeFont font;
    private Color color;
    private SoundManager sm;
    private Shape bounds;
    private SpaceJunk sj;


    public Title2StartGame(UnicodeFont font, Color color, SoundManager sm, SpaceJunk sj) {
        this.sj = sj;
        this.y = 0;
        this.mouseClicked = false; this.active = false; this.hovered = false;
        this.text = "START GAME";
        this.sm = sm;
        this.color = color;
        this.font = font;
        this.bounds = new Rectangle((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, font.getWidth(this.text), font.getHeight(this.text));
    }

    public void render() {
        this.bounds = new Rectangle((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, font.getWidth(this.text), font.getHeight(this.text));
        font.drawString((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, this.text, this.color);
    }

    public void renderScreen() {
        this.setActive(false);
        sj.setOnTitle(false);
        if(!Mouse.isButtonDown(0) && this.mouseClicked) this.mouseClicked = false;
    }

    public Shape getBounds() {
        return this.bounds;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTextY(int y) {
        this.y = y;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getText() {
        return this.text;
    }

    public void setMouseClicked(boolean clicked) {
        this.mouseClicked = clicked;
    }
}
