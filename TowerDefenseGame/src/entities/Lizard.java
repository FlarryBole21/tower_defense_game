package entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.GamePanel;
import utils.Path;

public class Lizard extends LivingBeing{
	//private static final Logger LOGGER = Logger.getLogger(Lizard.class.getName());
	private int battleCount = 0;
	private int walkingCount = 0;
	private int battleCount2 = 0;
	private int walkingCount2 = 0;
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
        this.battleCount = 0;
        this.walkingCount = 0;
        super.setDeathAnimationFrameCount(0);
        super.setDeathAnimationChanged(false);
        super.setDead(false); 
    }
	
	
	
	@Override
	public void update(GamePanel panel) {
		
		Dimension screenSize = panel.getScreenSize();
		int baseWidth = panel.getBaseWidth();
		int bound = 1000;
		
		if ((super.getRect().getX() < bound && friendly) 
				|| (super.getRect().getX() > screenSize.width-baseWidth-bound && !friendly)) {
			
			
			if ((walkingCount < 100 && friendly) || (walkingCount2 < 100 && !friendly)) {
				if(friendly) {
					super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
					
				}else {
					super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
				}
	            super.loadImage();
				move();
				walkingCount++;
				walkingCount2++;

			}else {
				
				if(friendly) {
					super.setPathImage(Path.IMAGE_LIZARD_PLAYER_ATTACK.getName());
					
				}else {
					super.setPathImage(Path.IMAGE_LIZARD_ENEMY_ATTACK.getName());
				}
				
	            super.loadImage();
	            battleCount++;
	            battleCount2++;
	            
	            if (battleCount >= 100) {
	            	walkingCount=0;
	            	battleCount=0;
	            }
	            
	            
	            if (battleCount2 >= 100) {
	            	walkingCount2=0;
	            	battleCount2=0;
	            }
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
	
	private void move() {
		if(friendly) {
			super.getRect().setX(super.getRect().getX()+super.getSpeed());
		}else {
			super.getRect().setX(super.getRect().getX()-super.getSpeed());
		}
	
	}
	

}
