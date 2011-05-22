/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

/**
 * 
 * @author Techjar
 */
public final class TickCounter {
    private int tickRate;
    private long ticks;


    public TickCounter(int tickRate) {
        this.ticks = 0;
        this.tickRate = tickRate;
    }
    
    public final int getTickRate() {
        return this.tickRate;
    }

    public final void setTickRate(int tickRate) {
        this.tickRate = tickRate;
    }

    public final void incTicks() {
        this.ticks++;
    }

    public final void incTicks(long ticks) {
        this.ticks += ticks;
    }

    public final void setTicks(long ticks) {
        this.ticks = ticks;
    }

    public final long getTicks() {
        return this.ticks;
    }

    public final long getTickMillis() {
        return Math.round(((double)this.ticks / (double)this.tickRate) * 1000D);
    }

    public final long getTickMicros() {
        return Math.round(((double)this.ticks / (double)this.tickRate) * 1000000D);
    }

    public final long getTickNanos() {
        return Math.round(((double)this.ticks / (double)this.tickRate) * 1000000000D);
    }
}
