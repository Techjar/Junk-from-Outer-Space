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
            soundEffects.put("ui.button.click", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ui/button/click.wav")));
            soundEffects.put("ui.button.clickrelease", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ui/button/clickrelease.wav")));
            soundEffects.put("ui.button.rollover", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ui/button/rollover.wav")));
            soundEffects.put("ship.gunfire", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ship/gunfire.wav")));
            soundEffects.put("ship.powerup", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ship/powerup.wav")));
            soundEffects.put("ambient.explode.0", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ambient/explode/0.wav")));
            soundEffects.put("ambient.explode.1", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ambient/explode/1.wav")));
            soundEffects.put("ambient.explode.2", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/ambient/explode/2.wav")));
            soundEffects.put("weapon.rocket", AudioLoader.getAudio("WAV", new FileInputStream("resources/sounds/weapon/rocket.wav")));
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void poll(int delta) {
        SoundStore.get().poll(delta);
    }

    public void stopMusic() {
        nullMusic.playAsMusic(1, 1, false);
    }

    public void playMusic(int track, boolean loop) {
        try {
            randMusic = AudioLoader.getStreamingAudio("OGG", new File("resources/music/" + track + ".ogg").toURI().toURL());
            randMusic.playAsMusic(1, 1, loop);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic(String track, boolean loop) {
        try {
            randMusic = AudioLoader.getStreamingAudio("OGG", new File("resources/music/" + track + ".ogg").toURI().toURL());
            randMusic.playAsMusic(1, 1, loop);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void playRandomMusic() {
        try {
            randMusic = AudioLoader.getStreamingAudio("OGG", new File("resources/music/" + random.nextInt(20) + ".ogg").toURI().toURL());
            randMusic.playAsMusic(1, 1, false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int playSoundEffect(String name, boolean loop) {
        Audio sound = soundEffects.get(name);
        if(sound != null) return sound.playAsSoundEffect(1, 1, loop);
        return -1;
    }

    public void stopSoundEffect(int id) {
        SoundStore.get().stopSoundEffect(id);
    }

    public void setMusicVolume(float volume) {
        SoundStore.get().setMusicVolume(MathHelper.clamp(volume, 0, 1));
    }

    public void setSoundVolume(float volume) {
        SoundStore.get().setSoundVolume(MathHelper.clamp(volume, 0, 1));
    }

    public float getMusicVolume() {
        return SoundStore.get().getMusicVolume();
    }

    public float getSoundVolume() {
        return SoundStore.get().getSoundVolume();
    }

    public Audio getRandMusic() {
        return randMusic;
    }
}
