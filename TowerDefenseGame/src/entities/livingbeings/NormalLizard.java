package entities.livingbeings;

import game.Main;
import utils.Path;

public class NormalLizard extends Lizard{

	private static final long serialVersionUID = 1L;

	public NormalLizard(int xPos, int yPos, int width, int height, int attack, int health, boolean friendly) {
		super(xPos, yPos, width, height, attack, health, friendly);
		if(friendly) {
			super.setWalkingPath(Path.IMAGE_NORMAL_LIZARD_PLAYER_WALK);
			super.setWaitingPath(Path.IMAGE_NORMAL_LIZARD_PLAYER_WAIT);
			super.setAttackingPath(Path.IMAGE_NORMAL_LIZARD_PLAYER_ATTACK);
			super.setDeathPath(Path.IMAGE_NORMAL_LIZARD_PLAYER_DEATH);
			super.resetState(Beings.FRIENDLY_NORMAL_LIZARD); 
			super.setPathImage(Path.IMAGE_NORMAL_LIZARD_PLAYER_WALK.getName());
			
			
		}else {
			super.setWalkingPath(Path.IMAGE_NORMAL_LIZARD_ENEMY_WALK);
			super.setWaitingPath(Path.IMAGE_NORMAL_LIZARD_ENEMY_WAIT);
			super.setAttackingPath(Path.IMAGE_NORMAL_LIZARD_ENEMY_ATTACK);
			super.setDeathPath(Path.IMAGE_NORMAL_LIZARD_ENEMY_DEATH);
			super.resetState(Beings.ENEMY_NORMAL_LIZARD); 
			super.setPathImage(Path.IMAGE_NORMAL_LIZARD_ENEMY_WALK.getName());
		}
		super.loadImage();
		
	}

}
