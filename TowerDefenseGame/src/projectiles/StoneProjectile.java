package projectiles;

import entities.towers.Tower;
import utils.Path;

public class StoneProjectile extends Projectile{

	private static final long serialVersionUID = 1L;

	public StoneProjectile(int xPos, int yPos, int width, int height, int attack,Tower tower) {
		super(xPos, yPos, width, height, attack,tower);
		setPathImage(Path.IMAGE_CAVE_TOWER_ROCK.getName());
		setSpeed(10);
		loadImage();
	}

}
