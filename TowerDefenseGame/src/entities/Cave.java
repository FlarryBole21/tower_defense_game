package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import utils.Path;

public class Cave extends Base{
	private Image image;


	public Cave(int xPos, int yPos, int width, int height, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		loadImage();
	}
	
	 private void loadImage() {
	        try {
	        	URI uri = null;
	        	if(super.isFriendly()) {
	        		uri = new URI(Path.IMAGE_CAVE_PLAYER.getName());
	        	}else {
	        		uri = new URI(Path.IMAGE_CAVE_ENEMY.getName());
		           
	        	}
	            image = ImageIO.read(uri.toURL());
	        } catch (IOException | URISyntaxException e) {
	            e.printStackTrace();
	        }
	    }

	@Override
	public void update() {
		//System.out.println("Update");
		
	}

	@Override
	public void draw(Graphics g) {
		if (image != null) {
            g.drawImage(image, super.getRect().getX(), super.getRect().getY(), null);
        }
	}

	@Override
	public void clear(Graphics g) {
		super.getRect().clear(g);
		
	}

}
