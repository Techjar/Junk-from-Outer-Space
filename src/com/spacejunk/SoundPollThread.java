/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk;

/**
 * 
 * @author Techjar
 */
public class SoundPollThread extends Thread {
    private SoundManager sm;


    public SoundPollThread(SoundManager sm) {
        this.sm = sm;
    }

    @Override
    public void run() {
        while(true) {
            sm.poll(20);
            try {
                Thread.sleep(20);
            }
            catch(InterruptedException e) {
            }
        }
    }
}
