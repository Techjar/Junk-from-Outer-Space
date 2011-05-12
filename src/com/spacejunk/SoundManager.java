/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import com.spacejunk.util.MathHelper;

/**
 * 
 * @author Techjar
 */
public class SoundManager {
    private Audio nullMusic, randMusic;
    private Map<String, Audio> soundEffects;
    private Random random = new Random();


    public SoundManager() {
        try {
            nullMusic = AudioLoader.getAudio("WAV", new FileInputStream("resources/null.wav"));
            soundEffects = new HashMap<String, Audio>();
            soundEffects.put("ship.gunfire", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/gunfire.wav")));
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void poll() {
        SoundStore.get().poll(0);
    }

    public void stopMusic() {
        nullMusic.playAsMusic(1, 1, false);
    }

    public void playMusic(int track) {
        try {
            randMusic = AudioLoader.getStreamingAudio("OGG", new File("resources/music/" + track + ".ogg").toURI().toURL());
            randMusic.playAsMusic(1, 1, true);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void playRandomMusic() {
        try {
            randMusic = AudioLoader.getStreamingAudio("OGG", new File("resources/music/" + random.nextInt(14) + ".ogg").toURI().toURL());
            randMusic.playAsMusic(1, 1, true);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playSoundEffect(String name) {
        Audio sound = soundEffects.get(name);
        if(sound != null) sound.playAsSoundEffect(1, 1, false);
    }

    public void setMusicVolume(float volume) {
        SoundStore.get().setMusicVolume(new MathHelper().clamp(volume, 0, 1));
    }

    public void setSoundVolume(float volume) {
        SoundStore.get().setSoundVolume(new MathHelper().clamp(volume, 0, 1));
    }

    public float getMusicVolume() {
        return SoundStore.get().getMusicVolume();
    }

    public float getSoundVolume() {
        return SoundStore.get().getSoundVolume();
    }
}
