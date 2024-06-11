package entities.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.Lizard;
import ui.GamePanel;

public class LizardSpawner extends Spawner{
	
	private LizardSpawner spawner;

	public LizardSpawner(long delay,GamePanel panel) {
		super(delay,panel);
		spawner = this;
	}
	
	@Override
	public void startSpawning() {
        super.getTimer().schedule(new SpawnTask(), super.getDelay());
    }
	
	
	private class SpawnTask extends TimerTask {

        @Override
        public void run() {
        	
        	Dimension screenSize = spawner.getPanel().getScreenSize();
    		int baseWidth = spawner.getPanel().getBaseWidth();
        	
        	Lizard newLizard = new Lizard(100, 550, 100, 100, 10, 100, true);
            newLizard.resetState();
            spawner.getPanel().getNewBeings().add(newLizard);
            Lizard newLizard2 = new Lizard(screenSize.width-baseWidth-50, 550, 100, 100, 10, 100, false);
            newLizard2.resetState();
            spawner.getPanel().getNewBeings().add(newLizard2);
        
            startSpawning();
        }
    }

}
