package ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;

import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.NormalLizard;

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
	
	
	public void setImageIconButtons() {
    	for(JButton imageButton: imageIconButtons) {
    		if(imageButton.getName().equals("NormalLizardButton")) {
    			imageButton.addActionListener(e -> {
    				
    				Beings normalFriend = Beings.FRIENDLY_NORMAL_LIZARD;

                	if(panel.getFriendlyLivingBeings().size() > 0) {
                		
                		if(normalFriend.getxPos()+normalFriend.getWaitingDistance() 
                				<=panel.getFriendlyLivingBeings().get
                				(panel.getFriendlyLivingBeings().size()-1).getRect().getX()) {
                			NormalLizard newLizard = new NormalLizard(normalFriend.getxPos(), normalFriend.getyPos(),
                                    normalFriend.getWidth(), normalFriend.getHeigth(), normalFriend.getAttack(),
                                    normalFriend.getHealth(), normalFriend.isFriendly());
                            newLizard.resetState(normalFriend);
                            panel.getFriendlyLivingBeings().add(newLizard);
                			startCooldown(imageButton);
                		}else {
                			System.out.println("Kann nicht gespawnt werden!");
                		}
                	}else {
              
                        NormalLizard newLizard = new NormalLizard(normalFriend.getxPos(), normalFriend.getyPos(),
                                normalFriend.getWidth(), normalFriend.getHeigth(), normalFriend.getAttack(),
                                normalFriend.getHealth(), normalFriend.isFriendly());
                        newLizard.resetState(normalFriend);
                        panel.getFriendlyLivingBeings().add(newLizard);
                        startCooldown(imageButton);
                	}

                });
    			
    		}else if(imageButton.getName().equals("IntermediateLizardButton")) {
    			imageButton.addActionListener(e -> {
    				
    				Beings intermediateFriend = Beings.FRIENDLY_INTERMEDIATE_LIZARD;

                	if(panel.getFriendlyLivingBeings().size() > 0) {
                		
                		if(intermediateFriend.getxPos()+intermediateFriend.getWaitingDistance() 
                				<=panel.getFriendlyLivingBeings().get(panel.getFriendlyLivingBeings().size()-1).getRect().getX()) {
                			IntermediateLizard newLizard = new IntermediateLizard(intermediateFriend.getxPos(), intermediateFriend.getyPos(),
                					intermediateFriend.getWidth(), intermediateFriend.getHeigth(), intermediateFriend.getAttack(),
                                    intermediateFriend.getHealth(), intermediateFriend.isFriendly());
                            newLizard.resetState(intermediateFriend);
                            panel.getFriendlyLivingBeings().add(newLizard);
                			startCooldown(imageButton);
                		
                		}else {
                			System.out.println("Kann nicht gespawnt werden!");
                		}
                	}else {
              
                		IntermediateLizard newLizard = new IntermediateLizard(intermediateFriend.getxPos(), intermediateFriend.getyPos(),
            					intermediateFriend.getWidth(), intermediateFriend.getHeigth(), intermediateFriend.getAttack(),
                                intermediateFriend.getHealth(), intermediateFriend.isFriendly());
                        newLizard.resetState(intermediateFriend);
                        panel.getFriendlyLivingBeings().add(newLizard);
            			startCooldown(imageButton);
                	}

                });
    			
    		}
    	}
    }
	
	
	private void startCooldown(JButton button) {
    	cooldownImageIconsMapActive.put(button.getName(), true);
    	
    	switch (button.getName()) {
        	case "NormalLizardButton":
        		cooldownImageIconsMapEndTime.put(button.getName(), System.currentTimeMillis() + 5000);
        		for (JButton imageButton : imageIconButtons) {
        			if(!imageButton.getName().equals(button.getName())) {
        				cooldownImageIconsMapEndTime.put(imageButton.getName(), System.currentTimeMillis() + 1000);
        				imageButton.setEnabled(false); 
        			}
        		}
        		break;
        	case "IntermediateLizardButton":
        		cooldownImageIconsMapEndTime.put(button.getName(), System.currentTimeMillis() + 10000);
        		for (JButton imageButton : imageIconButtons) {
        			if(!imageButton.getName().equals(button.getName())) {
        				cooldownImageIconsMapEndTime.put(imageButton.getName(), System.currentTimeMillis() + 1000);
        				imageButton.setEnabled(false); 
        			}
        		}
        		break;
        	default:
        		cooldownImageIconsMapEndTime.put(button.getName(), System.currentTimeMillis() + 5000);
    	}
    	

        button.setEnabled(false); 
        updateImageIcons();
    }
    
    
    public void updateImageIcons() {
        long currentTime = System.currentTimeMillis();
        for (JButton imageButton : imageIconButtons) {
            if (imageButton.getName() != null && (imageButton.getName().equals("NormalLizardButton")
                    || imageButton.getName().equals("IntermediateLizardButton"))) {

                Boolean cooldownActive = cooldownImageIconsMapActive.get(imageButton.getName());
                Long cooldownEndTime = cooldownImageIconsMapEndTime.get(imageButton.getName());

                if(panel.getFriendlyLivingBeings().size()> 0) {
                	
                	Beings normalFriend = Beings.FRIENDLY_NORMAL_LIZARD;
                    if (cooldownActive != null && cooldownEndTime != null && normalFriend.getxPos()+normalFriend.getWaitingDistance() 
    				<=panel.getFriendlyLivingBeings().get(panel.getFriendlyLivingBeings().size()-1).getRect().getX()) {
                        if (!cooldownActive && currentTime >= cooldownEndTime) {
                            cooldownImageIconsMapActive.put(imageButton.getName(), false);
                            imageButton.setEnabled(true);
                        } else {
                            imageButton.setEnabled(false); 
                        }
                    }
                	
                }else {
                	if (cooldownActive != null && cooldownEndTime != null) {
                        if (!cooldownActive && currentTime >= cooldownEndTime) {
                            cooldownImageIconsMapActive.put(imageButton.getName(), false);
                            imageButton.setEnabled(true);
                        } else {
                            imageButton.setEnabled(false); 
                        }
                    }	  
                }
                
            }
        }
    }
    
    
    
    public void updateOnActionPerformed() {

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
