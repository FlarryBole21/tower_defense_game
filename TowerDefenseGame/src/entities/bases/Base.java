package entities.bases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import entities.Entity;
import utils.Path;

public abstract class Base extends Entity{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private String pathImage;
	private LinkedList<Tower> towers;
	
	public Base(int xPos, int yPos, int width, int height, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		towers = new LinkedList<>();
	}
	
	
	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
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
	
	public void loadImage() {
		 try {
			   File file = null;
		       file = new File(getPathImage());
		       image = ImageIO.read(file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	@Override
	public void draw(Graphics g) {
		if (image != null) {
            g.drawImage(image, super.getRect().getX(), super.getRect().getY(), null);
        }
	}
	
	
}
