/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Asteroids main class.
 * @author Techjar
 */
public class Main {
    /**
     * Asteroids main method.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            LauncherGUI launcher = new LauncherGUI("Junk from Outer Space Launcher");
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /*
    private void startGame() {
        Asteroids ast = null;
        try {
            ast = new Asteroids(mode, fullscreen, vsync);
            ast.create();
            ast.run();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(ast != null) ast.destroy();
        }
    }
    */
}
