/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.pause;

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
public class Pause3Title implements Pause {
    private int y, lastMouseX, lastMouseY;
    private boolean mouseClicked, active, hovered;
    private String text;
    private UnicodeFont font;
    private Color color;
    private SoundManager sm;
    private Shape bounds;
    private SpaceJunk sj;


    public Pause3Title(UnicodeFont font, Color color, SoundManager sm, SpaceJunk sj) {
        this.sj = sj;
        this.y = 0; this.lastMouseX = 0; this.lastMouseY = 0;
        this.mouseClicked = false; this.active = false;
        this.text = "Return to Title";
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
        Shape mouse = new Rectangle(Mouse.getX(), Display.getDisplayMode().getHeight() - Mouse.getY(), 1, 1);
        font.drawString((Display.getDisplayMode().getWidth() - font.getWidth("Really return to title?")) / 2, ((Display.getDisplayMode().getHeight() - font.getHeight("Really return to title?")) / 2) - 15, "Really return to title?", Color.red);

        Shape yes = new Rectangle(((Display.getDisplayMode().getWidth() - font.getWidth("Yes")) / 2) - 30, ((Display.getDisplayMode().getHeight() - font.getHeight("Yes")) / 2) + 15, font.getWidth("Yes"), font.getHeight("Yes"));
        Shape no = new Rectangle(((Display.getDisplayMode().getWidth() - font.getWidth("No")) / 2) + 30, ((Display.getDisplayMode().getHeight() - font.getHeight("No")) / 2) + 15, font.getWidth("No"), font.getHeight("No"));
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("Yes")) / 2) - 30, ((Display.getDisplayMode().getHeight() - font.getHeight("Yes")) / 2) + 15, "Yes", mouse.intersects(yes) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("No")) / 2) + 30, ((Display.getDisplayMode().getHeight() - font.getHeight("No")) / 2) + 15, "No", mouse.intersects(no) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);

        if((mouse.intersects(yes) || mouse.intersects(no)) && !this.hovered) {
            sm.playSoundEffect("ui.button.rollover", false);
            this.hovered = true;
        }
        else if(!mouse.intersects(yes) && !mouse.intersects(no)) {
            this.hovered = false;
        }

        if(Mouse.isButtonDown(0) && !this.mouseClicked) {
            if(mouse.intersects(yes)) {
                sm.playSoundEffect("ui.button.click", false);
                this.setActive(false);
                sj.setOnTitle(true);
            }
            if(mouse.intersects(no)) {
                sm.playSoundEffect("ui.button.click", false);
                this.setActive(false);
            }
        }
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

    public void setLastMouse(int lastMouseX, int lastMouseY) {
        this.lastMouseX = lastMouseX;
        this.lastMouseY = lastMouseY;
    }
}
