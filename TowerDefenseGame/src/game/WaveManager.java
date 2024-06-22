package game;

import java.awt.Dimension;
import java.io.IOException;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import entities.bases.Bases;
import entities.towers.NormalStoneTower;
import entities.towers.Tower;
import entities.towers.Towers;
import game.spawner.SecondLineUpSpawner;
import game.spawner.FirstLineUpSpawner;
import game.spawner.Spawner;
import ui.setter.ButtonSetter;
import utils.CoinValues;
import utils.Path;

public class WaveManager {
	
	private GamePanel panel;
	private Spawner friendlySpawner;
    private Spawner enemySpawner;
    private int wave;
    private java.util.Timer waveTimer;
    private JLabel waveLabel; 
    private int waveDelay;
    
    {
    	waveTimer = new java.util.Timer();
    }
	
	public WaveManager(GamePanel panel,JLabel waveLabel) {
		this.panel=panel;
		this.waveLabel=waveLabel;
		this.wave=1;
		this.waveDelay=100000;
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
    	
    	
    	
    	//panel.addTowers(Towers.NORMAL_STONE_TOWER_01.getTower());
//    	panel.addTowersPlayer(new NormalStoneTower(100,600,100,
//        		100,1000,true));
    	//Towers.FRIENDLY_NORMAL_STONE_TOWER_01.getTower().startLoading();
    	//panel.addTowers(Towers.NORMAL_STONE_TOWER_02.getTower());
    	//panel.addTowers(Towers.NORMAL_STONE_TOWER_03.getTower());
//    	Towers.FRIENDLY_NORMAL_STONE_TOWER_02.getTower().startLoading();

    	panel.setFriendlyBase(Bases.FRIENDLY_CAVE.getBase());
    	panel.setEnemyBase(Bases.ENEMY_CAVE.getBase());
    	//panel.setFriendlyBase(Bases.FRIENDLY_FORTRESS.getBase());
//    	panel.setEnemyBase(Bases.ENEMY_FORTRESS.getBase());
		
		
//        friendlySpawner = new LizardSpawner(30000,panel,true);
//        friendlySpawner.startSpawning();
        enemySpawner = new FirstLineUpSpawner(5000,panel,false);
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

	private void waveSpawning(){
		TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	
            	if(panel.getFriendlyBase().getHealth() > 0 && panel.getEnemyBase().getHealth() >0) {
	                wave++;
//	                if(wave==2) {
//	                	panel.setCoins(200);
//	                	updateWaveLabel();
//	                	panel.getImageIconManager().coinTestingForAllButRemove();
//	                	
//	                
//	                }
	                if(wave >= 4) {
	                	//friendlySpawner.stopSpawning();
	                	panel.getImageIconManager().getImageIconButtons().clear();
	                	JButton normalStoneTowerImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_STONE_TOWER, "NormalStoneTowerButton", 
	            						CoinValues.NORMAL_STONE_TOWER.getValue(),64, 64);
	            		JButton normalMagicTowerImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_MAGIC_TOWER, "NormalMagicTowerButton", 
	            						CoinValues.NORMAL_MAGIC_TOWER.getValue(),64, 64);
	            		JButton normalStoneTowerRemoveImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_STONE_TOWER_REMOVE, "NormalStoneTowerRemoveButton", 0,64, 64);
	            		JButton normalBearImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_BEAR, "NormalBearButton", 
	            						CoinValues.NORMAL_BEAR.getValue(),64, 64);
	                	
	            		panel.getImageIconManager().addImageIconButton(normalStoneTowerImageButton);
	            		panel.getImageIconManager().addImageIconButton(normalMagicTowerImageButton);
	            		panel.getImageIconManager().addImageIconButton(normalStoneTowerRemoveImageButton);
	                	panel.getImageIconManager().addImageIconButton(normalBearImageButton);
	                	try {
							panel.getImageIconManager().refreshImageIcons();
						} catch (IOException e) {
							e.printStackTrace();
						}
	                	panel.getImageIconManager().setImageIconButtonsEvents();
	                	
	                	
	                	enemySpawner.stopSpawning();
//	                	friendlySpawner = new BearSpawner(30000,panel,true);
//	                    friendlySpawner.startSpawning();
	                    enemySpawner = new SecondLineUpSpawner(5000,panel,false);
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
        
        waveTimer.schedule(task, waveDelay);
		
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
	
	public void updateWaveLabel() {
        SwingUtilities.invokeLater(() -> {
            waveLabel.setText("Welle " + wave + " Gold " + panel.getCoins());
        });
    }
	

}
