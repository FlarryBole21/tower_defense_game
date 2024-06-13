package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Base;
import entities.Bases;
import entities.Beings;
import entities.Cave;
import entities.LivingBeing;
import entities.Lizard;
import entities.Tower;
import entities.spawner.LizardSpawner;
import entities.spawner.Spawner;
import utils.Path;

public class GamePanel extends JPanel implements ActionListener {

	public final static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize(); 
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private LinkedList<Base> bases;
    private LinkedList<LivingBeing> friendlyLivingBeings;
    private LinkedList<LivingBeing> enemyLivingBeings;
    private LinkedList<LivingBeing> friendlyNewBeings;
    private LinkedList<LivingBeing> enemyNewBeings;
    private LinkedList<LivingBeing> friendlyWaitingBeings;
    private LinkedList<LivingBeing> enemyWaitingBeings;
    private Image backgroundImage;
    private int baseWidth;
    private int baseHeight;
    private int towerWidth;
    private int towerHeight;
    private LinkedList<Tower>towersPlayer; 
    private LinkedList<Tower>towersEnemy; 
    private Spawner friendlySpawner;
    private Spawner enemySpawner;
    
    {
    	bases = new LinkedList<>();
    	friendlyLivingBeings = new LinkedList<>();
    	enemyLivingBeings = new LinkedList<>();
    	friendlyNewBeings = new LinkedList<>();
    	enemyNewBeings = new LinkedList<>();
        friendlyWaitingBeings = new LinkedList<>();
        enemyWaitingBeings = new LinkedList<>();
        towersPlayer = new LinkedList<>();
        towersEnemy = new LinkedList<>();
  
    }
    
    public GamePanel(int baseWidth,int baseHeight,int towerWidth,int towerHeight) {
    	this.baseWidth=baseWidth;
    	this.baseHeight=baseHeight;
    	this.towerHeight=towerHeight;
    	this.towerWidth=towerWidth;
    	this.setPreferredSize(SCREENSIZE);
        loadBackgroundImage();
        startConfig();
        loadBeings();
        timer = new Timer(1000 / 60, this);
        timer.start();
        friendlySpawner = new LizardSpawner(1000,this,true);
        friendlySpawner.startSpawning();
        enemySpawner = new LizardSpawner(1000,this,false);
        enemySpawner.startSpawning();
    }
    
    
    private void startConfig() {
//    	towersPlayer.add(new Tower(50,this.screenSize.height-(towerHeight+baseHeight),towerWidth,
//        		towerHeight,1000,true));
//        towersEnemy.add(new Tower(this.screenSize.width-towerWidth,this.screenSize.height-
//        		(towerHeight+baseHeight),towerWidth,towerHeight,
//        		1000,true));
        addBaseBases(Bases.FRIENDLY_CAVE.getBase(),towersPlayer);
        addBaseBases(Bases.ENEMY_CAVE.getBase(),towersEnemy);
        System.out.println(SCREENSIZE);
	
    }
    
    public void addBaseBases(Base base, LinkedList<Tower> towers) {
    	bases.add(base);
    	for(Tower tower: towers) {
        	base.addTower(tower);
        }

    }
    
    

	public int getBaseWidth() {
		return baseWidth;
	}


	public int getBaseHeight() {
		return baseHeight;
	}


	public LinkedList<LivingBeing> getFriendlyLivingBeings() {
		return friendlyLivingBeings;
	}


	public LinkedList<LivingBeing> getEnemyLivingBeings() {
		return enemyLivingBeings;
	}


	public LinkedList<LivingBeing> getFriendlyNewBeings() {
		return friendlyNewBeings;
	}


	public LinkedList<LivingBeing> getEnemyNewBeings() {
		return enemyNewBeings;
	}


	public LinkedList<LivingBeing> getFriendlyWaitingBeings() {
		return friendlyWaitingBeings;
	}


	public void addFriendlyWaitingBeings(LivingBeing waitingBeing) {
		if(waitingBeing.isFriendly()) {
			this.friendlyWaitingBeings.add(waitingBeing);
		}
	}
	
	
	public LinkedList<LivingBeing> getEnemyWaitingBeings() {
		return enemyWaitingBeings;
	}


	public void addEnemyWaitingBeings(LivingBeing waitingBeing) {
		if(waitingBeing.isFriendly()== false) {
			this.enemyWaitingBeings.add(waitingBeing);
		}
	
	}


	private void loadBeings() {
        // Example to add a friendly and an enemy lizard
    	friendlyLivingBeings.add(Beings.FRIENDLY_LIZARD.getBeing());
    	enemyLivingBeings.add(Beings.ENEMY_LIZARD.getBeing());
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
            base.update(this);
            LinkedList<Tower> towers = base.getTowers();
            for(Tower tower: towers) {
            	tower.update(this);
            }
        }
        
        
        
        Iterator<LivingBeing> iterator = friendlyLivingBeings.iterator();
        while (iterator.hasNext()) {
            LivingBeing being = iterator.next();
            being.update(this);
            
            if (being.isDead()) {
                
                iterator.remove();
                break;
                
              
                
            }
        }
        
        Iterator<LivingBeing> iterator2 = enemyLivingBeings.iterator();
        while (iterator2.hasNext()) {
            LivingBeing being = iterator2.next();
            being.update(this);
            
            if (being.isDead()) {
                
                iterator2.remove();
                break;
                
              
                
            }
        }
        
        friendlyLivingBeings.addAll(friendlyNewBeings);
        
        friendlyNewBeings.clear();
        
        enemyLivingBeings.addAll(enemyNewBeings);
        
        enemyNewBeings.clear();
        
        
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

        for (LivingBeing being : friendlyLivingBeings) {
            being.draw(g);
        }
        
        for (LivingBeing being : enemyLivingBeings) {
            being.draw(g);
        }
    }
	
}
