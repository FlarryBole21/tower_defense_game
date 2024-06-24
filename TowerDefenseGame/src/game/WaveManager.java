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
	private static final int LINEUPSPAWNERDELAY = 5000;
	private Spawner friendlySpawner;
    private Spawner enemySpawner;
    private int wave;
    private java.util.Timer waveTimer;
    private JLabel waveLabel; 
    private int waveDelay;
    private int waveMax;
    
    {
    	waveTimer = new java.util.Timer();
    }
	
    //Reguliert die Wellen
    //Spiel fängt bei Welle 1, Welle 5 ist die letzte Welle
	public WaveManager(GamePanel panel,JLabel waveLabel) {
		this.panel=panel;
		this.waveLabel=waveLabel;
		this.wave=1;
		this.waveDelay=100000;
		this.waveMax=5;

		
	}
	
	
	public int getWaveMax() {
		return waveMax;
	}


	public void setWaveMax(int waveMax) {
		this.waveMax = waveMax;
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

    	panel.setFriendlyBase(Bases.FRIENDLY_CAVE.getBase());
    	panel.setEnemyBase(Bases.ENEMY_CAVE.getBase());


        enemySpawner = new FirstLineUpSpawner(LINEUPSPAWNERDELAY ,panel,false);
        enemySpawner.startSpawning();
        
        waveSpawning();
	
    }
	
	
	//Reset der Welle auf Welle 1 und reset des Timers
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

	//Wellen-Timer, nach Wellen-Delay wird um eine Welle erhöt
	private void waveSpawning(){
		TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	
            	if(panel.getFriendlyBase().getHealth() > 0 && panel.getEnemyBase().getHealth() >0) {
	                wave++;

	                //Bei Welle 4 werden die meisten Icons aus Welle 1-3 durch neue ausgetauscht
	                //Neue Icons haben neue Events
	                if(wave >= 4 && wave <= waveMax) {

	                	panel.getImageIconManager().getImageIconButtons().clear();
	                	JButton normalStoneTowerImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_STONE_TOWER, "NormalStoneTowerButton", 
	            						CoinValues.NORMAL_STONE_TOWER.getValue(),64, 64);
	            		JButton normalMagicTowerImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_MAGIC_TOWER, "NormalMagicTowerButton", 
	            						CoinValues.NORMAL_MAGIC_TOWER.getValue(),64, 64);
	            		JButton normalStoneTowerRemoveImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_STONE_TOWER_REMOVE, "NormalStoneTowerRemoveButton", 0,64, 64);
	            		JButton normalSpiderImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_SPIDER, "NormalSpiderButton", 
	            						CoinValues.NORMAL_SPIDER.getValue(),64, 64);
	            		JButton normalBearImageButton = ButtonSetter.setImageIconButton
	            				(Path.IMAGE_ICON_NORMAL_BEAR, "NormalBearButton", 
	            						CoinValues.NORMAL_BEAR.getValue(),64, 64);
	           
	                	
	            		panel.getImageIconManager().addImageIconButton(normalStoneTowerImageButton);
	            		panel.getImageIconManager().addImageIconButton(normalMagicTowerImageButton);
	            		panel.getImageIconManager().addImageIconButton(normalStoneTowerRemoveImageButton);
	            		panel.getImageIconManager().addImageIconButton(normalSpiderImageButton);
	                	panel.getImageIconManager().addImageIconButton(normalBearImageButton);
	               
	                	try {
							panel.getImageIconManager().refreshImageIcons();
						} catch (IOException e) {
							e.printStackTrace();
						}
	                	panel.getImageIconManager().setImageIconButtonsEvents();
	                	
	                	
	                	enemySpawner.stopSpawning();
	                    enemySpawner = new SecondLineUpSpawner(LINEUPSPAWNERDELAY,panel,false);
	                    enemySpawner.startSpawning();
	                }
	                updateWaveLabel();
	                changeBackground();
	                waveSpawning();
            	}
            }
        };
        
        waveTimer.schedule(task, waveDelay);
		
	}
	
	
	//Updaten des Hintergrunds je nach Welle
	private void changeBackground() {
		SwingUtilities.invokeLater(() -> {
			if(wave == 1) {
				panel.loadBackgroundImage(Path.IMAGE_BACKGROUND_01);
			}else if(wave == 4) {
				panel.loadBackgroundImage(Path.IMAGE_BACKGROUND_02);
            }
            
        });
	}
	
	
	//Updaten der Wellenanzeige
	public void updateWaveLabel() {
        SwingUtilities.invokeLater(() -> {
            waveLabel.setText("Welle " + wave + " Gold " + panel.getCoins());
        });
    }
	

}
