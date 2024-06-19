package entities.bases;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import entities.Entity;
import entities.livingbeings.Beings;
import game.GamePanel;
import utils.Path;

public abstract class Projectile extends Entity{

	private static final long serialVersionUID = 1L;
	private ImageIcon imageIcon;
	private String pathImage;
	private int speed;
	private Tower tower;
	private int attack;

	public Projectile(int xPos, int yPos, int width, int height,int attack, int health,boolean friendly,Tower tower) {
		super(xPos, yPos, width, height, health,friendly);
		this.tower=tower;
		this.attack=attack;
	}
	
	
	public void resetState(Projectiles projectile) {
        if(projectile != null) {
        	super.getRect().setX(projectile.getxPos());
        	super.getRect().setY(projectile.getyPos());
        	super.setHealth(projectile.getHealth());
        	
        }
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

	@Override
	public void update(GamePanel panel) {
		

		if(getTower().isActive()) {
			move();
		}
		
	}

	@Override
	public void draw(Graphics g) {
		if (imageIcon != null) {
            imageIcon.paintIcon(null, g, getRect().getX(), getRect().getY());
        }
		
	}
	
	private void move() {

		if(isFriendly()) {
			super.getRect().setY(super.getRect().getY()+getSpeed());
		}
	
	}

}
