package entities.towers;

import utils.Path;

public class NormalMagicTower extends MagicTower{

	private static final long serialVersionUID = 1L;

	public NormalMagicTower(int xPos, int yPos, int width, int height, int health, int rangeShot, int spawnDelay) {
		super(xPos, yPos, width, height, health, rangeShot, spawnDelay);
		setPathImage(Path.IMAGE_MAGIC_TOWER_01.getName());
		loadImage();
	}

}
