package entities.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.Beings;
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
        	
        
    		//int baseWidth = spawner.getPanel().getBaseWidth();
        	
        	Lizard newLizard = new Lizard(Beings.FRIENDLY_LIZARD.getxPos(),Beings.FRIENDLY_LIZARD.getyPos(),
        			Beings.FRIENDLY_LIZARD.getWidth(),Beings.FRIENDLY_LIZARD.getHeigth(),Beings.FRIENDLY_LIZARD.getAttack(),
        			Beings.FRIENDLY_LIZARD.getHealth(),Beings.FRIENDLY_LIZARD.isFriendly());
            newLizard.resetState();
            spawner.getPanel().getNewBeings().add(newLizard);
            Lizard newLizard2 = new Lizard(Beings.ENEMY_LIZARD.getxPos(),Beings.ENEMY_LIZARD.getyPos(),
            		Beings.ENEMY_LIZARD.getWidth(),Beings.ENEMY_LIZARD.getHeigth(),Beings.ENEMY_LIZARD.getAttack(),
            		Beings.ENEMY_LIZARD.getHealth(),Beings.ENEMY_LIZARD.isFriendly());
            newLizard2.resetState();
            spawner.getPanel().getNewBeings().add(newLizard2);
        
            startSpawning();
        }
    }

}
