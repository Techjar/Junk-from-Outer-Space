/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

/**
 * 
 * @author Techjar
 */
public final class Powerup {
    public static final String NONE = "NONE";
    public static final String FASTSHOT = "FASTSHOT";
    public static final String BIGSHOT = "BIGSHOT";
    public static final String ROCKET = "ROCKET";
    public static final String INVINCIBILITY = "INVINCIBILITY";
    public static final String LASER = "LASER";
    public static final String NUKE = "NUKE";
    public static final String[] ALL_POWERUPS = {FASTSHOT, BIGSHOT, ROCKET, INVINCIBILITY, LASER, NUKE};
    public static final int TOTAL_POWERUPS = ALL_POWERUPS.length - 3;
}
