/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk;

import com.spacejunk.util.ConfigManager;
import com.spacejunk.util.ResolutionSorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import com.spacejunk.util.StackTrace;

/**
 * Asteroids main class.
 * @author Techjar
 */
public class Main {
    private static final boolean USE_LAUNCHER = false;
    private static DisplayMode[] displayModes;
    private static List<DisplayMode> modeList;


    /**
     * Asteroids main method.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            if(USE_LAUNCHER) {
                LauncherGUI launcher = null;
                try {
                    //launcher = new LauncherGUI("Junk from Outer Space Launcher", args.length < 1 ? false : args[0].equalsIgnoreCase("render-collision"));
                }
                catch(Exception e) {
                    e.printStackTrace();
                    if(launcher != null) launcher.dispose();
                    System.exit(0);
                }
            }
            else {
                displayModes = Display.getAvailableDisplayModes(); modeList = new ArrayList<DisplayMode>();
                for (int i = 0; i < displayModes.length; i++) {
                    DisplayMode cur = displayModes[i]; DisplayMode def = Display.getDesktopDisplayMode();
                    if(cur.getBitsPerPixel() == def.getBitsPerPixel() && cur.getFrequency() == def.getFrequency()) modeList.add(cur);
                }
                Collections.sort(modeList, new ResolutionSorter());
                String[] modeNames = new String[modeList.size()]; int defMode = 0;
                for(int i = 0; i < modeList.size(); i++) {
                    DisplayMode cur = modeList.get(i);
                    if(cur.getWidth() * cur.getHeight() == Integer.parseInt(ConfigManager.getProperty("video-width").toString()) * Integer.parseInt(ConfigManager.getProperty("video-height").toString())) defMode = i;
                    modeNames[i] = cur.getWidth() + "x" + cur.getHeight();
                }

                SpaceJunk sj = null;
                try {
                    sj = new SpaceJunk(getDifficulty(Integer.parseInt(ConfigManager.getProperty("difficulty").toString())), modeList.get(defMode), Boolean.parseBoolean(ConfigManager.getProperty("fullscreen").toString()), Boolean.parseBoolean(ConfigManager.getProperty("vertical-sync").toString()), Float.parseFloat(ConfigManager.getProperty("music-volume").toString()), Float.parseFloat(ConfigManager.getProperty("sound-volume").toString()), args.length < 1 ? false : args[0].equalsIgnoreCase("render-collision"));
                    sj.create();
                    sj.run();
                }
                catch(Throwable e) {
                    e.printStackTrace();
                    org.lwjgl.Sys.alert("Java Error", StackTrace.stackTraceToString(e));
                }
                finally {
                    if(sj != null) sj.destroy();
                    System.exit(0);
                }
            }
        }
        catch(Throwable e) {
            e.printStackTrace();
            org.lwjgl.Sys.alert("Java Error", StackTrace.stackTraceToString(e));
            System.exit(0);
        }
    }

    private static int getDifficulty(int diff) {
        switch(diff) {
            case 0: return 1;
            case 1: return 4;
            case 2: return 7;
            case 3: return 10;
            case 4: return 100;
            default: return 1;
        }
    }
}
