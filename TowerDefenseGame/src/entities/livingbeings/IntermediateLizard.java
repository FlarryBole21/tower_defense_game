package entities.livingbeings;

import utils.Path;

public class IntermediateLizard extends Lizard{

	private static final long serialVersionUID = 1L;

	public IntermediateLizard(int xPos, int yPos, int width, int height, int attack, int health, boolean friendly) {
		super(xPos, yPos, width, height, attack, health, friendly);
		if(friendly) {
		    super.setWalkingPath(Path.IMAGE_INTERMEDIATE_LIZARD_PLAYER_WALK);
		    super.setWaitingPath(Path.IMAGE_INTERMEDIATE_LIZARD_PLAYER_WAIT);
		    super.setAttackingPath(Path.IMAGE_INTERMEDIATE_LIZARD_PLAYER_ATTACK);
		    super.setDeathPath(Path.IMAGE_INTERMEDIATE_LIZARD_PLAYER_DEATH);
		    super.resetState(Beings.FRIENDLY_INTERMEDIATE_LIZARD); 
		    super.setPathImage(Path.IMAGE_INTERMEDIATE_LIZARD_PLAYER_WALK.getName());
		} else {
		    super.setWalkingPath(Path.IMAGE_INTERMEDIATE_LIZARD_ENEMY_WALK);
		    super.setWaitingPath(Path.IMAGE_INTERMEDIATE_LIZARD_ENEMY_WAIT);
		    super.setAttackingPath(Path.IMAGE_INTERMEDIATE_LIZARD_ENEMY_ATTACK);
		    super.setDeathPath(Path.IMAGE_INTERMEDIATE_LIZARD_ENEMY_DEATH);
		    super.resetState(Beings.ENEMY_INTERMEDIATE_LIZARD); 
		    super.setPathImage(Path.IMAGE_INTERMEDIATE_LIZARD_ENEMY_WALK.getName());
		}

		super.loadImage();
	}

}
