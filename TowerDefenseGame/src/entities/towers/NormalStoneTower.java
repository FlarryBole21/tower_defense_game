package entities.towers;

import utils.Path;

public class NormalStoneTower extends StoneTower{

	private static final long serialVersionUID = 1L;

	public NormalStoneTower(int xPos, int yPos, int width, int height, int health,int rangeShot,int spawnDelay) {
		super(xPos, yPos, width, height, health,rangeShot, spawnDelay);
		setPathImage(Path.IMAGE_CAVE_TOWER_01.getName());
		loadImage();
	}

}
