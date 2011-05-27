/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

/**
 * 
 * @author Techjar
 */
public class OperatingSystem {
    public static final int UNKNOWN = 0;
    public static final int WINDOWS = 1;
    public static final int MAC = 2;
    public static final int LINUX = 3;
    public static final int SOLARIS = 4;

    public static int getSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win") >= 0) return 1;
        if(os.indexOf("mac") >= 0) return 2;
        if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) return 3;
        if(os.indexOf("solaris") >= 0) return 4;
        return 0;
    }
}
