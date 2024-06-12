package entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.GamePanel;
import utils.Path;

public class Lizard extends LivingBeing{
	//private static final Logger LOGGER = Logger.getLogger(Lizard.class.getName());
	private boolean friendly;
	
	public Lizard(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width,height, attack, health,friendly);
		this.friendly=friendly;
		super.addSpeed(2);
		super.setFrameDelay(100);
		super.setTotalDeathFrames(6);
		resetState(); 
		if(friendly) {
			super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
		}
		super.loadImage();
		//LOGGER.info("Lizard created: " + this);
	}
	
	public void resetState() {
        if(friendly && Beings.FRIENDLY_LIZARD != null) {
        	super.getRect().setX(Beings.FRIENDLY_LIZARD.getxPos());
        	super.getRect().setY(Beings.FRIENDLY_LIZARD.getyPos());
        	
        }else if(!friendly && Beings.ENEMY_LIZARD != null){
        	super.getRect().setX(Beings.ENEMY_LIZARD.getxPos());
        	super.getRect().setY(Beings.ENEMY_LIZARD.getyPos());
        }
        
        super.setDeathAnimationFrameCount(0);
        super.setDeathAnimationChanged(false);
        super.setDead(false); 
    }
	
	
	
	@Override
	public void update(GamePanel panel) {
		

		if (this.getHealth() > 0) {

			living(panel);

        } else if (!super.isDeathAnimationChanged()) {
        	if(friendly) {
        		super.setPathImage(Path.IMAGE_LIZARD_PLAYER_DEATH.getName());
    			
    		}else {
    			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_DEATH.getName());
    		}
           
            super.loadImage();
            super.setDeathAnimationChanged(true);
          
        } else {
        	super.addDeathAnimationFrameCount(1);
        	
        	int framesPerSecond = 60;
        	int framesForDeathAnimation = super.getTotalDeathFrames() * (framesPerSecond / (1000 / super.getFrameDelay()));
        	
            
        	if (super.getDeathAnimationFrameCount() >= framesForDeathAnimation) {
        		
                super.setDead(true);
                if(friendly && panel.getFriendlyWaitingBeings().contains(this)) {
                	panel.getFriendlyWaitingBeings().remove(this);
                }
                
                if(!friendly && panel.getEnemyWaitingBeings().contains(this)) {
                	panel.getEnemyWaitingBeings().remove(this);
                }
                
                //LOGGER.info("Lizard is dead: " + this);
            }
        }
	
		
	}
	
	
	private void living(GamePanel panel) {
		
		System.out.println(panel.getFriendlyWaitingBeings());
     	System.out.println("Warten " + panel.getFriendlyWaitingBeings().size());
     	System.out.println("Leben " + panel.getLivingBeings().size());
     	System.out.println();
		
		
		if (this != panel.getLivingBeings().get(0) &&  this != panel.getLivingBeings().get(1)|| 
				panel.getLivingBeings().get(0).getRect().getX()+25 != panel.getLivingBeings().get(1).getRect().getX()-25) {
			if(friendly) {
				super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
				
			}else {
				super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
			}
            super.loadImage();
            
            if(this != panel.getLivingBeings().get(0) &&  this != panel.getLivingBeings().get(1) && 
            		panel.getLivingBeings().get(0).getRect().getX()+25 != panel.getLivingBeings().get(1).getRect().getX()-25
            		|| ((friendly && this != panel.getLivingBeings().get(0) && this.getRect().getX() < panel.getLivingBeings().get(0).getRect().getX()-100)
            				|| (!friendly && this != panel.getLivingBeings().get(1) && this.getRect().getX() 
            						> panel.getLivingBeings().get(1).getRect().getX()+100 ))) {
            	
            	if(panel.getFriendlyWaitingBeings().size() > 0) {
            		
            		boolean flag=false;
            		for(LivingBeing being: panel.getFriendlyWaitingBeings()) {
            			if(friendly && being.isFriendly() && this.getRect().getX()  >= being.getRect().getX()-100) {
            				flag=true;
            			}
            		}
            		
            		for(LivingBeing being: panel.getEnemyWaitingBeings()) { 
            			if(!friendly && !being.isFriendly() && this.getRect().getX()  <= being.getRect().getX()+100) {
            				flag=true;
            			}
            		}
            		
            		if( (friendly && !flag)||
	            			(!friendly && !flag)){
	            		
            			moveAndRemoveFromWaitingQueue(panel);	
	                 }else {
	                	 
	                	 //wait(panel); 
	                	 //moveAndRemoveFromWaitingQueue(panel);
	                	 
	                	 if(friendly && (getRect().getX() < panel.getFriendlyWaitingBeings().get(
	                					 panel.getFriendlyWaitingBeings().size()-1).getRect().getX()-100)
	                			 || this == panel.getFriendlyWaitingBeings().get(0)) {
	                		 moveAndRemoveFromWaitingQueue(panel);
	                	 }else if (!friendly && (getRect().getX() > panel.getEnemyWaitingBeings().get(
	                					 panel.getEnemyWaitingBeings().size()-1).getRect().getX()+100)
	                		 || this == panel.getEnemyWaitingBeings().get(0)){
	                		 moveAndRemoveFromWaitingQueue(panel);
	                	 }else {
	                		 
	                
	                		 wait(panel); 
	                	 }

	                 }
            	}else {
            
            		moveAndRemoveFromWaitingQueue(panel);
            		
            	}

            }else if (this == panel.getLivingBeings().get(0) ||  this == panel.getLivingBeings().get(1) &&
            		panel.getLivingBeings().get(0).getRect().getX()+25 != panel.getLivingBeings().get(1).getRect().getX()-25) {
            	
            	moveAndRemoveFromWaitingQueue(panel);
            	
            	
            }else if(this != panel.getLivingBeings().get(0) &&  this != panel.getLivingBeings().get(1)) {
            	wait(panel);
            	//System.out.println(this);
            	//System.out.println("Warten " + panel.getWaitingBeings().size());
            	//System.out.println("Leben " + panel.getLivingBeings().size());
            }

		}else {		
			attack(panel);
		}
	}
	
	
	private void attack(GamePanel panel) {
		if(friendly) {
			super.setPathImage(Path.IMAGE_LIZARD_PLAYER_ATTACK.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_ATTACK.getName());
		}
		
        super.loadImage();
        panel.getLivingBeings().get(0).removeHealth(1);
        panel.getLivingBeings().get(1).removeHealth(1);
		
	}
	
	private void wait(GamePanel panel) {
		if(this.isFriendly() && !panel.getFriendlyWaitingBeings().contains(this)) {
    		panel.addFriendlyWaitingBeings(this);
    	}
		
		if(!this.isFriendly() && !panel.getEnemyWaitingBeings().contains(this)) {
    		panel.addEnemyWaitingBeings(this);
    	}
    	
    	if(friendly) {
    		super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WAIT.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WAIT.getName());
		}
    	super.loadImage();
		
	}
	
	private void moveAndRemoveFromWaitingQueue(GamePanel panel) {
		move();
		if(friendly) {
    		super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
		}
		
		super.loadImage();
		
		if(this.isFriendly() && panel.getFriendlyWaitingBeings().contains(this)) {
			panel.getFriendlyWaitingBeings().remove(this);
    	}
		
		if(!this.isFriendly() && panel.getEnemyWaitingBeings().contains(this)) {
    		panel.getEnemyWaitingBeings().remove(this);
    	}
		
		
		//System.out.println("Warten " + panel.getWaitingBeings().size());
		
	}
	
	private void move() {
		if(friendly) {
			super.getRect().setX(super.getRect().getX()+super.getSpeed());
		}else {
			super.getRect().setX(super.getRect().getX()-super.getSpeed());
		}
	
	}
	

}
