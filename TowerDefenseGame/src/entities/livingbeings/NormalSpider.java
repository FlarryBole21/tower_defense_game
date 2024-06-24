package entities.livingbeings;

import game.Main;
import utils.Path;

public class NormalSpider extends Spider{

	private static final long serialVersionUID = 1L;

	public NormalSpider(int xPos, int yPos, int width, int height, int attack, double health, boolean friendly) {
		super(xPos, yPos, width, height, attack, health, friendly);
		if (friendly) {
		    super.setWalkingPath(Path.IMAGE_NORMAL_SPIDER_PLAYER_WALK);
		    super.setWaitingPath(Path.IMAGE_NORMAL_SPIDER_PLAYER_WAIT);
		    super.setAttackingPath(Path.IMAGE_NORMAL_SPIDER_PLAYER_ATTACK);
		    super.setDeathPath(Path.IMAGE_NORMAL_SPIDER_PLAYER_DEATH);
		    super.resetState(Beings.FRIENDLY_NORMAL_SPIDER); 
		    super.setPathImage(Path.IMAGE_NORMAL_SPIDER_PLAYER_WALK.getName());
		} else {
		    super.setWalkingPath(Path.IMAGE_NORMAL_SPIDER_ENEMY_WALK);
		    super.setWaitingPath(Path.IMAGE_NORMAL_SPIDER_ENEMY_WAIT);
		    super.setAttackingPath(Path.IMAGE_NORMAL_SPIDER_ENEMY_ATTACK);
		    super.setDeathPath(Path.IMAGE_NORMAL_SPIDER_ENEMY_DEATH);
		    super.resetState(Beings.ENEMY_NORMAL_SPIDER); 
		    super.setPathImage(Path.IMAGE_NORMAL_SPIDER_ENEMY_WALK.getName());
		}


		super.loadImage();
		
	}

}
