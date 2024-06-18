package ui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utils.Path;

public class BackgroundPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

    // Constructor to load the image
    public BackgroundPanel(Path path) {
    	super(new FlowLayout(FlowLayout.CENTER));
    	try {
            
            File file = new File(path.getName());
            backgroundImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), 800, this);
        }
    }

}
