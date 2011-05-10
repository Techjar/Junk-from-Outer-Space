/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

/**
 * Class containing extra math-based commands which don't exist in Java.lang.Math, such as clamp().
 * @author Techjar
 */
public class MathHelper {
    /**
     * Creates a new MathHelper.
     */
    public MathHelper() {
    }
    
    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public int clamp(int i, int low, int high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public long clamp(long i, long low, long high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public double clamp(double i, double low, double high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Converts input number to a number within the specified range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return clamped number
     */
    public float clamp(float i, float low, float high) {
        return Math.max(Math.min(i, high), low);
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public int loop(int i, int low, int high) {
        if(i < low) return high;
        if(i > high) return low;
        return i;
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public long loop(long i, long low, long high) {
        if(i < low) return high;
        if(i > high) return low;
        return i;
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public double loop(double i, double low, double high) {
        if(i < low) return high;
        if(i > high) return low;
        return i;
    }

    /**
     * Loops a number around in a range.
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public float loop(float i, float low, float high) {
        if(i < low) return high;
        if(i > high) return low;
        return i;
    }
}
