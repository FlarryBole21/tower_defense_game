package entities.bases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ui.GamePanel;
import utils.Path;

public class Cave extends Base{
	
	private static final long serialVersionUID = 1L;

	public Cave(int xPos, int yPos, int width, int height, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		if(friendly) {
			super.setPathImage(Path.IMAGE_CAVE_PLAYER.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_CAVE_ENEMY.getName());
		}
		super.loadImage();
	}
	
	@Override
	public void update(GamePanel panel) {
		
		
	}


}
