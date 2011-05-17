/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.pause;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import com.spacejunk.util.Bounds;
import com.spacejunk.SoundManager;
import com.spacejunk.SpaceJunk;

/**
 * 
 * @author Techjar
 */
public class Pause2Resume implements Pause {
    private int y, lastMouseX, lastMouseY;
    private boolean mouseClicked, active;
    private String text;
    private UnicodeFont font;
    private Color color;
    private SoundManager sm;
    private Bounds bounds;


    public Pause2Resume(UnicodeFont font, Color color, SoundManager sm, SpaceJunk sj) {
        this.y = 0; this.lastMouseX = 0; this.lastMouseY = 0;
        this.mouseClicked = false; this.active = false;
        this.text = "RESUME GAME";
        this.sm = sm;
        this.color = color;
        this.font = font;
        this.bounds = new Bounds((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, font.getWidth(this.text), font.getHeight(this.text));
    }

    public void render() {
        this.bounds = new Bounds((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, font.getWidth(this.text), font.getHeight(this.text));
        font.drawString((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, this.text, this.color);
    }

    public void renderScreen() {
        this.setActive(false);
        Mouse.setCursorPosition(lastMouseX, lastMouseY);
        Mouse.setGrabbed(true);
        if(!Mouse.isButtonDown(0) && this.mouseClicked) this.mouseClicked = false;
    }

    public Bounds getBounds() {
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

    public void setLastMouse(int lastMouseX, int lastMouseY) {
        this.lastMouseX = lastMouseX;
        this.lastMouseY = lastMouseY;
    }
}
