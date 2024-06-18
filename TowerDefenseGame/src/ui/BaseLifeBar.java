package ui;

import java.awt.Color;
import java.awt.Graphics;

import entities.bases.Bases;
import entities.bases.Cave;
import entities.bases.Fortress;
import game.GamePanel;

public class BaseLifeBar extends LifeBar{

	public BaseLifeBar(GamePanel panel,int lifeBarWidthFriend, int lifeBarWidthEnemy, int lifeBarHeight, int lifeBarX, int lifeBarY) {
		super(panel,lifeBarWidthFriend, lifeBarWidthEnemy, lifeBarHeight, lifeBarX, lifeBarY);
		
	}
	
	
	public void drawLifeBar(Graphics g, int health, boolean isFriendly) {
        int maxHealth = 100; 
        if(!(getPanel().getFriendlyBase() instanceof Cave)) {
        	
        	int divisor = 1;
        	
        	if(getPanel().getFriendlyBase() instanceof Fortress) {
        		divisor = Bases.FRIENDLY_FORTRESS.getHealth()/Bases.FRIENDLY_CAVE.getHealth();
        	}
      
        	if(isFriendly) {
        		setLifeBarWidthFriend(maxHealth/divisor); 
        	}
 
        }
        
        if(!(getPanel().getEnemyBase() instanceof Cave)) {
        	int divisor = 1;
        	
        	if(getPanel().getEnemyBase() instanceof Fortress) {
        		divisor = Bases.ENEMY_FORTRESS.getHealth()/Bases.ENEMY_CAVE.getHealth();
        	}
        	
        	if(!isFriendly) {
        		setLifeBarWidthEnemy(maxHealth/divisor); 
        	}

        }
        
        int lifeBarWidth=0;
        if(isFriendly) {
        	lifeBarWidth = (getLifeBarWidthFriend()  * health) / maxHealth;
        }else {
        	lifeBarWidth = (getLifeBarWidthEnemy() * health) / maxHealth;
        }
        
        g.setColor(isFriendly ? Color.GREEN : Color.RED);
        int x = getLifeBarX();
        int y = isFriendly ? getLifeBarY() : getLifeBarY()  + getLifeBarHeight()  + 10;
        g.fillRect(x, y, lifeBarWidth, getLifeBarHeight());
        //g.setColor(Color.BLACK);
        //g.drawRect(x, y, LIFE_BAR_WIDTH, LIFE_BAR_HEIGHT);
    }
	
	public void drawLifeBarBorder(Graphics g, boolean isFriendly) {
        int maxHealth = 100;
        int health = isFriendly ? Bases.FRIENDLY_CAVE.getHealth() : Bases.ENEMY_CAVE.getHealth();
        
        if(!(getPanel().getFriendlyBase() instanceof Cave)) {
        	
        	int divisor = 1;
        	
        	if(getPanel().getFriendlyBase() instanceof Fortress) {
        		divisor = Bases.FRIENDLY_FORTRESS.getHealth()/Bases.FRIENDLY_CAVE.getHealth();
        		
        		if(isFriendly) {
        			health = Bases.FRIENDLY_FORTRESS.getHealth();
        		}
        		
        		
        	}
      
        	
        	if(isFriendly) {
        		setLifeBarWidthFriend(maxHealth/divisor); 
        	}
        	
        }
        
        if(!(getPanel().getEnemyBase() instanceof Cave)) {
        	int divisor = 1;
        	
        	if(getPanel().getEnemyBase() instanceof Fortress) {
        		divisor = Bases.ENEMY_FORTRESS.getHealth()/Bases.ENEMY_CAVE.getHealth();
        		
        		if(!isFriendly) {
        			health = Bases.ENEMY_FORTRESS.getHealth();
        		}
        	}
        	
        	if(!isFriendly) {
        		setLifeBarWidthEnemy(maxHealth/divisor); 
        	}
        }
        
        int lifeBarWidth=0;
        if(isFriendly) {
        	lifeBarWidth = (getLifeBarWidthFriend()  * health) / maxHealth;
        }else {
        	lifeBarWidth = (getLifeBarWidthEnemy() * health) / maxHealth;
        }
   
      
        //g.setColor(isFriendly ? Color.GREEN : Color.RED);
        int x = getLifeBarX();
        int y = isFriendly ? getLifeBarY() : getLifeBarY()  + getLifeBarHeight()  + 10;
        g.fillRect(x, y, lifeBarWidth, getLifeBarHeight());
        //g.setColor(Color.BLACK);
        g.drawRect(x-1, y-1, lifeBarWidth+1, getLifeBarHeight()+1);
     
    }

}
