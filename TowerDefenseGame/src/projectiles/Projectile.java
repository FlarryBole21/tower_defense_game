package projectiles;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import entities.Entity;
import entities.livingbeings.Beings;
import entities.towers.Tower;
import game.GamePanel;
import utils.Path;
import utils.Rectangle;

public abstract class Projectile{

	private ImageIcon imageIcon;
	private String pathImage;
	private int speed;
	private Rectangle rect; 
	private Tower tower;
	private int attack;

	public Projectile(int xPos, int yPos, int width, int height,int attack, Tower tower) {
		rect = new Rectangle(xPos,yPos,width,height);
		this.tower=tower;
		this.attack=attack;
	}
	
	
	public void resetState(Projectiles projectile) {
        if(projectile != null) {
        	getRect().setX(projectile.getxPos());
        	getRect().setY(projectile.getyPos());
        }
    }
	
	
	public Rectangle getRect() {
		return rect;
	}
	
	

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}


	public Tower getTower() {
		return tower;
	}


	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	public void loadImage() {
		imageIcon = new ImageIcon(getPathImage());
	}
	
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public void addSpeed(int speed) {
		this.speed += speed;
	}

	public void update(GamePanel panel) {
	    if (getTower().isActive()) {
	        move();
	    }
	}


	public void draw(Graphics g) {
		if (imageIcon != null) {
            imageIcon.paintIcon(null, g, getRect().getX(), getRect().getY());
        }
		
	}
	
	private void move() {
		getRect().setY(getRect().getY()+getSpeed());
	}

}
