package entities;

import java.awt.Color;
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

	public LinkedList<Tower> getTowers() {
		return towers;
	}

	public void setTowers(LinkedList<Tower> towers) {
		this.towers = towers;
	}
	
}
