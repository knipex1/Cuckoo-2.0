package com.cuckoo2;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
	public static final String START_SOUND = "com/cuckoo2/swing/icons/start_sound.wav";
	public static final String SHUTDOWN_SOUND = "com/cuckoo2/swing/icons/shutdown_sound.wav";

	public static void playSound(String sound) {
		try {
			// Open an audio input stream.
			URL url = SoundPlayer.class.getClassLoader().getResource(sound);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
