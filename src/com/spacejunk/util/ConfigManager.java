/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * 
 * @author Techjar
 */
public final class ConfigManager {
    public static final int VERSION = 2;


    private static int getVersion() {
        try {
            if(!new File("options.yml").exists()) init();
            Yaml yaml = new Yaml();
            Map<String, Object> cfg = (Map)yaml.load(new FileReader("options.yml"));
            return cfg.containsKey("version") ? Integer.parseInt(cfg.get("version").toString()) : 0;
        }
        catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Object getProperty(String prop) {
        try {
            if(!new File("options.yml").exists()) init();
            if(getVersion() != VERSION) update();
            Yaml yaml = new Yaml();
            Map<String, Object> cfg = (Map)yaml.load(new FileReader("options.yml"));
            return cfg.containsKey(prop) ? cfg.get(prop) : "";
        }
        catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static void setProperty(String prop, Object value) {
        try {
            if(!new File("options.yml").exists()) init();
            if(getVersion() != VERSION) update();
            DumperOptions dumper = new DumperOptions();
            dumper.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
            dumper.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumper.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);

            Yaml yaml = new Yaml(dumper);
            Map<String, Object> cfg = (Map)yaml.load(new FileReader("options.yml")); cfg.put(prop, value);
            FileWriter fw = new FileWriter("options.yml");
            yaml.dump(cfg, fw); fw.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes(); DisplayMode defMode = Display.getDesktopDisplayMode();
            for(int i = 0; i < modes.length && defMode == Display.getDesktopDisplayMode(); i++) {
                if(modes[i].getWidth() * modes[i].getHeight() == 800 * 600) defMode = modes[i];
            }
            for(int i = 0; i < modes.length && defMode == Display.getDesktopDisplayMode(); i++) {
                if(modes[i].getWidth() * modes[i].getHeight() == 640 * 480) defMode = modes[i];
            }
            for(int i = 0; i < modes.length && defMode == Display.getDesktopDisplayMode(); i++) {
                if(modes[i].getWidth() * modes[i].getHeight() == 1024 * 768) defMode = modes[i];
            }

            DumperOptions dumper = new DumperOptions();
            dumper.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
            dumper.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumper.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);

            Yaml yaml = new Yaml(dumper); Map<String, Object> cfg = new HashMap<String, Object>();
            cfg.put("version", VERSION);
            cfg.put("music-volume", 1.0F);
            cfg.put("sound-volume", 1.0F);
            cfg.put("difficulty", 0);
            cfg.put("video-width", defMode.getWidth());
            cfg.put("video-height", defMode.getHeight());
            cfg.put("fullscreen", false);
            cfg.put("vertical-sync", true);
            cfg.put("high-score", 0);

            new File("options.yml").createNewFile();
            FileWriter fw = new FileWriter("options.yml");
            yaml.dump(cfg, fw); fw.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes(); DisplayMode defMode = Display.getDesktopDisplayMode();
            for(int i = 0; i < modes.length && defMode == Display.getDesktopDisplayMode(); i++) {
                if(modes[i].getWidth() * modes[i].getHeight() == 800 * 600) defMode = modes[i];
            }
            for(int i = 0; i < modes.length && defMode == Display.getDesktopDisplayMode(); i++) {
                if(modes[i].getWidth() * modes[i].getHeight() == 640 * 480) defMode = modes[i];
            }
            for(int i = 0; i < modes.length && defMode == Display.getDesktopDisplayMode(); i++) {
                if(modes[i].getWidth() * modes[i].getHeight() == 1024 * 768) defMode = modes[i];
            }

            DumperOptions dumper = new DumperOptions();
            dumper.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
            dumper.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumper.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);

            Yaml yaml = new Yaml(dumper); Map<String, Object> cfg = (Map)yaml.load(new FileReader("options.yml"));
            cfg.put("version", VERSION);
            if(!cfg.containsKey("music-volume")) cfg.put("music-volume", 1.0F);
            if(!cfg.containsKey("sound-volume")) cfg.put("sound-volume", 1.0F);
            if(!cfg.containsKey("difficulty")) cfg.put("difficulty", 0);
            if(!cfg.containsKey("video-width")) cfg.put("video-width", defMode.getWidth());
            if(!cfg.containsKey("video-height")) cfg.put("video-height", defMode.getHeight());
            if(!cfg.containsKey("fullscreen")) cfg.put("fullscreen", false);
            if(!cfg.containsKey("vertical-sync")) cfg.put("vertical-sync", true);
            if(!cfg.containsKey("high-score")) cfg.put("high-score", 0);
            FileWriter fw = new FileWriter("options.yml");
            yaml.dump(cfg, fw); fw.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
