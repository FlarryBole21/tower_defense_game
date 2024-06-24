package game.spawner;

import java.awt.Dimension;


import java.util.TimerTask;
import entities.livingbeings.AdvancedLizard;
import entities.livingbeings.Spider;
import entities.livingbeings.Bear;
import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.Lizard;
import entities.livingbeings.NormalSpider;
import entities.livingbeings.NormalBear;
import entities.livingbeings.NormalLizard;
import game.GamePanel;

public class SecondLineUpSpawner extends Spawner{
	
	private SecondLineUpSpawner spawner;
	private int basicSpawnerLimit;
	private int enemyCount;
	private Beings normalEnemy;
	private Beings intermediateEnemy;
	
	{
		normalEnemy=Beings.ENEMY_NORMAL_SPIDER;
		intermediateEnemy=Beings.ENEMY_NORMAL_BEAR;

		
	}

	public SecondLineUpSpawner(long delay,GamePanel panel,boolean friendly) {
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
		
		if(spawner.getPanel().getWaveManager().getWave() == 5 && enemyCount % 5 == 0) {
			spawnIntermediateEnemy();
		}else if(spawner.getPanel().getWaveManager().getWave() > 5) {
			spawnIntermediateEnemy();
		}
		else {
			spawnNormalEnemy();
		}
		
	}
	
	
	
	
	private void spawnNormalEnemy() {
		
		Spider newSpider = new NormalSpider(normalEnemy.getxPos(), normalEnemy.getyPos(),
				normalEnemy.getWidth(), normalEnemy.getHeigth(), normalEnemy.getAttack(),
	            normalEnemy.getHealth(), normalEnemy.isFriendly());
	    newSpider.resetState(normalEnemy);
	    spawner.getPanel().getEnemyNewBeings().add(newSpider);
		
		
	}
	

	private void spawnIntermediateEnemy() {
		
		Bear newBear = new NormalBear(intermediateEnemy.getxPos(), intermediateEnemy.getyPos(),
				intermediateEnemy.getWidth(), intermediateEnemy.getHeigth(),
				intermediateEnemy.getAttack(), intermediateEnemy.getHealth(),
				intermediateEnemy.isFriendly());
	    newBear.resetState(intermediateEnemy);
	    spawner.getPanel().getEnemyNewBeings().add(newBear);
	}
	
	
}
