package entities;

import java.util.LinkedList;

public abstract class Base extends Entity{
	
	private LinkedList<Tower> towers;


	public Base(int xPos, int yPos, int width, int height, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		towers = new LinkedList<>();
	}
	
	public void addTower(Tower tower) {
		towers.add(tower);
	}
	
	

}
