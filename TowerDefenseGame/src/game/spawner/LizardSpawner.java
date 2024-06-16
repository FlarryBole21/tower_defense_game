package game.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.Lizard;
import entities.livingbeings.NormalLizard;
import ui.GamePanel;

public class LizardSpawner extends Spawner{
	
	private LizardSpawner spawner;
	private boolean friendly;
	private int basicSpawnerLimit;
	private int friendlyCount;
	private int enemyCount;
	private Beings normalFriend;
	private Beings normalEnemy;
	private Beings intermediateFriend;
	private Beings intermediateEnemy;
	
	{
		normalFriend=Beings.FRIENDLY_NORMAL_LIZARD;
		normalEnemy=Beings.ENEMY_NORMAL_LIZARD;
		intermediateFriend=Beings.FRIENDLY_INTERMEDIATE_LIZARD;
		intermediateEnemy=Beings.ENEMY_INTERMEDIATE_LIZARD;
		
	}

	public LizardSpawner(long delay,GamePanel panel,boolean friendly) {
		super(delay,panel);
		spawner = this;
		this.friendly=friendly;
		basicSpawnerLimit=8;
	}
	
	@Override
	public void startSpawning() {
        super.getTimer().schedule(new SpawnTask(), super.getDelay());
    }
	
	@Override
	public void stopSpawning() {
		getTimer().cancel();
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
        				
        				if(normalFriend.getxPos()+normalFriend.getWaitingDistance() 
        						<= spawner.getPanel().getFriendlyLivingBeings()
        						.get(spawner.getPanel().getFriendlyLivingBeings().size()-1).getRect().getX()
        						&& intermediateFriend.getxPos()+intermediateFriend.getWaitingDistance()
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
        				if(normalEnemy.getxPos()-normalEnemy.getWaitingDistance() >= spawner.getPanel().getEnemyLivingBeings()
        						.get(spawner.getPanel().getEnemyLivingBeings().size()-1).getRect().getX() &&
        						intermediateEnemy.getxPos()-intermediateEnemy.getWaitingDistance() >= spawner.getPanel().getEnemyLivingBeings()
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
		friendlyCount++;
		
		if(spawner.getPanel().getWaveManager().getWave() == 2 && friendlyCount % 5 == 0) {
			spawnIntermediateFriend();
		}else if(spawner.getPanel().getWaveManager().getWave() > 2) {
			spawnIntermediateFriend();
		}
		else {
			spawnNormalFriend();
		}
		
	}
	
	
	private void spawnEnemy() {
		enemyCount++;
		
		if(spawner.getPanel().getWaveManager().getWave() == 2 && enemyCount % 5 == 0) {
			spawnIntermediateEnemy();
		}else if(spawner.getPanel().getWaveManager().getWave() > 2) {
			spawnIntermediateEnemy();
		}
		else {
			spawnNormalEnemy();
		}
		
	}
	
	
	private void spawnNormalFriend() {
		Lizard newLizard = new NormalLizard(normalFriend.getxPos(),normalFriend.getyPos(),
				normalFriend.getWidth(),normalFriend.getHeigth(),normalFriend.getAttack(),
				normalFriend.getHealth(),normalFriend.isFriendly());
        newLizard.resetState(normalFriend);
        spawner.getPanel().getFriendlyNewBeings().add(newLizard);
	}
	
	
	private void spawnNormalEnemy() {
		
		Lizard newLizard = new NormalLizard(normalEnemy.getxPos(),normalEnemy.getyPos(),
				normalEnemy.getWidth(),normalEnemy.getHeigth(),normalEnemy.getAttack(),
        		normalEnemy.getHealth(),normalEnemy.isFriendly());
        newLizard.resetState(normalEnemy);
        spawner.getPanel().getEnemyNewBeings().add(newLizard);
		
	}
	
	
	private void spawnIntermediateFriend() {
	    Lizard newLizard = new IntermediateLizard(intermediateFriend.getxPos(), intermediateFriend.getyPos(),
	    		intermediateFriend.getWidth(), intermediateFriend.getHeigth(), intermediateFriend.getAttack(),
	    		intermediateFriend.getHealth(), intermediateFriend.isFriendly());
	    newLizard.resetState(intermediateFriend);
	    spawner.getPanel().getFriendlyNewBeings().add(newLizard);
	}

	private void spawnIntermediateEnemy() {
	    Lizard newLizard = new IntermediateLizard(intermediateEnemy.getxPos(), intermediateEnemy.getyPos(),
	    		intermediateEnemy.getWidth(), intermediateEnemy.getHeigth(), intermediateEnemy.getAttack(),
	    		intermediateEnemy.getHealth(), intermediateEnemy.isFriendly());
	    newLizard.resetState(intermediateEnemy);
	    spawner.getPanel().getEnemyNewBeings().add(newLizard);
	}


}
