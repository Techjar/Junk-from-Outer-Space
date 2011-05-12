/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

import org.lwjgl.opengl.DisplayMode;
import java.util.Comparator;

/**
 * 
 * @author Techjar
 */
public class ResolutionSorter implements Comparator<DisplayMode> {
    public ResolutionSorter() {
    }

    public int compare(DisplayMode o1, DisplayMode o2) throws ClassCastException {
        if(o1.getWidth() * o1.getHeight() > o2.getWidth() * o2.getHeight()) return 1;
        if(o1.getWidth() * o1.getHeight() < o2.getWidth() * o2.getHeight()) return -1;
        return 0;
    }
}
