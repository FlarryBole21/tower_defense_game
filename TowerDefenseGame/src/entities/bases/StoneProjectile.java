package entities.bases;

import utils.Path;

public class StoneProjectile extends Projectile{

	private static final long serialVersionUID = 1L;

	public StoneProjectile(int xPos, int yPos, int width, int height, int attack,int health, boolean friendly,Tower tower) {
		super(xPos, yPos, width, height, attack,health, friendly,tower);
		setPathImage(Path.IMAGE_CAVE_TOWER_ROCK_PLAYER.getName());
		setSpeed(10);
		loadImage();
	}

}
