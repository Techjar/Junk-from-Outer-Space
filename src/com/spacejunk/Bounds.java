/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk;

/**
 * 
 * @author Techjar
 */
public class Bounds {
    private int b1, b2, b3, b4;


    public Bounds(int b1, int b2, int b3, int b4) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
    }

    public boolean intersect(Bounds b) {
        if(b1 >= b.getB1() && b2 >= b.getB2() && b3 <= b.getB3() && b4 <= b.getB4()) return true;
        return false;
    }

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        this.b1 = b1;
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        this.b2 = b2;
    }

    public int getB3() {
        return b3;
    }

    public void setB3(int b3) {
        this.b3 = b3;
    }

    public int getB4() {
        return b4;
    }

    public void setB4(int b4) {
        this.b4 = b4;
    }
}
