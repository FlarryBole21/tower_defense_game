package entities.spawner;

import java.util.Timer;
import java.util.TimerTask;

import entities.LivingBeing;
import ui.GamePanel;

public abstract class Spawner {
	
	private GamePanel panel;
	private Timer timer;
    private long delay;

    public Spawner(long delay,GamePanel panel) {
        this.delay = delay;
        this.panel=panel;
        this.timer = new Timer();
    }
    
  
    public GamePanel getPanel() {
		return panel;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public abstract void startSpawning();

 
}
