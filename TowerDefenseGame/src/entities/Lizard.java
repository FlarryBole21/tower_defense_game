package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import utils.Path;

public class Lizard extends LivingBeing{
	

	
	public Lizard(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width,height, attack, health,friendly);
		super.addSpeed(2);
		super.setFrameDelay(100);
		super.setTotalDeathFrames(6);
		if(friendly) {
			super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
		}
		super.loadImage();
	}
	
	
	
	@Override
	public void update() {
		
		if (super.getRect().getX() < 200) {
            move();
        } else if (!super.isAnimationChanged()) {
            super.setPathImage(Path.IMAGE_LIZARD_PLAYER_DEATH.getName());
            super.loadImage();
            super.setAnimationChanged(true);
          
        } else {
        	super.addDeathAnimationFrameCount(1);
            
        	if (super.getDeathAnimationFrameCount() >= super.getTotalDeathFrames() * (super.getFrameDelay() / 1000.0) * 60) {
                super.setDead(true);
            }
        }
	
		
	}
	
	private void move() {
		super.getRect().setX(super.getRect().getX()+super.getSpeed());
	}
	

}
