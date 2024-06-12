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

			if (this != panel.getLivingBeings().get(0) &&  this != panel.getLivingBeings().get(1)|| 
					panel.getLivingBeings().get(0).getRect().getX() != panel.getLivingBeings().get(1).getRect().getX()) {
				if(friendly) {
					super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
					
				}else {
					super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
				}
	            super.loadImage();
	            
	            if(this != panel.getLivingBeings().get(0) &&  this != panel.getLivingBeings().get(1) && 
	            		panel.getLivingBeings().get(0).getRect().getX() != panel.getLivingBeings().get(1).getRect().getX()
	            		|| ((friendly && this != panel.getLivingBeings().get(0) && this.getRect().getX() < panel.getLivingBeings().get(0).getRect().getX()-100)
	            				|| (!friendly && this != panel.getLivingBeings().get(1) && this.getRect().getX() 
	            						> panel.getLivingBeings().get(1).getRect().getX()+100 ))) {
	            	
	            	if(panel.getWaitingBeings().size() > 0) {
	            		
	            		boolean flag=false;
	            		for(LivingBeing being: panel.getWaitingBeings()) {
	            			if(friendly && being.isFriendly() && this.getRect().getX()  >= being.getRect().getX()-100) {
	            				flag=true;
	            			}
	            			
	            			if(!friendly && !being.isFriendly() && this.getRect().getX()  <= being.getRect().getX()+100) {
	            				flag=true;
	            			}
	            		}
	            		
	            		if( (friendly && !flag)||
		            			(!friendly && !flag)){
		            		
	            			moveAndRemoveFromWaitingQueue(panel);		
		                 }
	            	}else {
	            
	            		moveAndRemoveFromWaitingQueue(panel);
	            		
	            	}

	            }else if (this == panel.getLivingBeings().get(0) ||  this == panel.getLivingBeings().get(1) &&
	            		panel.getLivingBeings().get(0).getRect().getX() != panel.getLivingBeings().get(1).getRect().getX()) {
	            	
	            	moveAndRemoveFromWaitingQueue(panel);
	            	
	            }else if(this != panel.getLivingBeings().get(0) &&  this != panel.getLivingBeings().get(1)) {
	            	if(!panel.getWaitingBeings().contains(this)) {
	            		panel.addWaitingBeings(this);
	            	}
	            	
	            	System.out.println(this);
	            	System.out.println(panel.getWaitingBeings().size());
	            	
	            }
	            
				

			}else {
				
				
				
				if(friendly) {
					super.setPathImage(Path.IMAGE_LIZARD_PLAYER_ATTACK.getName());
					
				}else {
					super.setPathImage(Path.IMAGE_LIZARD_ENEMY_ATTACK.getName());
				}
				
	            super.loadImage();
	            panel.getLivingBeings().get(0).removeHealth(1);
	            panel.getLivingBeings().get(1).removeHealth(1);

			}

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
                //LOGGER.info("Lizard is dead: " + this);
            }
        }
	
		
	}
	
	private void moveAndRemoveFromWaitingQueue(GamePanel panel) {
		move();
		Iterator<LivingBeing> iterator = panel.getWaitingBeings().iterator();
		while (iterator.hasNext()) {
            LivingBeing being = iterator.next();

            if (being.equals(this)) {
                
                iterator.remove();
                break; 
            }
       }
		
	}
	
	private void move() {
		if(friendly) {
			super.getRect().setX(super.getRect().getX()+super.getSpeed());
		}else {
			super.getRect().setX(super.getRect().getX()-super.getSpeed());
		}
	
	}
	

}
