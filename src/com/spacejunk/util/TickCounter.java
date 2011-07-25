/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.util;

/**
 * A tick counting class for timing purposes. Better than using system time, as you can always know what the value will be.
 * Also isn't affected by system time changes. You should call incTicks() as many times per second as the tick rate is set to, for example:
 * if your tick rate is 60, called incTicks() 60 times per second.
 * @author Techjar
 */
public final class TickCounter {
    private int tickRate;
    private long ticks;


    /**
     * Constructs a new TickCounter with the default tick rate of 60.
     */
    public TickCounter() {
        this.ticks = 0;
        this.tickRate = 60;
    }

    /**
     * Constructs a new TickCounter with the specified tick rate.
     * @param tickRate number of ticks per second
     */
    public TickCounter(int tickRate) {
        this.ticks = 0;
        this.tickRate = tickRate;
    }
    
    /**
     * Returns the tick rate for this TickCounter.
     * @return the tick rate
     */
    public final int getTickRate() {
        return this.tickRate;
    }

    /**
     * Sets the tick rate for this TickCounter.
     * @param tickRate new tick rate
     */
    public final void setTickRate(int tickRate) {
        this.tickRate = tickRate;
    }

    /**
     * Increments the ticks for this TickCounter.
     */
    public final void incTicks() {
        this.ticks++;
    }

    /**
     * Increments the ticks for this TickCounter by the specified amount.
     * @param ticks number of ticks to increment by
     */
    public final void incTicks(long ticks) {
        this.ticks += ticks;
    }

    /**
     * Explicitly sets the number of ticks for this TickCounter.
     * @param ticks number of ticks
     */
    public final void setTicks(long ticks) {
        this.ticks = ticks;
    }

    /**
     * Returns the current number of ticks for this TickCounter.
     * @return number of ticks
     */
    public final long getTicks() {
        return this.ticks;
    }

    /**
     * Returns the current milliseconds for this TickCounter based on the tick rate.
     * @return milliseconds passed
     */
    public final long getTickMillis() {
        return Math.round(((double)this.ticks / (double)this.tickRate) * 1000D);
    }

    /**
     * Returns the current microseconds for this TickCounter based on the tick rate.
     * @return microseconds passed
     */
    public final long getTickMicros() {
        return Math.round(((double)this.ticks / (double)this.tickRate) * 1000000D);
    }

    /**
     * Returns the current nanoseconds for this TickCounter based on the tick rate.
     * @return nanoseconds passed
     */
    public final long getTickNanos() {
        return Math.round(((double)this.ticks / (double)this.tickRate) * 1000000000D);
    }
}
