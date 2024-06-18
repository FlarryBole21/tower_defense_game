package ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.NormalBear;
import entities.livingbeings.NormalLizard;
import game.GamePanel;
import game.Main;
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
		
		if(imageIconButtons.size() > 0) {
			for(JButton imageButton: imageIconButtons) {
	    		if(imageButton.getName().equals("NormalLizardButton")) {
	    			imageButton.addActionListener(e -> {
	    				spawnNormalLizardEvent(imageButton);

	                });
	    			
	    		}else if(imageButton.getName().equals("IntermediateLizardButton")) {
	    			imageButton.addActionListener(e -> {
	    				
	    				spawnIntermediateLizardEvent(imageButton);

	                });
	    		}else if(imageButton.getName().equals("NormalBearButton")) {
	    			imageButton.addActionListener(e -> {
	    				spawnNormalBearEvent(imageButton);

	                });
	    		}
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
			startCooldown2(imageButton);
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
		
		int normalLizardCoolDown = 5000;
		int intermediateLizardCoolDown = 10000;
		
		if(imageIconButtons.size() > 0) {
			
			cooldownImageIconsMapActive.put(button.getName(), true);
	    	
	    	switch (button.getName()) {
	        	case "NormalLizardButton":
	        		cooldownImageIconsMapEndTime.put(button.getName(), System.currentTimeMillis() + normalLizardCoolDown);
	        		defaultCoolDowns(button);
	        		break;
	        	case "IntermediateLizardButton":
	        		cooldownImageIconsMapEndTime.put(button.getName(), System.currentTimeMillis() + intermediateLizardCoolDown);
	        		defaultCoolDowns(button);
	        		break;
	    	}
	    	
	        button.setEnabled(false); 
	        updateImageIcons();

		}

    }
	
	
	
    private void startCooldown2(JButton button) {
		int normalBearCoolDown = 5000;
		
		if(imageIconButtons.size() > 0) {
			
			cooldownImageIconsMapActive.put(button.getName(), true);
	    	
	    	switch (button.getName()) {
	        	case "NormalBearButton":
	        		cooldownImageIconsMapEndTime.put(button.getName(), System.currentTimeMillis() + normalBearCoolDown);
	        		defaultCoolDowns(button);
	        		break;
	    	}
	    	
	        button.setEnabled(false); 
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
						imageButton.setEnabled(false); 
					}
					
				}else {
					cooldownImageIconsMapEndTime.put(imageButton.getName(), System.currentTimeMillis() + defaultCoolDown);
					imageButton.setEnabled(false); 
				}
				
				
			}
		}
	}
    
	
    
    public void updateImageIcons() {
    	
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
    	long currentTime = System.currentTimeMillis();
    	if (cooldownActive != null && cooldownEndTime != null) {
            if ((!cooldownActive && currentTime >= cooldownEndTime)|| !panel.isGameStart()) {
                cooldownImageIconsMapActive.put(imageButton.getName(), false);
                imageButton.setEnabled(true);
            } else {
                imageButton.setEnabled(false); 
            }
        }	  
    	
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
    
    
    
    public void refreshImageIcons() {
        //panel.removeAll();
    	if(panel.getComponent(0) instanceof JPanel) {

    		JPanel subPanel = (JPanel) panel.getComponent(0);
        	subPanel.removeAll();
            for (JButton button : imageIconButtons) {
            
            	subPanel.add(button);
            }
            subPanel.add(panel.getWaveManager().getWaveLabel());
            subPanel.revalidate();
            subPanel.repaint();

    	}
    
    }
    
    
    public void returnToOriginalLineUp() {
    	
    	resetCoolDowns();
    	imageIconButtons.clear();
    	Main.resetImageIcons(imageIconButtons);
        refreshImageIcons();
        setImageIconButtonsEvents();
    }

}
