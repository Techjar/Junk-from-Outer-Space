/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.pause;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.Color;
import com.spacejunk.util.Bounds;
import com.spacejunk.SoundManager;
import com.spacejunk.SpaceJunk;
import com.spacejunk.util.ResolutionSorter;
import com.spacejunk.util.MathHelper;

/**
 * 
 * @author Techjar
 */
public class Pause0Options implements Pause {
    private int y, pressed, difficulty, displayInt;
    private boolean mouseClicked, active, hovered;
    private String text;
    private UnicodeFont font;
    private Color color;
    private SoundManager sm;
    private Bounds bounds;
    private DisplayMode displayMode;
    private DisplayMode[] displayModes;
    private List<DisplayMode> modeList;
    private String[] modeNames, diffModes = {"Easy", "Normal", "Hard", "Expert", "Oh God..."};
    private SpaceJunk sj;


    public Pause0Options(UnicodeFont font, Color color, SoundManager sm, SpaceJunk sj) throws LWJGLException {
        this.y = 0; this.pressed = 0; this.difficulty = sj.getDifficulty(); this.displayInt = 0;
        this.mouseClicked = false; this.active = false; this.hovered = false;
        this.text = "OPTIONS";
        this.sm = sm;
        this.color = color;
        this.font = font;
        this.bounds = new Bounds((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, font.getWidth(this.text), font.getHeight(this.text));
        this.displayMode = Display.getDisplayMode();
        this.displayModes = Display.getAvailableDisplayModes();
        this.modeList = new ArrayList<DisplayMode>();
        this.sj = sj;

        for (int i = 0; i < displayModes.length; i++) {
            DisplayMode cur = displayModes[i]; DisplayMode def = Display.getDesktopDisplayMode();
            if(cur.getBitsPerPixel() == def.getBitsPerPixel() && cur.getFrequency() == def.getFrequency()) modeList.add(cur);
        }
        Collections.sort(modeList, new ResolutionSorter());
        modeNames = new String[modeList.size()];
        for(int i = 0; i < modeList.size(); i++) {
            DisplayMode cur = modeList.get(i);
            modeNames[i] = cur.getWidth() + "x" + cur.getHeight();
        }

        for(int i = 0; i < modeList.size(); i++) {
            if(displayMode.getWidth() == modeList.get(i).getWidth() && displayMode.getHeight() == modeList.get(i).getHeight()) {
                this.displayMode = modeList.get(i);
                this.displayInt = i;
            }
        }
    }

    public void render() {
        this.bounds = new Bounds((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, font.getWidth(this.text), font.getHeight(this.text));
        font.drawString((Display.getDisplayMode().getWidth() - font.getWidth(this.text)) / 2, this.y, this.text, this.color);
    }

    public void renderScreen() {
        Bounds mouse = new Bounds(Mouse.getX(), Display.getDisplayMode().getHeight() - Mouse.getY(), 1, 1);
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("MUSIC VOLUME")) / 2) - 100, 100, "MUSIC VOLUME", Color.red);
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("SOUND VOLUME")) / 2) - 105, 130, "SOUND VOLUME", Color.red);
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("DIFFICULTY")) / 2) - 76, 160, "DIFFICULTY", Color.red);
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("VIDEO MODE")) / 2) - 83, 190, "VIDEO MODE", Color.red);
        font.drawString(((Display.getDisplayMode().getWidth() - font.getWidth("FULLSCREEN")) / 2) - 85, 220, "FULLSCREEN", Color.red);
        drawSquare(((Display.getDisplayMode().getWidth() - 200) / 2) + 100, 108, 200, 2, Color.darkGray);
        drawSquare(((Display.getDisplayMode().getWidth() - 200) / 2) + 100, 138, 200, 2, Color.darkGray);

        Bounds mus = new Bounds(((Display.getDisplayMode().getWidth() - 200) / 2) + 96 + Math.round(sm.getMusicVolume() * 200F), 100, 8, 18);
        Bounds snd = new Bounds(((Display.getDisplayMode().getWidth() - 200) / 2) + 96 + Math.round(sm.getSoundVolume() * 200F), 130, 8, 18);
        Bounds diff = new Bounds(Display.getDisplayMode().getWidth() / 2, 160, font.getWidth(diffModes[getDifficultyRev(this.difficulty)]), font.getHeight(diffModes[getDifficultyRev(this.difficulty)]));
        Bounds disp = new Bounds(Display.getDisplayMode().getWidth() / 2, 190, font.getWidth(modeNames[this.displayInt]), font.getHeight(modeNames[this.displayInt]));
        Bounds fs = new Bounds(Display.getDisplayMode().getWidth() / 2, 220, font.getWidth(Display.isFullscreen() ? "ON" : "OFF"), font.getHeight(Display.isFullscreen() ? "ON" : "OFF"));
        Bounds back = new Bounds((Display.getDisplayMode().getWidth() - font.getWidth("BACK")) / 2, 270, font.getWidth("BACK"), font.getHeight("BACK"));
        drawSlider(((Display.getDisplayMode().getWidth() - 200) / 2) + 100 + Math.round(sm.getMusicVolume() * 200F), 100, 8, 18, mouse.intersects(mus) || this.pressed == 1 ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);
        drawSlider(((Display.getDisplayMode().getWidth() - 200) / 2) + 100 + Math.round(sm.getSoundVolume() * 200F), 130, 8, 18, mouse.intersects(snd) || this.pressed == 2 ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);
        font.drawString(Display.getDisplayMode().getWidth() / 2, 160, diffModes[getDifficultyRev(this.difficulty)], mouse.intersects(diff) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);
        font.drawString(Display.getDisplayMode().getWidth() / 2, 190, modeNames[this.displayInt], mouse.intersects(disp) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);
        font.drawString(Display.getDisplayMode().getWidth() / 2, 220, Display.isFullscreen() ? "ON" : "OFF", mouse.intersects(fs) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);
        font.drawString((Display.getDisplayMode().getWidth() - font.getWidth("BACK")) / 2, 270, "BACK", mouse.intersects(back) ? Color.red.addToCopy(new Color(0, 50, 50)) : Color.red);

        if((mouse.intersects(mus) || mouse.intersects(snd) || mouse.intersects(diff) || mouse.intersects(disp) || mouse.intersects(back) || mouse.intersects(fs)) && !this.hovered && this.pressed == 0) {
            sm.playSoundEffect("ui.button.rollover", false);
            this.hovered = true;
        }
        else if(!mouse.intersects(mus) && !mouse.intersects(snd) && !mouse.intersects(back) && !mouse.intersects(diff) && !mouse.intersects(disp) && !mouse.intersects(fs)) {
            this.hovered = false;
        }

        if(Mouse.isButtonDown(0) && !this.mouseClicked) {
            int offset = ((Display.getDisplayMode().getWidth() - 200) / 2) + 100;
            if(mouse.intersects(mus) || this.pressed == 1) {
                this.pressed = 1;
                sm.setMusicVolume((float)(Mouse.getX() - offset) / 200F);
            }
            if(mouse.intersects(snd) || this.pressed == 2) {
                this.pressed = 2;
                sm.setSoundVolume((float)(Mouse.getX() - offset) / 200F);
            }
            if(mouse.intersects(diff) && this.pressed != 3) {
                sm.playSoundEffect("ui.button.click", false);
                this.pressed = 3;
                this.difficulty = this.getDifficulty(MathHelper.loop(this.getDifficultyRev(this.difficulty) + 1, 0, 4));
                sj.setDifficulty(this.difficulty);
            }
            if(mouse.intersects(disp) && this.pressed != 4) {
                sm.playSoundEffect("ui.button.click", false);
                this.pressed = 4;
                this.displayInt = MathHelper.loop(this.displayInt + 1, 0, modeList.size() - 1);
                this.displayMode = modeList.get(this.displayInt);
                try {
                    sj.changeDisplayMode(this.displayMode, Display.isFullscreen());
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            if(mouse.intersects(fs) && this.pressed != 5) {
                sm.playSoundEffect("ui.button.click", false);
                this.pressed = 5;
                try {
                    sj.changeDisplayMode(this.displayMode, !Display.isFullscreen());
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            if(mouse.intersects(back)) {
                sm.playSoundEffect("ui.button.click", false);
                this.setActive(false);
            }
        }
        else {
            this.pressed = 0;
        }
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

    private void drawSquare(int x, int y, int width, int height, Color color) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);

        glTranslatef(x, y, 0);
        glColor3f(color.r, color.g, color.b);
        glBegin(GL_QUADS);
            glTexCoord2f(0, 0); glVertex2f(0, 0);
            glTexCoord2f(1, 0); glVertex2f(width, 0);
            glTexCoord2f(1, 1); glVertex2f(width, height);
            glTexCoord2f(0, 1); glVertex2f(0, height);
        glEnd();

        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }

    private void drawSlider(int x, int y, int width, int height, Color color) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);

        glTranslatef(x, y, 0);
        glTranslatef(-(width >> 1), 0, 0);
        glColor3f(color.r, color.g, color.b);
        glBegin(GL_QUADS);
            glTexCoord2f(0, 0); glVertex2f(0, 0);
            glTexCoord2f(1, 0); glVertex2f(width, 0);
            glTexCoord2f(1, 1); glVertex2f(width, height);
            glTexCoord2f(0, 1); glVertex2f(0, height);
        glEnd();

        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }

    private int getDifficulty(int diff) {
        switch(diff) {
            case 0: return 1;
            case 1: return 4;
            case 2: return 7;
            case 3: return 10;
            case 4: return 100;
            default: return 1;
        }
    }

    private int getDifficultyRev(int diff) {
        switch(diff) {
            case 1: return 0;
            case 4: return 1;
            case 7: return 2;
            case 10: return 3;
            case 100: return 4;
            default: return 0;
        }
    }
}
