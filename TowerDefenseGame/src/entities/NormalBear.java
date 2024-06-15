package entities;

import game.Main;
import utils.Path;

public class NormalBear extends Bear{

	private static final long serialVersionUID = 1L;

	public NormalBear(int xPos, int yPos, int width, int height, int attack, int health, boolean friendly) {
		super(xPos, yPos, width, height, attack, health, friendly);
		if (friendly) {
		    super.setWalkingPath(Path.IMAGE_NORMAL_BEAR_PLAYER_WALK);
		    super.setWaitingPath(Path.IMAGE_NORMAL_BEAR_PLAYER_WAIT);
		    super.setAttackingPath(Path.IMAGE_NORMAL_BEAR_PLAYER_ATTACK);
		    super.setDeathPath(Path.IMAGE_NORMAL_BEAR_PLAYER_DEATH);
		    super.resetState(Beings.FRIENDLY_NORMAL_BEAR); 
		    super.setPathImage(Path.IMAGE_NORMAL_BEAR_PLAYER_WALK.getName());
		} else {
		    super.setWalkingPath(Path.IMAGE_NORMAL_BEAR_ENEMY_WALK);
		    super.setWaitingPath(Path.IMAGE_NORMAL_BEAR_ENEMY_WAIT);
		    super.setAttackingPath(Path.IMAGE_NORMAL_BEAR_ENEMY_ATTACK);
		    super.setDeathPath(Path.IMAGE_NORMAL_BEAR_ENEMY_DEATH);
		    super.resetState(Beings.ENEMY_NORMAL_BEAR); 
		    super.setPathImage(Path.IMAGE_NORMAL_BEAR_ENEMY_WALK.getName());
		}

		super.loadImage();
		
	}

}
