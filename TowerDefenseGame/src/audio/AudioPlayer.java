package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	private Clip audioClip;
	
	public void play(String fileName, boolean loop) {
		try {
	       
	        File audioFile = new File("audio/GloriousMorning2.wav");
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	        audioClip = AudioSystem.getClip();
	        audioClip.open(audioStream);
	        audioClip.start();
	        if(loop) {
	        	audioClip.loop(Clip.LOOP_CONTINUOUSLY);
	        }
	        

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}
	
	
	
	public void stop() {
        if (audioClip != null && audioClip.isRunning()) {
        	audioClip.stop();
        	audioClip.close();
        }
    }
}

