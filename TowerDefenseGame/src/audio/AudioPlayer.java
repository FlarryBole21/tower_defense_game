package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	
	public void play() {
		try {
	       
	        File audioFile = new File("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
	    			+ "tower_defense_game/TowerDefenseGame/audio/GloriousMorning2.wav");
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	        Clip audioClip = AudioSystem.getClip();
	        audioClip.open(audioStream);
	        audioClip.start();

	        while (!audioClip.isRunning()) {
	            Thread.sleep(10);
	        }
	        while (audioClip.isRunning()) {
	            Thread.sleep(10);
	        }

	        audioClip.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}
}

