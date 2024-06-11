package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import utils.Path;

public class Lizard extends LivingBeing{
	private ImageIcon imageIcon;
	private int speed = 2; 
	

	public Lizard(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width,height, attack, health,friendly);
		loadImage();
	}
	
	
	@Override
	public void update() {
	
        if (super.getRect().getX() < 1700) {
        	super.getRect().setX(super.getRect().getX()+speed);
        }
		
		
	}
	
	 private void loadImage() {
	        try {
	        	URI uri = null;
	        	if(super.isFriendly()) {
	        		uri = new URI(Path.IMAGE_LIZARD_PLAYER.getName());
	        	}else {
	        		uri = new URI(Path.IMAGE_LIZARD_PLAYER.getName());
		           
	        	}
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
		g.setColor(new Color(0, 0, 0, 0)); // Transparent color
	    g.fillRect(super.getRect().getX(), super.getRect().getY(), super.getRect().getWidth(), super.getRect().getHeight());
	}

	
}
