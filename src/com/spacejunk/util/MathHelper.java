/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

/**
 * Class containing extra math-based commands which don't exist in Java.lang.Math, such as clamp().
 * @author Techjar
 */
public abstract class MathHelper {
    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public static int clamp(int i, int low, int high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public static long clamp(long i, long low, long high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public static double clamp(double i, double low, double high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public static float clamp(float i, float low, float high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static int loop(int i, int low, int high) {
        if(i < low) return high - (low - (i + 1));
        if(i > high) return low + (i - (high + 1));
        return i;
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static long loop(long i, long low, long high) {
        if(i < low) return high - (low - (i + 1));
        if(i > high) return low + (i - (high + 1));
        return i;
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static double loop(double i, double low, double high) {
        if(i < low) return high - (low - (i + 1));
        if(i > high) return low + (i - (high + 1));
        return i;
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static float loop(float i, float low, float high) {
        if(i < low) return high - (low - (i + 1));
        if(i > high) return low + (i - (high + 1));
        return i;
    }
}
