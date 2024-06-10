package entities;

import java.awt.Graphics;
import java.util.LinkedList;

public class Tower extends Entity{
	
	private LinkedList<Weapon> weapons; 

	public Tower(int xPos, int yPos, int width, int height, int health, boolean friendly) {
		super(xPos, yPos, width, height, health, friendly);
	}
	
	public void addWeapon(Weapon weapon) {
		weapons.add(weapon);
	}


	@Override
	public void update() {
		System.out.println("Update");
		
	}

	@Override
	public void draw(Graphics g) {
		super.getRect().draw(g);
		
	}

	@Override
	public void clear(Graphics g) {
		super.getRect().clear(g);
		
	}
	
	
	

}
