package game.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.livingbeings.AdvancedLizard;
import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.Lizard;
import entities.livingbeings.NormalLizard;
import game.GamePanel;

public class FirstLineUpSpawner extends Spawner{
	
	private FirstLineUpSpawner spawner;
	private int basicSpawnerLimit;
	private int enemyCount;
	private Beings normalEnemy;
	private Beings intermediateEnemy;
	private Beings advancedEnemy;
	
	{
		normalEnemy=Beings.ENEMY_NORMAL_LIZARD;
		intermediateEnemy=Beings.ENEMY_INTERMEDIATE_LIZARD;
		advancedEnemy=Beings.ENEMY_ADVANCED_LIZARD;
		
		
	}

	public FirstLineUpSpawner(long delay,GamePanel panel,boolean friendly) {
		super(delay,panel);
		spawner = this;
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
        	
        	
        		
    		if(enemySize <= basicSpawnerLimit+Math.abs(enemySize-friendlySize)) {
    			
			if(spawner.getPanel().getEnemyLivingBeings().size() > 0) {
				if (normalEnemy.getxPos() - normalEnemy.getWaitingDistance() >= spawner.getPanel().getEnemyLivingBeings()
				        .get(spawner.getPanel().getEnemyLivingBeings().size() - 1).getRect().getX() &&
				    intermediateEnemy.getxPos() - intermediateEnemy.getWaitingDistance() >= spawner.getPanel().getEnemyLivingBeings()
				        .get(spawner.getPanel().getEnemyLivingBeings().size() - 1).getRect().getX() &&
				    advancedEnemy.getxPos() - advancedEnemy.getWaitingDistance() >= spawner.getPanel().getEnemyLivingBeings()
				        .get(spawner.getPanel().getEnemyLivingBeings().size() - 1).getRect().getX()){

    					spawnEnemy();
    				}
    			}else {
    				spawnEnemy();
    			}  
        	}
        	

            startSpawning();
        	
        }
    }
	

	
	private void spawnEnemy() {
		enemyCount++;
		
		if(spawner.getPanel().getWaveManager().getWave() == 2 && enemyCount % 5 == 0) {
			spawnIntermediateEnemy();
		}else if(spawner.getPanel().getWaveManager().getWave() > 2 && enemyCount % 5 == 0) {
			spawnAdvancedEnemy();
		}else if(spawner.getPanel().getWaveManager().getWave() > 2) {
			spawnIntermediateEnemy();
		}
		else {
			spawnNormalEnemy();
		}
		
	}
	
	
	
	
	private void spawnNormalEnemy() {
		
		Lizard newLizard = new NormalLizard(normalEnemy.getxPos(),normalEnemy.getyPos(),
				normalEnemy.getWidth(),normalEnemy.getHeigth(),normalEnemy.getAttack(),
        		normalEnemy.getHealth(),normalEnemy.isFriendly());
        newLizard.resetState(normalEnemy);
        spawner.getPanel().getEnemyNewBeings().add(newLizard);
		
	}
	

	private void spawnIntermediateEnemy() {
	    Lizard newLizard = new IntermediateLizard(intermediateEnemy.getxPos(), intermediateEnemy.getyPos(),
	    		intermediateEnemy.getWidth(), intermediateEnemy.getHeigth(), intermediateEnemy.getAttack(),
	    		intermediateEnemy.getHealth(), intermediateEnemy.isFriendly());
	    newLizard.resetState(intermediateEnemy);
	    spawner.getPanel().getEnemyNewBeings().add(newLizard);
	}
	
	
	
	private void spawnAdvancedEnemy() {
	    Lizard newLizard = new AdvancedLizard(advancedEnemy.getxPos(), advancedEnemy.getyPos(),
	            advancedEnemy.getWidth(), advancedEnemy.getHeigth(), advancedEnemy.getAttack(),
	            advancedEnemy.getHealth(), advancedEnemy.isFriendly());
	    newLizard.resetState(advancedEnemy);
	    spawner.getPanel().getEnemyNewBeings().add(newLizard);
	}



}
