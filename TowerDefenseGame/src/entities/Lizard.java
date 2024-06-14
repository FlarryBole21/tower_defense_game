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

import audio.AudioPlayer;
import game.Main;
import ui.GamePanel;
import utils.Path;

public class Lizard extends LivingBeing{

	
	public Lizard(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width,height, attack, health,friendly);
		super.setAttackingDistance(20);
		super.setWaitingDistance(110);
		super.addSpeed(2);
		super.setFrameDelay(100);
		super.setTotalDeathFrames(6);
		super.setAttackingAudio(Main.LIZARD_ATTACK_PLAYER);
		if(friendly) {
			super.setWalkingPath(Path.IMAGE_LIZARD_PLAYER_WALK);
			super.setWaitingPath(Path.IMAGE_LIZARD_PLAYER_WAIT);
			super.setAttackingPath(Path.IMAGE_LIZARD_PLAYER_ATTACK);
			super.setDeathPath(Path.IMAGE_LIZARD_PLAYER_DEATH);
			super.resetState(Beings.FRIENDLY_LIZARD); 
			super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
			
			
		}else {
			super.setWalkingPath(Path.IMAGE_LIZARD_ENEMY_WALK);
			super.setWaitingPath(Path.IMAGE_LIZARD_ENEMY_WAIT);
			super.setAttackingPath(Path.IMAGE_LIZARD_ENEMY_ATTACK);
			super.setDeathPath(Path.IMAGE_LIZARD_ENEMY_DEATH);
			super.resetState(Beings.ENEMY_LIZARD); 
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
		}
		super.loadImage();
	}
	

	
	@Override
	public void update(GamePanel panel) {
		
		if(getPanel() == null) {
			setPanel(panel);
		}
		
		if (this.getHealth() > 0) {
			if(isFriendly()) {
				living();
			}else {
				living();
			}
			
        } else if (!super.isDeathAnimationChanged()) {
        	if(isFriendly()) {
        		deathStart();
        	}else {
        		deathStart();
        	}
        	
        } else {
        	death();
        	
        }

	}
	
	
	

}
