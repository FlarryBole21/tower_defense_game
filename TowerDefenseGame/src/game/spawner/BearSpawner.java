package game.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.livingbeings.Bear;
import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.NormalBear;
import ui.GamePanel;

public class BearSpawner extends Spawner{
	
	private BearSpawner spawner;
	private boolean friendly;
	private int basicSpawnerLimit;
	private int friendlyCount;
	private int enemyCount;

	public BearSpawner(long delay,GamePanel panel,boolean friendly) {
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

	public boolean isFriendly() {
		return friendly;
	}


	private class SpawnTask extends TimerTask {

	    @Override
	    public void run() {
	        
	        int friendlySize = spawner.getPanel().getFriendlyLivingBeings().size();
	        int enemySize = spawner.getPanel().getEnemyLivingBeings().size();
	        
	        if (friendly) {
	            
	            if (friendlySize <= basicSpawnerLimit + Math.abs(enemySize - friendlySize)) {
	                
	                if (spawner.getPanel().getFriendlyLivingBeings().size() > 0) {
	                    
	                    if (Beings.FRIENDLY_NORMAL_BEAR.getxPos() + Beings.FRIENDLY_NORMAL_BEAR.getWaitingDistance()  
	                            <= spawner.getPanel().getFriendlyLivingBeings()
	                            .get(spawner.getPanel().getFriendlyLivingBeings().size() - 1).getRect().getX()) {
	                        
	                        spawnFriend();
	                    }

	                } else {

	                    spawnFriend();
	                }

	            }
	    
	        } else {
	            
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
	        }

	        startSpawning();
	        
	    }
	}

	
	private void spawnFriend() {
	    friendlyCount++;
	    spawnNormalFriend();  
	}


	private void spawnEnemy() {
	    enemyCount++;
	    spawnNormalEnemy();

	}

	
	
	private void spawnNormalFriend() {
	    Bear newBear = new NormalBear(Beings.FRIENDLY_NORMAL_BEAR.getxPos(), Beings.FRIENDLY_NORMAL_BEAR.getyPos(),
	            Beings.FRIENDLY_NORMAL_BEAR.getWidth(), Beings.FRIENDLY_NORMAL_BEAR.getHeigth(),
	            Beings.FRIENDLY_NORMAL_BEAR.getAttack(), Beings.FRIENDLY_NORMAL_BEAR.getHealth(),
	            Beings.FRIENDLY_NORMAL_BEAR.isFriendly());
	    newBear.resetState(Beings.FRIENDLY_NORMAL_BEAR);
	    spawner.getPanel().getFriendlyNewBeings().add(newBear);
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
