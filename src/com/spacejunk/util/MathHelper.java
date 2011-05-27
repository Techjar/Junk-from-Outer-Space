/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

/**
 * Class containing extra math-based commands which don't exist in java.lang.Math, such as clamp().
 * @author Techjar
 */
public final class MathHelper {
    private MathHelper() {
    }

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
     * Loops a number around in a range. POSITIVE NUMBERS ONLY!
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static int loop(int i, int low, int high) {
        if(low < 0 || high < 0) throw new NumberFormatException("loop() does not accept a negative low or high value.");
        int j = i;
        if(j < low) {
            while(j + high <= low) j += high;
            return high - (low - (j + 1));
        }
        if(j > high) {
            while(j - high >= high) j -= high;
            return low + (j - (high + 1));
        }
        return i;
    }

    /**
     * Loops a number around in a range. POSITIVE NUMBERS ONLY!
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static long loop(long i, long low, long high) {
        if(low < 0 || high < 0) throw new NumberFormatException("loop() does not accept a negative low or high value.");
        long j = i;
        if(j < low) {
            while(j + high <= low) j += high;
            return high - (low - (j + 1));
        }
        if(j > high) {
            while(j - high >= high) j -= high;
            return low + (j - (high + 1));
        }
        return i;
    }

    /**
     * Loops a number around in a range. POSITIVE NUMBERS ONLY!
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static double loop(double i, double low, double high) {
        if(low < 0 || high < 0) throw new NumberFormatException("loop() does not accept a negative low or high value.");
        double j = i;
        if(j < low) {
            while(j + high <= low) j += high;
            return high - (low - (j + 1));
        }
        if(j > high) {
            while(j - high >= high) j -= high;
            return low + (j - (high + 1));
        }
        return i;
    }

    /**
     * Loops a number around in a range. POSITIVE NUMBERS ONLY!
     * @param i input number
     * @param low minimum range
     * @param high maximum range
     * @return looped number
     */
    public static float loop(float i, float low, float high) {
        if(low < 0 || high < 0) throw new NumberFormatException("loop() does not accept a negative low or high value.");
        float j = i;
        if(j < low) {
            while(j + high <= low) j += high;
            return high - (low - (j + 1));
        }
        if(j > high) {
            while(j - high >= high) j -= high;
            return low + (j - (high + 1));
        }
        return i;
    }

    /**
     * Returns a number indicating the sign (+/-) of a number, as either -1, 0, or 1.
     * @param i input number
     * @return sign of number
     */
    public static int sign(int i) {
        return clamp(i, -1, 1);
    }

    /**
     * Returns a number indicating the sign (+/-) of a number, as either -1, 0, or 1.
     * @param i input number
     * @return sign of number
     */
    public static long sign(long i) {
        return clamp(i, -1, 1);
    }

    /**
     * Returns a number indicating the sign (+/-) of a number, as either -1, 0, or 1.
     * @param i input number
     * @return sign of number
     */
    public static double sign(double i) {
        return clamp(i < 0 ? Math.floor(i) : Math.ceil(i), -1, 1);
    }

    /**
     * Returns a number indicating the sign (+/-) of a number, as either -1, 0, or 1.
     * @param i input number
     * @return sign of number
     */
    public static float sign(float i) {
        return clamp(i < 0 ? (float)Math.floor(i) : (float)Math.ceil(i), -1, 1);
    }
}
