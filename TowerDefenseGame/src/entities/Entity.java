package entities;

import java.awt.Color;

import utils.Clearable;
import utils.Drawable;
import utils.Rectangle;
import utils.Updateable;

public abstract class Entity implements Updateable, Drawable, Clearable {
	
	private Rectangle rect; 
	private int health;
	private boolean friendly;
	
	public Entity(int xPos, int yPos, int width, int height, int health, boolean friendly,Color color) {
		rect = new Rectangle(xPos,yPos,width,height,color);
		this.health = health;
		this.friendly=friendly;
	}
	
	
	public Entity(int xPos, int yPos, int width, int height, int health, boolean friendly) {
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


	public int getHealth() {
		return health;
	}
	
	
	public void removeHealth(int health) {
		this.health -= health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isFriendly() {
		return friendly;
	}

	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}
	
	
	
}
