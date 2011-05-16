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

/**
 * 
 * @author Techjar
 */
public class Pause1Quit implements Pause {
    private int y;
    private boolean active, hovered;
    private String text;
    private UnicodeFont font;
    private Color color;
    private SoundManager sm;
    private Bounds bounds;


    public Pause1Quit(UnicodeFont font, Color color, SoundManager sm) {
        this.y = 0;
        this.active = false; this.hovered = false;
        this.text = "QUIT";
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
        Bounds mouse = new Bounds(Mouse.getX(), Display.getDisplayMode().getHeight() - Mouse.getY(), 1, 1);
        font.drawString((Display.getDisplayMode().getWidth() - font.getWidth("REALLY QUIT?")) / 2, ((Display.getDisplayMode().getHeight() - font.getHeight("REALLY QUIT?")) / 2) - 15, "REALLY QUIT?", Color.red);

        Bounds yes = new Bounds(((Display.getDisplayMode().getWidth() - font.getWidth("YES")) / 2) - 30, ((Display.getDisplayMode().getHeight() - font.getHeight("YES")) / 2) + 15, font.getWidth("YES"), font.getHeight("YES"));
        Bounds no = new Bounds(((Display.getDisplayMode().getWidth() - font.getWidth("NO")) / 2) + 30, ((Display.getDisplayMode().getHeight() - font.getHeight("NO")) / 2) + 15, font.getWidth("NO"), font.getHeight("NO"));
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("YES")) / 2) - 30, ((Display.getDisplayMode().getHeight() - font.getHeight("YES")) / 2) + 15, "YES", mouse.intersects(yes) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("NO")) / 2) + 30, ((Display.getDisplayMode().getHeight() - font.getHeight("NO")) / 2) + 15, "NO", mouse.intersects(no) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);

        if((mouse.intersects(yes) || mouse.intersects(no)) && !this.hovered) {
            sm.playSoundEffect("ui.button.rollover", false);
            this.hovered = true;
        }
        else if(!mouse.intersects(yes) && !mouse.intersects(no)) {
            this.hovered = false;
        }

        if(Mouse.isButtonDown(0)) {
            if(mouse.intersects(yes)) {
                sm.playSoundEffect("ui.button.click", false);
                System.exit(0);
            }
            if(mouse.intersects(no)) {
                sm.playSoundEffect("ui.button.click", false);
                this.setActive(false);
            }
        }
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
}
