package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	private Clip audioClip;
	private boolean loop;
	private boolean play;
	private boolean continueAfterStop;
	
	
	public AudioPlayer(String fileName,boolean play,boolean loop,boolean continueAfterStop) {
		this.play=play;
		this.loop=loop;
		this.continueAfterStop=continueAfterStop;
        try {
	        File audioFile = new File(fileName);
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	        audioClip = AudioSystem.getClip();
	        audioClip.open(audioStream);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public Clip getAudioClip() {
		return audioClip;
	}
	
	
	public boolean isLoop() {
		return loop;
	}

	public boolean isPlay() {
		return play;
	}
	
	public void resetFrame() {
		if(audioClip != null) {
			audioClip.setFramePosition(0); 
		}
	}

	public void setPlay(boolean play) {
		this.play = play;
		
		if (play && !audioClip.isRunning()) {
			
			if(!continueAfterStop) {
				audioClip.setFramePosition(0); 
			}
            audioClip.start();

			if(loop) {
				audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			}

        }else {
        	stop();
        }
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
		
		if (audioClip != null && audioClip.isRunning()) {
            if (loop) {
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                audioClip.loop(0);
            }
        }
	}

	public void play() {
		
		if(audioClip != null) {
			
			try {

		        audioClip.start();
		        if(loop) {
		        	audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		        }else {
		        	audioClip.loop(0);
		        }
		        

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			
		}
	
	}

	public void stop() {
		play=false;
        if (audioClip != null && audioClip.isRunning()) {
        	audioClip.stop();
        	if(!continueAfterStop) {
				audioClip.setFramePosition(0); 
			}
        }
    }
}

