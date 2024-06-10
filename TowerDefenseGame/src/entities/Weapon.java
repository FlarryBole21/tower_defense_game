package entities;

public abstract class Weapon extends Entity{

	public Weapon(int xPos, int yPos, int width, int height, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
	}

}
