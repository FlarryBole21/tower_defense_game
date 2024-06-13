package entities.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.Beings;
import entities.Lizard;
import ui.GamePanel;

public class LizardSpawner extends Spawner{
	
	private LizardSpawner spawner;
	private boolean friendly;
	private int basicSpawnerLimit;
	private int totalBeingSize;

	public LizardSpawner(long delay,GamePanel panel,boolean friendly) {
		super(delay,panel);
		spawner = this;
		this.friendly=friendly;
		basicSpawnerLimit=8;
		totalBeingSize=15;
	}
	
	@Override
	public void startSpawning() {
        super.getTimer().schedule(new SpawnTask(), super.getDelay());
    }
	
	
	
	
	public boolean isFriendly() {
		return friendly;
	}


	private class SpawnTask extends TimerTask {

        @Override
        public void run() {
        	
        	int friendlySize = spawner.getPanel().getFriendlyLivingBeings().size();
        	int enemySize = spawner.getPanel().getEnemyLivingBeings().size();
    		//int baseWidth = spawner.getPanel().getBaseWidth();
        	
        	if(friendly) {
        		
        		if(friendlySize <= basicSpawnerLimit+Math.abs(enemySize-friendlySize)) {
        			
        			
        			if(spawner.getPanel().getFriendlyLivingBeings().size() > 0) {
        				
        				if(Beings.FRIENDLY_LIZARD.getxPos()+100 
        						<= spawner.getPanel().getFriendlyLivingBeings()
        						.get(spawner.getPanel().getFriendlyLivingBeings().size()-1).getRect().getX()) {

        					spawnFriend();
        				}

        			}else {

        				spawnFriend();
        			}

            	}
	
        	}else {
        		
        		if(enemySize <= basicSpawnerLimit+Math.abs(enemySize-friendlySize)) {
        			
    			if(spawner.getPanel().getEnemyLivingBeings().size() > 0) {
        				if(Beings.ENEMY_LIZARD.getxPos()-100 >= spawner.getPanel().getEnemyLivingBeings()
        						.get(spawner.getPanel().getEnemyLivingBeings().size()-1).getRect().getX()) {

        					spawnEnemy();
        				}
        			}else {
        				spawnEnemy();

        			}  
            	}
        	}

            startSpawning();
        	
        }
    }
	
	
	private void spawnFriend() {
		Lizard newLizard = new Lizard(Beings.FRIENDLY_LIZARD.getxPos(),Beings.FRIENDLY_LIZARD.getyPos(),
    			Beings.FRIENDLY_LIZARD.getWidth(),Beings.FRIENDLY_LIZARD.getHeigth(),Beings.FRIENDLY_LIZARD.getAttack(),
    			Beings.FRIENDLY_LIZARD.getHealth(),Beings.FRIENDLY_LIZARD.isFriendly());
        newLizard.resetState();
        spawner.getPanel().getFriendlyNewBeings().add(newLizard);
	}
	
	
	private void spawnEnemy() {
		
		Lizard newLizard2 = new Lizard(Beings.ENEMY_LIZARD.getxPos(),Beings.ENEMY_LIZARD.getyPos(),
        		Beings.ENEMY_LIZARD.getWidth(),Beings.ENEMY_LIZARD.getHeigth(),Beings.ENEMY_LIZARD.getAttack(),
        		Beings.ENEMY_LIZARD.getHealth(),Beings.ENEMY_LIZARD.isFriendly());
        newLizard2.resetState();
        spawner.getPanel().getEnemyNewBeings().add(newLizard2);
		
	}

}
