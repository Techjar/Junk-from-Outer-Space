/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import com.spacejunk.util.ResolutionSorter;
import com.spacejunk.util.ConfigManager;

/**
 * 
 * @author Techjar
 */
public class LauncherGUI extends JFrame {
    private boolean renderColl;
    private JComboBox resMenu, diffMenu;
    private JCheckBox fullscreen, vSync;
    private DisplayMode[] displayModes = Display.getAvailableDisplayModes();
    private List<DisplayMode> modeList = new ArrayList<DisplayMode>();


    public LauncherGUI(String title, boolean renderColl) throws LWJGLException {
        super(title);
        this.renderColl = renderColl;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(Box.createVerticalStrut(5));
        for (int i = 0; i < displayModes.length; i++) {
            DisplayMode cur = displayModes[i]; DisplayMode def = Display.getDesktopDisplayMode();
            if(cur.getBitsPerPixel() == def.getBitsPerPixel() && cur.getFrequency() == def.getFrequency()) modeList.add(cur);
        }
        Collections.sort(modeList, new ResolutionSorter());
        String[] modeNames = new String[modeList.size()]; int defMode = 0;
        for(int i = 0; i < modeList.size(); i++) {
            DisplayMode cur = modeList.get(i);
            if(cur.getWidth() * cur.getHeight() == Integer.parseInt(ConfigManager.getProperty("video-width").toString()) * Integer.parseInt(ConfigManager.getProperty("video-height").toString())) defMode = i;
            modeNames[i] = cur.getWidth() + "x" + cur.getHeight();
        }
        String[] diffModes = {"Easy", "Normal", "Hard", "Expert", "Oh God..."};
        Box box;

        
        box = Box.createHorizontalBox();
            box.add(new JLabel("Difficulty:"));
            box.add(Box.createHorizontalStrut(5));
            diffMenu = new JComboBox(diffModes);
            diffMenu.setSelectedIndex((Integer)ConfigManager.getProperty("difficulty"));
            diffMenu.addActionListener(new DiffMenuListener());
            box.add(diffMenu);
            box.add(Box.createHorizontalStrut(10));
            box.add(new JLabel("Resolution:"));
            box.add(Box.createHorizontalStrut(5));
            resMenu = new JComboBox(modeNames);
            resMenu.addActionListener(new ResMenuListener());
            resMenu.setSelectedIndex(defMode);
            box.add(resMenu);
        mainPanel.add(box);
        mainPanel.add(Box.createVerticalStrut(5));
        box = Box.createHorizontalBox();
            fullscreen = new JCheckBox("Fullscreen");
            fullscreen.addActionListener(new FullscreenListener());
            fullscreen.setMnemonic(KeyEvent.VK_F);
            fullscreen.setSelected(Boolean.parseBoolean(ConfigManager.getProperty("fullscreen").toString()));
            box.add(fullscreen);
            box.add(Box.createHorizontalStrut(10));
            vSync = new JCheckBox("Vertical Sync");
            vSync.addActionListener(new VSyncListener());
            vSync.setMnemonic(KeyEvent.VK_V);
            vSync.setSelected(Boolean.parseBoolean(ConfigManager.getProperty("vertical-sync").toString()));
            box.add(vSync);
        mainPanel.add(box);
        mainPanel.add(Box.createVerticalStrut(5));
        box = Box.createHorizontalBox();
            JButton button = new JButton("Start Game");
            button.addActionListener(new StartGameListener());
            box.add(button);
        mainPanel.add(box);


        getContentPane().add(mainPanel);
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);
        setResizable(false);
        setVisible(true);
    }

    private class StartGameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            SpaceJunk sj = null;
            try {
                setVisible(false);
                sj = new SpaceJunk(getDifficulty(diffMenu.getSelectedIndex()), modeList.get(resMenu.getSelectedIndex()), fullscreen.isSelected(), vSync.isSelected(), Float.parseFloat(ConfigManager.getProperty("music-volume").toString()), Float.parseFloat(ConfigManager.getProperty("sound-volume").toString()), renderColl);
                sj.create();
                sj.run();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                if(sj != null) sj.destroy();
                System.exit(0);
            }
        }

        private int getDifficulty(int diff) {
            switch(diff) {
                case 0: return 1;
                case 1: return 4;
                case 2: return 7;
                case 3: return 10;
                case 4: return 100;
                default: return 1;
            }
        }
    }

    private class DiffMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ConfigManager.setProperty("difficulty", diffMenu.getSelectedIndex());
        }
    }

    private class ResMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ConfigManager.setProperty("video-width", modeList.get(resMenu.getSelectedIndex()).getWidth());
            ConfigManager.setProperty("video-height", modeList.get(resMenu.getSelectedIndex()).getHeight());
        }
    }

    private class FullscreenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ConfigManager.setProperty("fullscreen", fullscreen.isSelected());
        }
    }

    private class VSyncListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ConfigManager.setProperty("vertical-sync", vSync.isSelected());
        }
    }
}
