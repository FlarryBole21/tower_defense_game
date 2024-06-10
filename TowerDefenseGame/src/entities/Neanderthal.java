package entities;

import java.awt.Graphics;

public class Neanderthal extends LivingBeing{

	public Neanderthal(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width, height, attack, health,friendly);
		// TODO Auto-generated constructor stub
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
