package entities;

import java.awt.Color;
import javax.swing.JFrame;

import utils.Rectangle;
import utils.interfaces.Drawable;
import utils.interfaces.Updateable;

public abstract class Entity extends JFrame implements Updateable, Drawable {
	
	private static final long serialVersionUID = 1L;
	private Rectangle rect; 
	private double health;
	private boolean friendly;
	
	
	public Entity(int xPos, int yPos, int width, int height, double health, boolean friendly) {
		rect = new Rectangle(xPos,yPos,width,height);
		this.health = health;
		this.friendly=friendly;
	}

	public Rectangle getRect() {
		return rect;
	}
	
	

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}


	public double getHealth() {
		return health;
	}
	
	
	public void removeHealth(double health) {
		this.health -= health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public boolean isFriendly() {
		return friendly;
	}

	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}
	
	
	
}
