package game.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.livingbeings.Bear;
import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.NormalBear;
import game.GamePanel;

public class SecondLineUpSpawner extends Spawner{
	
	private SecondLineUpSpawner spawner;
	private int basicSpawnerLimit;
	private int enemyCount;

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
	        
	            
            if (enemySize <= basicSpawnerLimit + Math.abs(enemySize - friendlySize)) {
                
                if (spawner.getPanel().getEnemyLivingBeings().size() > 0) {
                    if (Beings.ENEMY_NORMAL_BEAR.getxPos() - Beings.ENEMY_NORMAL_BEAR.getWaitingDistance()  >= spawner.getPanel().getEnemyLivingBeings()
                            .get(spawner.getPanel().getEnemyLivingBeings().size() - 1).getRect().getX()) {

                        spawnEnemy();
                    }
                } else {
                    spawnEnemy();
                }  
            }
	        

	        startSpawning();
	        
	    }
	}

	


	private void spawnEnemy() {
	    enemyCount++;
	    spawnNormalEnemy();

	}

	
	private void spawnNormalEnemy() {
	    Bear newBear = new NormalBear(Beings.ENEMY_NORMAL_BEAR.getxPos(), Beings.ENEMY_NORMAL_BEAR.getyPos(),
	            Beings.ENEMY_NORMAL_BEAR.getWidth(), Beings.ENEMY_NORMAL_BEAR.getHeigth(),
	            Beings.ENEMY_NORMAL_BEAR.getAttack(), Beings.ENEMY_NORMAL_BEAR.getHealth(),
	            Beings.ENEMY_NORMAL_BEAR.isFriendly());
	    newBear.resetState(Beings.ENEMY_NORMAL_BEAR);
	    spawner.getPanel().getEnemyNewBeings().add(newBear);
	}


	

}
