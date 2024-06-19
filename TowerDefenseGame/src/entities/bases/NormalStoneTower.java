package entities.bases;

import utils.Path;

public class NormalStoneTower extends StoneTower{

	private static final long serialVersionUID = 1L;

	public NormalStoneTower(int xPos, int yPos, int width, int height, int health, boolean friendly) {
		super(xPos, yPos, width, height, health, friendly);
		setPathImage(Path.IMAGE_CAVE_TOWER_02_PLAYER.getName());
		loadImage();
	}

}
