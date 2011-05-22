/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk;

import com.spacejunk.util.ConfigManager;

/**
 * 
 * @author Techjar
 */
public class ShutdownThread extends Thread {
    private SpaceJunk sj;


    public ShutdownThread(SpaceJunk sj) {
        this.sj = sj;
    }

    @Override
    public void run() {
        ConfigManager.setProperty("high-score", sj.getHighScore());
    }
}
