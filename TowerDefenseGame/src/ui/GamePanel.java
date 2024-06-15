package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import entities.bases.Base;
import entities.bases.Bases;
import entities.bases.Cave;
import entities.bases.Tower;
import entities.livingbeings.Beings;
import entities.livingbeings.LivingBeing;
import entities.livingbeings.Lizard;
import entities.spawner.BearSpawner;
import entities.spawner.LizardSpawner;
import entities.spawner.Spawner;
import game.WaveManager;
import utils.Path;

public class GamePanel extends JPanel implements ActionListener {

	public final static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize(); 
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private GamePanel panel;
	private Timer timer;
	//private int wave;
	//private java.util.Timer waveTimer;
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
    //private Spawner friendlySpawner;
    //private Spawner enemySpawner;
    private WaveManager waveManager;
    //private JLabel waveLabel; 
    
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
    
    public GamePanel(int baseWidth,int baseHeight,int towerWidth,int towerHeight,JFrame frame,JLabel waveLabel) {
    	this.frame=frame;
    	this.panel=this;
    	this.baseWidth=baseWidth;
    	this.baseHeight=baseHeight;
    	this.towerHeight=towerHeight;
    	this.towerWidth=towerWidth;
    	waveManager = new WaveManager(this,waveLabel);
    	waveManager.waveSpawning();
    	setLayout(new BorderLayout());
    	this.setPreferredSize(SCREENSIZE);
        timer = new Timer(1000 / 60, this);
        timer.start();
    }
    

    public WaveManager getWaveManager() {
		return waveManager;
	}


	public LinkedList<Tower> getTowersPlayer() {
		return towersPlayer;
	}


	public void setTowersPlayer(LinkedList<Tower> towersPlayer) {
		this.towersPlayer = towersPlayer;
	}


	public LinkedList<Tower> getTowersEnemy() {
		return towersEnemy;
	}


	public void setTowersEnemy(LinkedList<Tower> towersEnemy) {
		this.towersEnemy = towersEnemy;
	}


    public void addBases(Base base, LinkedList<Tower> towers) {
    	bases.add(base);
    	for(Tower tower: towers) {
        	base.addTower(tower);
        }

    }
    
    
    public void removeBaseBases() {
    	bases.clear();
    }
    
	public JFrame getFrame() {
		return frame;
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
    	friendlyLivingBeings.add(Beings.FRIENDLY_NORMAL_LIZARD.getBeing());
    	enemyLivingBeings.add(Beings.ENEMY_NORMAL_LIZARD.getBeing());
    	//livingBeings.add(new Lizard(200, 550, 100, 100, 10, 100, false));
    }
    
    
    
    public void loadBackgroundImage(Path path) {
    	try {
           
            File file = new File(path.getName());
            backgroundImage = ImageIO.read(file);
        } catch (IOException e) {
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
            	being.resetState(Beings.FRIENDLY_NORMAL_LIZARD);
                iterator.remove();
                break;
                
              
                
            }
        }
        
        Iterator<LivingBeing> iterator2 = enemyLivingBeings.iterator();
        while (iterator2.hasNext()) {
            LivingBeing being = iterator2.next();
            being.update(this);
            
            if (being.isDead()) {
            	being.resetState(Beings.ENEMY_NORMAL_LIZARD);
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
