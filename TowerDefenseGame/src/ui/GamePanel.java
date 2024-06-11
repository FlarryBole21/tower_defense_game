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
import entities.Lizard;
import entities.Tower;
import utils.Path;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private LinkedList<Base> bases;
    private LinkedList<LivingBeing> livingBeings;
    private Dimension screenSize; 
    private Image backgroundImage;
    private int baseWidth;
    private int baseHeight;
    private int towerWidth;
    private int towerHeight;
    private LinkedList<Tower>towersPlayer; 
    private LinkedList<Tower>towersEnemy; 
    
    {
    	bases = new LinkedList<>();
        livingBeings = new LinkedList<>();
        towersPlayer = new LinkedList<>();
        towersEnemy = new LinkedList<>();
  
    }
    
    public GamePanel(Dimension screenSize,int baseWidth,int baseHeight,int towerWidth,int towerHeight) {
    	this.screenSize=screenSize;
    	this.baseWidth=baseWidth;
    	this.baseHeight=baseHeight;
    	this.towerHeight=towerHeight;
    	this.towerWidth=towerWidth;
    	this.setPreferredSize(this.screenSize);
        loadBackgroundImage();
        startConfig();
        loadBeings();
        timer = new Timer(1000 / 60, this);
        timer.start();
    }
    
    
    private void startConfig() {
//    	towersPlayer.add(new Tower(50,this.screenSize.height-(towerHeight+baseHeight),towerWidth,
//        		towerHeight,1000,true));
//        towersEnemy.add(new Tower(this.screenSize.width-towerWidth,this.screenSize.height-
//        		(towerHeight+baseHeight),towerWidth,towerHeight,
//        		1000,true));
        addBaseBases(new Cave(0,this.screenSize.height-baseHeight,baseWidth,baseHeight,1000,true),
        		towersPlayer);
        addBaseBases(new Cave(this.screenSize.width-baseWidth,this.screenSize.height-baseHeight,baseWidth,baseHeight
        		,1000,false),towersEnemy);
	
    }
    
    public void addBaseBases(Base base, LinkedList<Tower> towers) {
    	bases.add(base);
    	for(Tower tower: towers) {
        	base.addTower(tower);
        }

    }
    
    
    private void loadBeings() {
        // Example to add a friendly and an enemy lizard
    	livingBeings.add(new Lizard(100, 550, 100, 100, 10, 100, true));
    	//livingBeings.add(new Lizard(200, 550, 100, 100, 10, 100, false));
    }
    
    
    
    private void loadBackgroundImage() {
    	try {
        	URI uri = null;
    
        	uri = new URI(Path.IMAGE_BACKGROUND_01.getName());
	           
        	
        	backgroundImage = ImageIO.read(uri.toURL());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    	
    }
    
    public void drawBackground(Graphics g) {
    	if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null); 
        }
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
        
        for (LivingBeing being : livingBeings) {
            being.update();
        }
        
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        
        for (Base base : bases) {
            base.draw(g);
            LinkedList<Tower> towers = base.getTowers();
            for (Tower tower : towers) {
                tower.draw(g);
            }
        }

        for (LivingBeing being : livingBeings) {
        	if(being.getRect().getX() < 1000) {
        		being.draw(g);
        	}else {
        		being.clear(g);
        		livingBeings.remove(being);
        	}
            
        }
    }
	
}
