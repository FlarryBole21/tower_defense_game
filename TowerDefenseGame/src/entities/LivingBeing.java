package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

public abstract class LivingBeing extends Entity{
	
	private int attack;
	private ImageIcon imageIcon;
	private String pathImage;
	private int speed; 
	private boolean dead;
	private boolean deathAnimationChanged;
    private int deathAnimationFrameCount;
    private int totalDeathFrames;  
    private int frameDelay; 
	
	public LivingBeing(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		this.attack=attack;
		this.deathAnimationChanged = false;
        this.deathAnimationFrameCount = 0;
        this.dead = false;
	
	}
	
	public int getSpeed() {
		return speed;
	}


	public void addSpeed(int speed) {
		this.speed += speed;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public String getPathImage() {
		return pathImage;
	}


	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }
	
	
	public void setTotalDeathFrames(int totalDeathFrames) {
        this.totalDeathFrames = totalDeathFrames;
    }
	

	public int getTotalDeathFrames() {
		return totalDeathFrames;
	}

	public int getFrameDelay() {
		return frameDelay;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isDeathAnimationChanged() {
		return deathAnimationChanged;
	}

	public void setDeathAnimationChanged(boolean deathAnimationChanged) {
		this.deathAnimationChanged = deathAnimationChanged;
	}

	public int getDeathAnimationFrameCount() {
		return deathAnimationFrameCount;
	}
	
	public void setDeathAnimationFrameCount(int deathAnimationFrameCount) {
		this.deathAnimationFrameCount = deathAnimationFrameCount;
	}

	public void addDeathAnimationFrameCount(int deathAnimationFrameCount) {
		this.deathAnimationFrameCount += deathAnimationFrameCount;
	}

	public void loadImage() {
	        try {
	        	URI uri = new URI(getPathImage());
	        	
	        	imageIcon = new ImageIcon(uri.toURL());
	        } catch (IOException | URISyntaxException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public void draw(Graphics g) {
		if (imageIcon != null) {
            imageIcon.paintIcon(null, g, getRect().getX(), getRect().getY());
        }
		
	}

	@Override
	public void clear(Graphics g) {
		g.setColor(new Color(0, 0, 0, 0)); 
	    g.fillRect(super.getRect().getX(), super.getRect().getY(), super.getRect().getWidth(), super.getRect().getHeight());
	}
	
	
	

}
