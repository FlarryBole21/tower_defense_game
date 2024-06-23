package entities.livingbeings;

import utils.Path;

public class AdvancedLizard extends Lizard{

	private static final long serialVersionUID = 1L;

	public AdvancedLizard(int xPos, int yPos, int width, int height, int attack, double health, boolean friendly) {
		super(xPos, yPos, width, height, attack, health, friendly);
		if(friendly) {
		    super.setWalkingPath(Path.IMAGE_ADVANCED_LIZARD_PLAYER_WALK);
		    super.setWaitingPath(Path.IMAGE_ADVANCED_LIZARD_PLAYER_WAIT);
		    super.setAttackingPath(Path.IMAGE_ADVANCED_LIZARD_PLAYER_ATTACK);
		    super.setDeathPath(Path.IMAGE_ADVANCED_LIZARD_PLAYER_DEATH);
		    super.resetState(Beings.FRIENDLY_ADVANCED_LIZARD); 
		    super.setPathImage(Path.IMAGE_ADVANCED_LIZARD_PLAYER_WALK.getName());
		} else {
		    super.setWalkingPath(Path.IMAGE_ADVANCED_LIZARD_ENEMY_WALK);
		    super.setWaitingPath(Path.IMAGE_ADVANCED_LIZARD_ENEMY_WAIT);
		    super.setAttackingPath(Path.IMAGE_ADVANCED_LIZARD_ENEMY_ATTACK);
		    super.setDeathPath(Path.IMAGE_ADVANCED_LIZARD_ENEMY_DEATH);
		    super.resetState(Beings.ENEMY_ADVANCED_LIZARD); 
		    super.setPathImage(Path.IMAGE_ADVANCED_LIZARD_ENEMY_WALK.getName());
		}


		super.loadImage();
	}

}
