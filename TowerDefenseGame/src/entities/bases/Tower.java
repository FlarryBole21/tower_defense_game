package entities.bases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Entity;
import ui.GamePanel;
import utils.Path;

public class Tower extends Entity{
	private Image image;
	private Weapon weapon; 
	
	public Tower(int xPos, int yPos, int width, int height, int health, boolean friendly) {
		super(xPos, yPos, width, height, health, friendly);
		loadImage();
	}
	
	 private void loadImage() {
	        try {
	        	URI uri = null;
	        	if(super.isFriendly()) {
	        		uri = new URI(Path.IMAGE_CAVE_TOWER_PLAYER.getName());
	        	}else {
	        		uri = new URI(Path.IMAGE_CAVE_TOWER_ENEMY.getName());
		           
	        	}
	            image = ImageIO.read(uri.toURL());
	        } catch (IOException | URISyntaxException e) {
	            e.printStackTrace();
	        }
	    }
	
	public void addWeapon(Weapon weapon) {
		this.weapon=weapon;
	}

	public Weapon getWeapons() {
		return weapon;
	}


	@Override
	public void update(GamePanel panel) {
		//System.out.println("Update");
		
	}

	@Override
	public void draw(Graphics g) {
		if (image != null) {
            g.drawImage(image, super.getRect().getX(), super.getRect().getY(), null);
        }
	}


}
