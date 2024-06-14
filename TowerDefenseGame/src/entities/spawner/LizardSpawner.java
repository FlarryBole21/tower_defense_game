package entities.spawner;

import java.awt.Dimension;
import java.util.TimerTask;

import entities.Beings;
import entities.IntermediateLizard;
import entities.Lizard;
import entities.NormalLizard;
import ui.GamePanel;

public class LizardSpawner extends Spawner{
	
	private LizardSpawner spawner;
	private boolean friendly;
	private int basicSpawnerLimit;
	private String type;

	public LizardSpawner(long delay,GamePanel panel,boolean friendly,String type) {
		super(delay,panel);
		spawner = this;
		this.friendly=friendly;
		this.type=type;
		basicSpawnerLimit=8;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
        				
        				if(Beings.FRIENDLY_NORMAL_LIZARD.getxPos()+100 
        						<= spawner.getPanel().getFriendlyLivingBeings()
        						.get(spawner.getPanel().getFriendlyLivingBeings().size()-1).getRect().getX()
        						&& Beings.FRIENDLY_INTERMEDIATE_LIZARD.getxPos()+100 
        						<= spawner.getPanel().getFriendlyLivingBeings()
        						.get(spawner.getPanel().getFriendlyLivingBeings().size()-1).getRect().getX()) {
        					
        					switch (type) {
        				    case "Normal":
        				    	spawnNormalFriend();
        				        break;
        				    case "Intermediate":
        				    	spawnIntermediateFriend();
        				        break;
        				    default:
        					}
        				}

        			}else {

        				switch (type) {
    				    case "Normal":
    				    	spawnNormalFriend();
    				        break;
    				    case "Intermediate":
    				    	spawnIntermediateFriend();
    				        break;
    				    default:
    					}
        			}

            	}
	
        	}else {
        		
        		if(enemySize <= basicSpawnerLimit+Math.abs(enemySize-friendlySize)) {
        			
    			if(spawner.getPanel().getEnemyLivingBeings().size() > 0) {
        				if(Beings.ENEMY_NORMAL_LIZARD.getxPos()-100 >= spawner.getPanel().getEnemyLivingBeings()
        						.get(spawner.getPanel().getEnemyLivingBeings().size()-1).getRect().getX() &&
        						Beings.ENEMY_INTERMEDIATE_LIZARD.getxPos()-100 >= spawner.getPanel().getEnemyLivingBeings()
        						.get(spawner.getPanel().getEnemyLivingBeings().size()-1).getRect().getX()) {

        					switch (type) {
        				    case "Normal":
        				    	spawnNormalEnemy();
        				        break;
        				    case "Intermediate":
        				    	spawnIntermediateEnemy();
        				        break;
        				    default:
        					}
        				}
        			}else {
        				switch (type) {
    				    case "Normal":
    				    	spawnNormalEnemy();
    				        break;
    				    case "Intermediate":
    				    	spawnIntermediateEnemy();
    				        break;
    				    default:
    					}

        			}  
            	}
        	}

            startSpawning();
        	
        }
    }
	
	
	private void spawnNormalFriend() {
		Lizard newLizard = new NormalLizard(Beings.FRIENDLY_NORMAL_LIZARD.getxPos(),Beings.FRIENDLY_NORMAL_LIZARD.getyPos(),
    			Beings.FRIENDLY_NORMAL_LIZARD.getWidth(),Beings.FRIENDLY_NORMAL_LIZARD.getHeigth(),Beings.FRIENDLY_NORMAL_LIZARD.getAttack(),
    			Beings.FRIENDLY_NORMAL_LIZARD.getHealth(),Beings.FRIENDLY_NORMAL_LIZARD.isFriendly());
        newLizard.resetState(Beings.FRIENDLY_NORMAL_LIZARD);
        spawner.getPanel().getFriendlyNewBeings().add(newLizard);
	}
	
	
	private void spawnNormalEnemy() {
		
		Lizard newLizard2 = new NormalLizard(Beings.ENEMY_NORMAL_LIZARD.getxPos(),Beings.ENEMY_NORMAL_LIZARD.getyPos(),
        		Beings.ENEMY_NORMAL_LIZARD.getWidth(),Beings.ENEMY_NORMAL_LIZARD.getHeigth(),Beings.ENEMY_NORMAL_LIZARD.getAttack(),
        		Beings.ENEMY_NORMAL_LIZARD.getHealth(),Beings.ENEMY_NORMAL_LIZARD.isFriendly());
        newLizard2.resetState(Beings.ENEMY_NORMAL_LIZARD);
        spawner.getPanel().getEnemyNewBeings().add(newLizard2);
		
	}
	
	
	private void spawnIntermediateFriend() {
	    Lizard newLizard = new IntermediateLizard(Beings.FRIENDLY_INTERMEDIATE_LIZARD.getxPos(), Beings.FRIENDLY_INTERMEDIATE_LIZARD.getyPos(),
	            Beings.FRIENDLY_INTERMEDIATE_LIZARD.getWidth(), Beings.FRIENDLY_INTERMEDIATE_LIZARD.getHeigth(), Beings.FRIENDLY_INTERMEDIATE_LIZARD.getAttack(),
	            Beings.FRIENDLY_INTERMEDIATE_LIZARD.getHealth(), Beings.FRIENDLY_INTERMEDIATE_LIZARD.isFriendly());
	    newLizard.resetState(Beings.FRIENDLY_INTERMEDIATE_LIZARD);
	    spawner.getPanel().getFriendlyNewBeings().add(newLizard);
	}

	private void spawnIntermediateEnemy() {
	    Lizard newLizard2 = new IntermediateLizard(Beings.ENEMY_INTERMEDIATE_LIZARD.getxPos(), Beings.ENEMY_INTERMEDIATE_LIZARD.getyPos(),
	            Beings.ENEMY_INTERMEDIATE_LIZARD.getWidth(), Beings.ENEMY_INTERMEDIATE_LIZARD.getHeigth(), Beings.ENEMY_INTERMEDIATE_LIZARD.getAttack(),
	            Beings.ENEMY_INTERMEDIATE_LIZARD.getHealth(), Beings.ENEMY_INTERMEDIATE_LIZARD.isFriendly());
	    newLizard2.resetState(Beings.ENEMY_INTERMEDIATE_LIZARD);
	    spawner.getPanel().getEnemyNewBeings().add(newLizard2);
	}


}
