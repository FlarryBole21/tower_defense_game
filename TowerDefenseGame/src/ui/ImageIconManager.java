package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.NormalBear;
import entities.livingbeings.NormalLizard;
import entities.towers.MagicTower;
import entities.towers.Tower;
import entities.towers.Towers;
import game.GamePanel;
import game.Main;
import ui.setter.PanelSetter;
import utils.CoinValues;
import utils.Path;

public class ImageIconManager {
	
	private GamePanel panel;
	private LinkedList<JButton> imageIconButtons;
	private Map<String, Boolean> cooldownImageIconsMapActive;
    private Map<String, Long> cooldownImageIconsMapEndTime;
	
	
	{
		cooldownImageIconsMapActive=new HashMap<>();
        cooldownImageIconsMapEndTime=new HashMap<>();
	}
	
	public ImageIconManager(GamePanel panel,LinkedList<JButton> imageIconButtons) {
		this.panel=panel;
		this.imageIconButtons=imageIconButtons;
	}
	

	public LinkedList<JButton> getImageIconButtons() {
		return imageIconButtons;
	}




	public Map<String, Boolean> getCooldownImageIconsMapActive() {
		return cooldownImageIconsMapActive;
	}



	public Map<String, Long> getCooldownImageIconsMapEndTime() {
		return cooldownImageIconsMapEndTime;
	}


	public void addImageIconButton(JButton button) {
		
		imageIconButtons.add(button);
	}


	public void setImageIconButtonsEvents() {
		
		if(panel == null) {
			return;
		}

		Beings normalFriend = Beings.FRIENDLY_NORMAL_LIZARD;
		
		
		if(panel.getFriendlyLivingBeings().size() > 0) {
			if(normalFriend.getxPos()+normalFriend.getWaitingDistance() 
			>panel.getFriendlyLivingBeings().get(panel.getFriendlyLivingBeings().size()-1).getRect().getX()) {
				
				for(JButton button: imageIconButtons) {
					if(!button.getName().contains("Tower")) {
						startCooldown(button);
					}
				}
			}

		}
		

		if(panel.getTowers().size() > 2) {
			for(JButton button: imageIconButtons) {
				if(button.getName().contains("Tower") && !button.getName().contains("Remove")) {
					button.setEnabled(false);
				}
			}
		}
		
		coinTestingForAllButRemove();
		

		if(imageIconButtons.size() > 0) {
			for(JButton imageButton: imageIconButtons) {
	    		if(imageButton.getName().equals("NormalLizardButton")) {
	    			imageButton.addActionListener(e -> {

	    				panel.setCoins(panel.getCoins()-CoinValues.NORMAL_LIZARD.getValue());
	    				panel.getWaveManager().updateWaveLabel();
    					spawnNormalLizardEvent(imageButton);
	    			

	                });
	    			
	    		}else if(imageButton.getName().equals("IntermediateLizardButton")) {
	    			imageButton.addActionListener(e -> {
	    				
	    				panel.setCoins(panel.getCoins()-CoinValues.INTERMEDIATE_LIZARD.getValue());
	    				panel.getWaveManager().updateWaveLabel();
	    				spawnIntermediateLizardEvent(imageButton);
	    				

	                });
	    		}else if(imageButton.getName().equals("NormalBearButton")) {
	    			imageButton.addActionListener(e -> {
	    				panel.setCoins(panel.getCoins()-CoinValues.NORMAL_BEAR.getValue());
	    				panel.getWaveManager().updateWaveLabel();
	    				spawnNormalBearEvent(imageButton);
	    			

	                });
	    		}else if(imageButton.getName().equals("NormalStoneTowerButton")) {
	    			
	    			imageButton.addActionListener(e -> {
	    				panel.setCoins(panel.getCoins()-CoinValues.NORMAL_STONE_TOWER.getValue());
	    				panel.getWaveManager().updateWaveLabel();
	    				spawnNormalStoneTower(imageButton);
	    			
	    				for(JButton button: imageIconButtons) {
	    					if(button.getName().equals("NormalStoneTowerRemoveButton")) {
	    						button.setEnabled(true);
	    					}
	    				}
	    				

	                });
	    		}else if(imageButton.getName().equals("NormalMagicTowerButton")) {
	    			imageButton.addActionListener(e -> {
	    				panel.setCoins(panel.getCoins()-CoinValues.NORMAL_MAGIC_TOWER.getValue());
	    				panel.getWaveManager().updateWaveLabel();
	    				spawnMagicStoneTower(imageButton);
	    			
	    				
	    				for(JButton button: imageIconButtons) {
	    					if(button.getName().equals("NormalStoneTowerRemoveButton")) {
	    						button.setEnabled(true);
	    					}
	    				}
	    				

	                });
	    		}else if(imageButton.getName().equals("NormalStoneTowerRemoveButton")) {
	    			imageButton.addActionListener(e -> {
	    				if(panel.getTowers().size() > 0) {
	    					panel.getTowers().remove(panel.getTowers().size()-1);
	    					
	    					if(panel.getTowers().size() == 0) {
	    						imageButton.setEnabled(false);
	    					}
	    					
	    					
	    					
	    					if(panel.getTowers().size() < 3) {
	    						for(JButton button: imageIconButtons) {
			    					if(button.getName().contains("Tower") && !button.getName().contains("Remove")) {
			    						
			    						
			    						if(button.getName().contains("Magic")) {
			    							boolean alreadyThere = false;
			    							
			    							for(Tower tower :panel.getTowers()) {
			    								if(tower instanceof MagicTower) {
			    									alreadyThere=true;
			    								}
			    								
			    							}
			    							
			    							if(!alreadyThere) {
			    								
			    								
			    								
		    									if(coinTester(button)) {
		    									
		    										button.setEnabled(true);
		    									}
			    								
			    							}
			    							
			    						}else {
			    							if(coinTester(button)) {
		    									
	    										button.setEnabled(true);
	    									}
			    						}
			    						
			    					}
			    				}
	    						
	    					}
	    					
	    				}else {
	    					imageButton.setEnabled(false);
	    				}
	    				

	             });
	    		}
	    	}
		}
    }
	
	
	public void coinTestingForAllButRemove() {
		for(JButton button: imageIconButtons) {
			if(!coinTester(button) && !button.getName().contains("Remove")) {
				button.setEnabled(false);
				
			}else if(coinTester(button) && !button.getName().contains("Remove")) {
				
				
				if(button.getName().contains("Tower") && panel.getTowers().size() < 3) {
					
					
					if(button.getName().contains("Magic")) {
						
						
						boolean alreadyThere = false;
						
						for(Tower tower :panel.getTowers()) {
							if(tower instanceof MagicTower) {
								alreadyThere=true;
							}
							
						}
						
						if(!alreadyThere) {
							button.setEnabled(true);
						}
						
					}else {
						
						button.setEnabled(true);
						
					}

				}
				
			}
		}
	}
	
	
	private void spawnNormalStoneTower(JButton imageButton) {
	
		
		
		if(panel.getTowers().size()==0) {
			panel.addTowers(Towers.NORMAL_STONE_TOWER_01.getTower());
			
		}else if(panel.getTowers().size()==1) {
			panel.addTowers(Towers.NORMAL_STONE_TOWER_02.getTower());
		}else if(panel.getTowers().size()==2) {
			panel.addTowers(Towers.NORMAL_STONE_TOWER_03.getTower());
			for(JButton button: imageIconButtons) {
				if(button.getName().contains("Tower") && !button.getName().contains("Remove")) {
					button.setEnabled(false);
				}
			}
			//imageButton.setEnabled(false); 
		}
		
		for(JButton button: imageIconButtons) {
			if(!coinTester(button) && !button.getName().contains("Remove")) {
			
				button.setEnabled(false);
			}
		}

		
	}
	
	
	private void spawnMagicStoneTower(JButton imageButton) {
		
		
		Runnable run = ()->{
			
			for(JButton button: imageIconButtons) {
				if(button.getName().contains("Magic")) {
					button.setEnabled(false);
				}
			}
			
		};
		
		if(panel.getTowers().size()==0) {
			
			boolean alreadyThere = false;
			
			for(Tower tower :panel.getTowers()) {
				if(tower instanceof MagicTower) {
					alreadyThere=true;
				}
				
			}
			
			if(!alreadyThere) {
				panel.addTowers(Towers.NORMAL_MAGIC_TOWER_01.getTower());	
				
				run.run();
			}
		
		}else if(panel.getTowers().size()==1) {
			boolean alreadyThere = false;
			
			for(Tower tower :panel.getTowers()) {
				if(tower instanceof MagicTower) {
					alreadyThere=true;
				}
				
			}
			
			if(!alreadyThere) {
				panel.addTowers(Towers.NORMAL_MAGIC_TOWER_02.getTower());	
				
				run.run();
			}
			
		}else if(panel.getTowers().size()==2) {
			
			
			boolean alreadyThere = false;
			
			for(Tower tower :panel.getTowers()) {
				if(tower instanceof MagicTower) {
					alreadyThere=true;
				}
				
			}
			
			if(!alreadyThere) {
				panel.addTowers(Towers.NORMAL_MAGIC_TOWER_03.getTower());	
			
				for(JButton button: imageIconButtons) {
					if(button.getName().contains("Tower") && !button.getName().contains("Remove")) {
						button.setEnabled(false);
					}
				}
			}

		}
		
		
		for(JButton button: imageIconButtons) {
			if(!coinTester(button) && !button.getName().contains("Remove")) {
			
				button.setEnabled(false);
			}
		}
		
	}
	

	private void spawnNormalLizardEvent(JButton imageButton) {
	    Beings friend = Beings.FRIENDLY_NORMAL_LIZARD;

	    Runnable runnable = () -> {
	        NormalLizard newLizard = new NormalLizard(friend.getxPos(), friend.getyPos(),
	                friend.getWidth(), friend.getHeigth(), friend.getAttack(),
	                friend.getHealth(), friend.isFriendly());
	        newLizard.resetState(friend);
	        panel.getFriendlyLivingBeings().add(newLizard);
	        startCooldown(imageButton);
	    };

	    spawnFriend(friend,runnable);
	}
 
	
	
	private void spawnIntermediateLizardEvent(JButton imageButton) {
		
		Beings friend = Beings.FRIENDLY_INTERMEDIATE_LIZARD;

		Runnable runnable = ()->{
			IntermediateLizard newLizard = new IntermediateLizard(friend.getxPos(), friend.getyPos(),
					friend.getWidth(), friend.getHeigth(), friend.getAttack(),
                    friend.getHealth(), friend.isFriendly());
            newLizard.resetState(friend);
            panel.getFriendlyLivingBeings().add(newLizard);
			startCooldown(imageButton);
		};

		spawnFriend(friend,runnable);
		
	}
	
	
	private void spawnNormalBearEvent(JButton imageButton) {
		
		Beings friend = Beings.FRIENDLY_NORMAL_BEAR;
		
		Runnable runnable = ()->{
			NormalBear newBear = new NormalBear(friend.getxPos(), friend.getyPos(),
                    friend.getWidth(), friend.getHeigth(), friend.getAttack(),
                    friend.getHealth(), friend.isFriendly());
			newBear.resetState(friend);
            panel.getFriendlyLivingBeings().add(newBear);
			startCooldown(imageButton);
		};
		

		spawnFriend(friend,runnable);
		
	} 
	
	
	private void spawnFriend(Beings friend,Runnable runnable) {
		
		if(panel.getFriendlyLivingBeings().size() > 0) {
    		
    		if(friend.getxPos()+friend.getWaitingDistance() 
    				<=panel.getFriendlyLivingBeings().get
    				(panel.getFriendlyLivingBeings().size()-1).getRect().getX()) {
    			runnable.run();
    		}
    	}else {
  
    		runnable.run();
    	}
		
	}
	
	
	private void startCooldown(JButton button) {
		
		if(imageIconButtons.size() > 0) {
			
			cooldownImageIconsMapActive.put(button.getName(), true);
			cooldownImageIconsMapEndTime.put(button.getName(), System.currentTimeMillis());
			defaultCoolDowns(button);

	    	
	    	if (!button.getName().contains("Tower")) {
                button.setEnabled(false);
            }
	        updateImageIcons();

		}

    }
	
	
	
	
	private void defaultCoolDowns(JButton button) {
		int defaultCoolDown = 1000;
		
		for (JButton imageButton : imageIconButtons) {
			if(!imageButton.getName().equals(button.getName())) {
				
				if(cooldownImageIconsMapActive.containsKey(imageButton.getName())) {
					
					if(!cooldownImageIconsMapActive.get(imageButton.getName())) {
						cooldownImageIconsMapEndTime.put(imageButton.getName(), System.currentTimeMillis() + defaultCoolDown);
						if (!imageButton.getName().contains("Tower")) {
		                    imageButton.setEnabled(false);
		                }
					}
					
				}else {
					cooldownImageIconsMapEndTime.put(imageButton.getName(), System.currentTimeMillis() + defaultCoolDown);
					if (!imageButton.getName().contains("Tower")) {
	                    imageButton.setEnabled(false);
	                }

				}
				
				
			}
		}
	}
    
	
    
    public void updateImageIcons() {
    	
    	
    	for(JButton button: imageIconButtons) {
			if(!coinTester(button) && button.getName().contains("Tower") &&!button.getName().contains("Remove")) {
				button.setEnabled(false);
				
			}
		}
    	
    	if(imageIconButtons.size() > 0){
    		
            for (JButton imageButton : imageIconButtons) {
                if (imageButton.getName() != null) {

                    Boolean cooldownActive = cooldownImageIconsMapActive.get(imageButton.getName());
                    Long cooldownEndTime = cooldownImageIconsMapEndTime.get(imageButton.getName());

                    if(panel.getFriendlyLivingBeings().size()> 0) {
                    	
                    	Beings normalFriend = Beings.FRIENDLY_NORMAL_LIZARD;
                        if (cooldownActive != null && cooldownEndTime != null && normalFriend.getxPos()+normalFriend.getWaitingDistance() 
        				<=panel.getFriendlyLivingBeings().get(panel.getFriendlyLivingBeings().size()-1).getRect().getX()) {
                        	
                        	coolDownSwitcher(cooldownActive, cooldownEndTime, imageButton);	 
                        }
                    	
                    }else {
                    	
                    	coolDownSwitcher(cooldownActive, cooldownEndTime, imageButton);	  
                    }
                    
                }
            }

    	}

    }
    
    
    private void coolDownSwitcher(Boolean cooldownActive, Long cooldownEndTime, JButton imageButton) {
    	
    	boolean coinTest =coinTester(imageButton);
    	
    	if(!coinTest) {
    		return;
    	}
    	
    	
    	long currentTime = System.currentTimeMillis();
    	if (cooldownActive != null && cooldownEndTime != null) {
            if ((!cooldownActive && currentTime >= cooldownEndTime)|| !panel.isGameStart()) {
                cooldownImageIconsMapActive.put(imageButton.getName(), false);
                
                if (!imageButton.getName().contains("Tower")) {
                    imageButton.setEnabled(true);
                }
                
            } else {
            	if (!imageButton.getName().contains("Tower")) {
                    imageButton.setEnabled(false);
                }
            }
        }	  
    	
    }
    
    
    private boolean coinTester(JButton imageButton) {
    	
    	if(imageButton.getName().equals("NormalLizardButton")) {
    		if(panel.getCoins() < CoinValues.NORMAL_LIZARD.getValue()) {
        		return false;
        	}
    	}else if(imageButton.getName().equals("IntermediateLizardButton")) {
    		if(panel.getCoins() < CoinValues.INTERMEDIATE_LIZARD.getValue()) {
        		return false;
        	}
    	}else if(imageButton.getName().equals("NormalBearButton")) {
    		if(panel.getCoins() < CoinValues.NORMAL_BEAR.getValue()) {
        		return false;
        	}
    	}else if(imageButton.getName().equals("NormalStoneTowerButton")) {
    		if(panel.getCoins() < CoinValues.NORMAL_STONE_TOWER.getValue()) {
        		return false;
        	}
    	}else if(imageButton.getName().equals("NormalMagicTowerButton")) {
    		if(panel.getCoins() < CoinValues.NORMAL_MAGIC_TOWER.getValue()) {
        		return false;
        	}
    	}

    	return true;
    	
    }
    
    
    public void updateOnActionPerformed() {

    	if(imageIconButtons.size() > 0) {
    		for (Map.Entry<String, Long> endTimeEntry : cooldownImageIconsMapEndTime.entrySet()) {
    	        String key = endTimeEntry.getKey();
    	        Long cooldownEndTime = endTimeEntry.getValue();
    	        
    	        if (System.currentTimeMillis() >= cooldownEndTime) {
    	            cooldownImageIconsMapActive.put(key, false); 
    	            updateImageIcons(); 
    	        }
    	    }	
    	}
    }
    
    
    public void resetCoolDowns() {
    
    	for (Map.Entry<String, Boolean> activeEntry : getCooldownImageIconsMapActive().entrySet()) {
    		cooldownImageIconsMapActive.put(activeEntry.getKey(),false);
    		cooldownImageIconsMapEndTime.put(activeEntry.getKey(),System.currentTimeMillis());
    		updateImageIcons();
    	}
    	
    }
    
    
    
    public void refreshImageIcons() throws IOException {
        //panel.removeAll();
    	for(Component comp: panel.getComponents()) {
    		
    		if(comp instanceof JPanel) {
    			
    			if(comp.getName().equals("Bottom Panel")) {

    	    		JPanel subPanel = (JPanel) comp;
    	        	subPanel.removeAll();
    	            for (JButton button : imageIconButtons) {
    	            	
    	            	PanelSetter.coinLabelSetting(button, subPanel);
    	            }
    	            subPanel.add(panel.getWaveManager().getWaveLabel());
    	            subPanel.revalidate();
    	            subPanel.repaint();

    	    	}

    		}

    	}
    	
    }
    
    
    public void returnToOriginalLineUp() throws IOException {
    	
    	resetCoolDowns();
    	imageIconButtons.clear();
    	Main.resetImageIcons(imageIconButtons);
        refreshImageIcons();
        setImageIconButtonsEvents();
    }

}
