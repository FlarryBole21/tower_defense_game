package ui;

import java.awt.Color;
import java.awt.Graphics;

import entities.bases.Bases;
import entities.bases.Cave;
import entities.bases.Fortress;
import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.NormalBear;
import entities.livingbeings.NormalLizard;
import game.GamePanel;

public class BeingLifeBar extends LifeBar{

	public BeingLifeBar(GamePanel panel, int lifeBarWidthFriend, int lifeBarWidthEnemy, int lifeBarHeight, int lifeBarX,
			int lifeBarY) {
		super(panel, lifeBarWidthFriend, lifeBarWidthEnemy, lifeBarHeight, lifeBarX, lifeBarY);
	}
	
	
	public void drawLifeBar(Graphics g, double health, boolean isFriendly) {
        double maxHealth = 100; 
   
        if(getPanel().getFriendlyLivingBeings().size() > 0) {

        	if(!(getPanel().getFriendlyLivingBeings().get(0) instanceof NormalLizard)) {
            	
            	double divisor = 1;
            	
            	if(getPanel().getFriendlyLivingBeings().get(0) instanceof IntermediateLizard) {
            		divisor = (Beings.FRIENDLY_INTERMEDIATE_LIZARD.getHealth()/Beings.FRIENDLY_NORMAL_LIZARD.getHealth());
            	}else if (getPanel().getFriendlyLivingBeings().get(0) instanceof NormalBear) {
            		divisor = (Beings.FRIENDLY_NORMAL_BEAR.getHealth()/Beings.FRIENDLY_NORMAL_LIZARD.getHealth());
            	}
          
            	if(isFriendly) {
            		setLifeBarWidthFriend(maxHealth/divisor); 
            	}
     
            }else {
            	double divisor = 1;
            	setLifeBarWidthFriend(maxHealth/divisor); 
            }
        	
        }else {
        	double divisor = 1;
        	setLifeBarWidthFriend(maxHealth/divisor); 
        	
        }
        	
        if(getPanel().getEnemyLivingBeings().size() > 0) {

        	if(!(getPanel().getEnemyLivingBeings().get(0) instanceof NormalLizard)) {
            	double divisor = 1;
            	
            	if(getPanel().getEnemyLivingBeings().get(0) instanceof IntermediateLizard) {
            		divisor = (Beings.ENEMY_INTERMEDIATE_LIZARD.getHealth()/Beings.ENEMY_NORMAL_LIZARD.getHealth());
            	}else if (getPanel().getEnemyLivingBeings().get(0) instanceof NormalBear) {
            		divisor = (Beings.ENEMY_NORMAL_BEAR.getHealth()/Beings.ENEMY_NORMAL_LIZARD.getHealth());
            	}
            	
            	if(!isFriendly) {
            		setLifeBarWidthEnemy(maxHealth/divisor); 
            	}

            }else {
            	double divisor = 1;
            	setLifeBarWidthEnemy(maxHealth/divisor); 
            	
            }		
        }else {
        	double divisor = 1;
        	setLifeBarWidthEnemy(maxHealth/divisor); 
        	
        }
        
        int lifeBarWidth=0;
        if(isFriendly) {
        	lifeBarWidth = (int)((getLifeBarWidthFriend()  * health) / maxHealth);
        }else {
        	lifeBarWidth = (int)((getLifeBarWidthEnemy() * health) / maxHealth);
        }
        
        g.setColor(isFriendly ? Color.GREEN : Color.RED);
        int x = getLifeBarX();
        int y = isFriendly ? getLifeBarY() : getLifeBarY()  + getLifeBarHeight()  + 10;
        g.fillRect(x, y, lifeBarWidth, getLifeBarHeight());
        //g.setColor(Color.BLACK);
        //g.drawRect(x, y, LIFE_BAR_WIDTH, LIFE_BAR_HEIGHT);
    }
	
	public void drawLifeBarBorder(Graphics g, boolean isFriendly) {
        double maxHealth = 100;
        double health = isFriendly ? Beings.FRIENDLY_NORMAL_LIZARD.getHealth() : Beings.ENEMY_NORMAL_LIZARD.getHealth();
        
	
        if(getPanel().getFriendlyLivingBeings().size() > 0) {

        	if(!(getPanel().getFriendlyLivingBeings().get(0) instanceof NormalLizard)) {
            	
            	double divisor = 1;
            	
            	if(getPanel().getFriendlyLivingBeings().get(0) instanceof IntermediateLizard) {
            		divisor = (Beings.FRIENDLY_INTERMEDIATE_LIZARD.getHealth()/Beings.FRIENDLY_NORMAL_LIZARD.getHealth());
            		
            		if(isFriendly) {
            			health = Beings.FRIENDLY_INTERMEDIATE_LIZARD.getHealth();
            		}
            	}else if (getPanel().getFriendlyLivingBeings().get(0) instanceof NormalBear) {
            		divisor = (Beings.FRIENDLY_NORMAL_BEAR.getHealth()/Beings.FRIENDLY_NORMAL_LIZARD.getHealth());
            		
            		if(isFriendly) {
            			health = Beings.FRIENDLY_NORMAL_BEAR.getHealth();
            		}
            	}
          
            	if(isFriendly) {
            		setLifeBarWidthFriend(maxHealth/divisor); 
            	}
     
            }else {
            	double divisor = 1;
            	setLifeBarWidthFriend(maxHealth/divisor); 	
            }
        	
        }else {
        	double divisor = 1;
        	setLifeBarWidthFriend(maxHealth/divisor); 
        	
        }
        	
        if(getPanel().getEnemyLivingBeings().size() > 0) {

        	if(!(getPanel().getEnemyLivingBeings().get(0) instanceof NormalLizard)) {
            	double divisor = 1;
            	
            	if(getPanel().getEnemyLivingBeings().get(0) instanceof IntermediateLizard) {
            		divisor = (Beings.ENEMY_INTERMEDIATE_LIZARD.getHealth()/Beings.ENEMY_NORMAL_LIZARD.getHealth());
            		
            		if(!isFriendly) {
            			health = Beings.ENEMY_INTERMEDIATE_LIZARD.getHealth();
            		}
            		
            	}else if (getPanel().getEnemyLivingBeings().get(0) instanceof NormalBear) {
            		divisor = (Beings.ENEMY_NORMAL_BEAR.getHealth()/Beings.ENEMY_NORMAL_LIZARD.getHealth());
            		
            		if(!isFriendly) {
            			health = Beings.ENEMY_NORMAL_BEAR.getHealth();
            		}
            	}
          
            	if(!isFriendly) {
            		setLifeBarWidthEnemy(maxHealth/divisor); 
            	}

            }else {
            	double divisor = 1;
            	setLifeBarWidthEnemy(maxHealth/divisor); 
            }		
        }else {
        	double divisor = 1;
        	
        	setLifeBarWidthEnemy(maxHealth/divisor); 
        	
        }
        
        
        int lifeBarWidth=0;
        if(isFriendly) {
        	lifeBarWidth = (int)((getLifeBarWidthFriend()  * health) / maxHealth);
        }else {
        	lifeBarWidth = (int)((getLifeBarWidthEnemy() * health) / maxHealth);
        }
   
      
        //g.setColor(isFriendly ? Color.GREEN : Color.RED);
        int x = getLifeBarX();
        int y = isFriendly ? getLifeBarY() : getLifeBarY()  + getLifeBarHeight()  + 10;
        g.setColor(Color.BLACK);
        g.fillRect(x, y, lifeBarWidth, getLifeBarHeight());
      
        g.drawRect(x-1, y-1, lifeBarWidth+1, getLifeBarHeight()+1);
     
    }

}
