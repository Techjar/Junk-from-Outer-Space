/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

import org.newdawn.slick.geom.Vector2f;

/**
 * 
 * @author Techjar
 */
public final class PolygonHitbox {
    // Generic
    public static final float[] DEFAULT = {0, 0, 5, 0, 5, 5, 0, 5};

    // Various sprites
    public static final float[] SHIP = {25, 5, 20, 8, 16, 7, 16, 10, 19, 10, 21, 15, 19, 21, 16, 21, 16, 24, 19, 23, 25, 26, 20, 29, 11, 29, 6, 27, 4, 22, 7, 16, 4, 9, 6, 4, 11, 2, 20, 2};

    // Asteroids
    public static final float[] ASTEROID_0 = {20, 6, 28, 2, 35, 7, 47, 7, 54, 16, 54, 28, 53, 31, 56, 35, 53, 49, 41, 53, 38, 62, 33, 60, 24, 61, 22, 51, 19, 46, 12, 49, 6, 43, 10, 37, 11, 34, 15, 31, 17, 22, 20, 19};
    public static final float[] ASTEROID_1 = {32, 1, 38, 1, 44, 15, 53, 17, 55, 19, 55, 27, 52, 32, 52, 36, 48, 48, 36, 62, 27, 56, 24, 51, 21, 50, 14, 41, 7, 36, 8, 27, 7, 23, 20, 12, 24, 13};
    public static final float[] ASTEROID_2 = {40, 5, 49, 12, 50, 24, 61, 31, 53, 43, 46, 44, 42, 55, 26, 54, 23, 57, 10, 51, 1, 41, 6, 31, 4, 22, 21, 10, 35, 9};
    public static final float[] ASTEROID_3 = {15, 3, 23, 10, 35, 9, 39, 4, 44, 4, 57, 23, 61, 24, 56, 40, 52, 42, 46, 59, 34, 55, 22, 59, 12, 47, 15, 39, 14, 37, 14, 24, 7, 19, 2, 10, 8, 4};
    public static final float[] ASTEROID_4 = {44, 7, 51, 22, 55, 22, 62, 34, 49, 41, 44, 40, 36, 49, 29, 52, 24, 57, 18, 56, 14, 38, 4, 36, 2, 26, 3, 23, 2, 12, 5, 8, 17, 8, 24, 14, 31, 14, 37, 8};
    public static final float[] ASTEROID_5 = {63, 2, 75, 3, 77, 17, 102, 35, 104, 43, 117, 50, 114, 79, 109, 85, 107, 97, 104, 101, 73, 105, 66, 120, 57, 126, 47, 121, 26, 116, 22, 90, 16, 86, 8, 68, 25, 41, 25, 38, 22, 33, 23, 16, 51, 11};
    public static final float[] ASTEROID_6 = {68, 8, 99, 15, 101, 20, 120, 31, 125, 48, 119, 85, 119, 94, 96, 105, 87, 119, 72, 117, 48, 104, 53, 89, 38, 74, 16, 81, 3, 68, 3, 62, 33, 39, 34, 34, 60, 21, 64, 12};
    public static final float[] ASTEROID_7 = {79, 8, 131, 7, 148, 37, 205, 41, 226, 77, 249, 95, 248, 138, 254, 150, 244, 181, 197, 203, 179, 222, 129, 213, 119, 208, 88, 217, 57, 247, 8, 181, 18, 156, 8, 141, 1, 122, 44, 72, 50, 72};
    public static final float[][] ASTEROIDS = {ASTEROID_0, ASTEROID_1, ASTEROID_2, ASTEROID_3, ASTEROID_4, ASTEROID_5, ASTEROID_6, ASTEROID_7};
    public static final Vector2f[] ASTEROID_OFFSETS = {new Vector2f(-1, 3), new Vector2f(1, -1), new Vector2f(-1, 1), new Vector2f(-2, -5), new Vector2f(-4, -4), new Vector2f(-1, 1), new Vector2f(4, -3), new Vector2f(0, 5)};


    // Useless constructor
    private PolygonHitbox() {
    }
}
