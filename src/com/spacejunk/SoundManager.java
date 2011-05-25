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
    private int tracks;
    private Audio nullMusic, randMusic;
    private Map<String, Audio> soundEffects;
    private Random random = new Random();


    public SoundManager() {
        try {
            for(this.tracks = 0; new File("resources/music/" + this.tracks + ".ogg").exists(); this.tracks++){}
            nullMusic = AudioLoader.getAudio("WAV", new FileInputStream("resources/null.wav"));
            this.initSoundList();
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

    public void playMusic(Object track) {
        this.playMusic(track, false, 1, 1);
    }

    public void playMusic(Object track, boolean loop) {
        this.playMusic(track, loop, 1, 1);
    }

    public void playMusic(Object track, boolean loop, float pitch) {
        this.playMusic(track, loop, pitch, 1);
    }

    public void playMusic(Object track, boolean loop, float pitch, float gain) {
        try {
            randMusic = AudioLoader.getStreamingAudio("OGG", new File("resources/music/" + track + ".ogg").toURI().toURL());
            randMusic.playAsMusic(pitch, gain, loop);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void playRandomMusic() {
        this.playMusic(random.nextInt(this.tracks));
    }

    public int playSoundEffect(String name) {
        return this.playSoundEffect(name, false, 1, 1);
    }

    public int playSoundEffect(String name, boolean loop) {
        return this.playSoundEffect(name, loop, 1, 1);
    }

    public int playSoundEffect(String name, boolean loop, float pitch) {
        return this.playSoundEffect(name, loop, pitch, 1);
    }

    public int playSoundEffect(String name, boolean loop, float pitch, float gain) {
        Audio sound = soundEffects.get(name);
        if(sound != null) return sound.playAsSoundEffect(pitch, gain, loop);
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
        return this.randMusic;
    }

    private void initSoundList() throws java.io.FileNotFoundException, java.io.IOException {
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
}
