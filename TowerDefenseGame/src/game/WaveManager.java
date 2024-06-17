package game;

import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import entities.bases.Bases;
import game.spawner.BearSpawner;
import game.spawner.LizardSpawner;
import game.spawner.Spawner;
import ui.GamePanel;
import utils.Path;

public class WaveManager {
	
	private GamePanel panel;
	private Spawner friendlySpawner;
    private Spawner enemySpawner;
    private int wave;
    private java.util.Timer waveTimer;
    private JLabel waveLabel; 
    
    {
    	waveTimer = new java.util.Timer();
    }
	
	public WaveManager(GamePanel panel,JLabel waveLabel) {
		this.panel=panel;
		this.waveLabel=waveLabel;
		this.wave=1;
//    	updateWaveLabel();
//    	changeBackground();
    	//startConfig();
		
	}
	
	
	public void setFriendlySpawner(Spawner friendlySpawner) {
		this.friendlySpawner = friendlySpawner;
	}

	public void setEnemySpawner(Spawner enemySpawner) {
		this.enemySpawner = enemySpawner;
	}


	public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

    public Spawner getFriendlySpawner() {
		return friendlySpawner;
	}


	public Spawner getEnemySpawner() {
		return enemySpawner;
	}

	public java.util.Timer getWaveTimer() {
		return waveTimer;
	}
	
	
	public JLabel getWaveLabel() {
		return waveLabel;
	}

	public void setWaveLabel(JLabel waveLabel) {
		this.waveLabel = waveLabel;
	}

	public void startConfig() {
		updateWaveLabel();
    	changeBackground();
//    	towersPlayer.add(new Tower(50,this.screenSize.height-(towerHeight+baseHeight),towerWidth,
//        		towerHeight,1000,true));
//        towersEnemy.add(new Tower(this.screenSize.width-towerWidth,this.screenSize.height-
//        		(towerHeight+baseHeight),towerWidth,towerHeight,
//        		1000,true));
    	panel.setFriendlyBase(Bases.FRIENDLY_CAVE.getBase(),panel.getTowersPlayer());
    	panel.setEnemyBase(Bases.ENEMY_CAVE.getBase(),panel.getTowersEnemy());
//    	panel.setFriendlyBase(Bases.FRIENDLY_FORTRESS.getBase(),panel.getTowersPlayer());
//    	panel.setEnemyBase(Bases.ENEMY_FORTRESS.getBase(),panel.getTowersEnemy());
		
		
//        friendlySpawner = new LizardSpawner(30000,panel,true);
//        friendlySpawner.startSpawning();
        enemySpawner = new LizardSpawner(3000,panel,false);
        enemySpawner.startSpawning();
        
        waveSpawning();
	
    }
	
	
	public void reset() {
	    waveTimer.cancel(); 
	    waveTimer = new java.util.Timer();
	    wave = 1; 
	    if (friendlySpawner != null) {
	        friendlySpawner.stopSpawning();
	    }
	    if (enemySpawner != null) {
	        enemySpawner.stopSpawning();
	    }
	}

	private void waveSpawning() {
		TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	
            	if(panel.getFriendlyBase().getHealth() > 0 && panel.getEnemyBase().getHealth() >0) {
	                wave++;
	                if(wave >= 4) {
	                	//friendlySpawner.stopSpawning();
	                	enemySpawner.stopSpawning();
//	                	friendlySpawner = new BearSpawner(30000,panel,true);
//	                    friendlySpawner.startSpawning();
	                    enemySpawner = new BearSpawner(3000,panel,false);
	                    enemySpawner.startSpawning();
	//                    removeBaseBases();
	//                    addBaseBases(Bases.FRIENDLY_FORTRESS.getBase(),towersPlayer);
	//                    addBaseBases(Bases.ENEMY_FORTRESS.getBase(),towersEnemy);
	                }
	                updateWaveLabel();
	                changeBackground();
	                waveSpawning();
            	}
            }
        };
        
        waveTimer.schedule(task, 50000);
		
	}
	
	
	private void changeBackground() {
		SwingUtilities.invokeLater(() -> {
			if(wave == 1) {
				panel.loadBackgroundImage(Path.IMAGE_BACKGROUND_01);
			}else if(wave == 4) {
				panel.loadBackgroundImage(Path.IMAGE_BACKGROUND_02);
            }
            
        });
	}
	
	private void updateWaveLabel() {
        SwingUtilities.invokeLater(() -> {
            waveLabel.setText("Welle " + wave);
        });
    }
	

}
