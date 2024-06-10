package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Base;
import entities.Cave;
import entities.LivingBeing;
import entities.Tower;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private LinkedList<Base> bases;
    private LinkedList<LivingBeing> livingBeings;
    private Dimension screenSize; 
    private Image backgroundImage;
    
    public GamePanel(Dimension screenSize,int baseWidth,int baseHeight,int towerWidth,int towerHeight) {
    	this.screenSize=screenSize;
    	this.setPreferredSize(this.screenSize);
        bases = new LinkedList<>();
        livingBeings = new LinkedList<>();
        LinkedList<Tower> towersA = new LinkedList<>();
        LinkedList<Tower> towersB = new LinkedList<>();
        loadBackgroundImage();
        towersA.add(new Tower(50,this.screenSize.height-(towerHeight+baseHeight),towerWidth,
        		towerHeight,1000,true));
        towersB.add(new Tower(this.screenSize.width-towerWidth,this.screenSize.height-
        		(towerHeight+baseHeight),towerWidth,towerHeight,
        		1000,true,Color.PINK));
        addBaseBases(new Cave(0,this.screenSize.height-baseHeight,baseWidth,baseHeight,1000,true),
        		towersA);
        addBaseBases(new Cave(this.screenSize.width-baseWidth,this.screenSize.height-baseHeight,baseWidth,baseHeight
        		,1000,false,Color.RED),towersB);
   
        timer = new Timer(1200, this);
        timer.start();
    }
    
    public void addBaseBases(Base base, LinkedList<Tower> towers) {
    	bases.add(base);
    	for(Tower tower: towers) {
        	base.addTower(tower);
        }

    }
    
    private void loadBackgroundImage() {
    	try {
        	URI uri = null;
        	
        	uri = new URI("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
	        			+ "tower_defense_game/TowerDefenseGame/img/background_01.png");
	           
        	
        	backgroundImage = ImageIO.read(uri.toURL());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    	
    }
    
    public void drawBackground(Graphics g) {
		if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 400, null);
        }
		//super.getRect().draw(g);
		
	}
    
  
	@Override
	public void actionPerformed(ActionEvent e) {
		updateGame(); 
        repaint(); 
		
	}
	
	
	private void updateGame() {
        for (Base base : bases) {
            base.update();
            LinkedList<Tower> towers = base.getTowers();
            for(Tower tower: towers) {
            	tower.update();
            }
        }
    }
	
	
	@Override
	public void paint( Graphics g ) {
		super.paint(g);

		drawBackground(g);
		
		for (Base base : bases) {
            base.draw(g);
            LinkedList<Tower> towers = base.getTowers();
            for(Tower tower: towers) {
            	tower.draw(g);
            }
        }
		
	}
	
}
