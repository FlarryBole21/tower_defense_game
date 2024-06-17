package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import entities.bases.Base;
import entities.bases.Bases;
import entities.bases.Cave;
import entities.bases.Tower;
import entities.livingbeings.Beings;
import entities.livingbeings.LivingBeing;
import entities.livingbeings.Lizard;
import game.Main;
import game.WaveManager;
import game.spawner.BearSpawner;
import game.spawner.LizardSpawner;
import game.spawner.Spawner;
import utils.Path;

public class GamePanel extends JPanel implements ActionListener {

	public final static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize(); 
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private GamePanel panel;
	private Timer timer;
	private Base friendlyBase;
	private Base enemyBase;
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
    private WaveManager waveManager;
    private boolean gameStart;
    private JLabel waveLabel; 
    private CardLayout layout;
    
    {
    	friendlyLivingBeings = new LinkedList<>();
    	enemyLivingBeings = new LinkedList<>();
    	friendlyNewBeings = new LinkedList<>();
    	enemyNewBeings = new LinkedList<>();
        friendlyWaitingBeings = new LinkedList<>();
        enemyWaitingBeings = new LinkedList<>();
        towersPlayer = new LinkedList<>();
        towersEnemy = new LinkedList<>();
  
    }
    
    public GamePanel(int baseWidth,int baseHeight,int towerWidth,int towerHeight,
    		JFrame frame,JLabel waveLabel,CardLayout layout) {
    	this.frame=frame;
    	this.panel=this;
    	this.baseWidth=baseWidth;
    	this.baseHeight=baseHeight;
    	this.towerHeight=towerHeight;
    	this.towerWidth=towerWidth;
    	this.waveLabel=waveLabel;
    	this.layout=layout;
    	setLayout(new BorderLayout());
    	this.setPreferredSize(SCREENSIZE);
    	
    	
    	
    }
    
    public void startConfig() {
    	waveManager = new WaveManager(this,waveLabel);
    	waveManager.startConfig();
    	if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000 / 60, this);
        timer.start();
    }
    

    public boolean isGameStart() {
		return gameStart;
	}


	public void setGameStart(boolean gameStart) {
		this.gameStart = gameStart;
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


//    public void addBases(Base base, LinkedList<Tower> towers) {
//    	bases.add(base);
//    	for(Tower tower: towers) {
//        	base.addTower(tower);
//        }
//
//    }
    
    public void setFriendlyBase(Base base, LinkedList<Tower> towers) {
    	friendlyBase=base;
    	for(Tower tower: towers) {
        	base.addTower(tower);
        }
    }
    
    
    public void setEnemyBase(Base base, LinkedList<Tower> towers) {
    	enemyBase=base;
    	for(Tower tower: towers) {
        	base.addTower(tower);
        }
    }
    
    
    public Base getFriendlyBase() {
    	return friendlyBase;
    }
    
    public Base getEnemyBase() {
    	return enemyBase;
    }
    
    public void removeBaseBases() {
    	friendlyBase=null;
    	enemyBase=null;
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
    
    
    public void resetGame() {
      
    	Main.stopAudio();
    	Main.BACKGROUND_PLAYER.resetFrame();
    	
    	friendlyLivingBeings.clear();
        enemyLivingBeings.clear();
        friendlyNewBeings.clear();
        enemyNewBeings.clear();
        friendlyWaitingBeings.clear();
        enemyWaitingBeings.clear();
        towersPlayer.clear();
        towersEnemy.clear();
        waveManager.reset();
        

        Bases.FRIENDLY_CAVE.getBase().setHealth(Bases.FRIENDLY_CAVE.getHealth());
        Bases.ENEMY_CAVE.getBase().setHealth(Bases.ENEMY_CAVE.getHealth());
        
        gameStart = false;
    }

    
  
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(friendlyBase.getHealth() > 0 && enemyBase.getHealth() > 0 && gameStart) {
			updateGame(); 
	        repaint(); 
		}else {
		    if(friendlyBase.getHealth() <= 0) {
		    	resetGame();
		    	layout.show(Main.MAINPANEL, "LosingPanel");
		    }else if(enemyBase.getHealth() <= 0) {
		    	resetGame();
		    	layout.show(Main.MAINPANEL, "WinningPanel");
		    }
		   
		}
	}
	
	
	private void updateGame() {
		
//		if(friendlyBase != null) {
//			friendlyBase.update(this);
//			LinkedList<Tower> towers = friendlyBase.getTowers();
//            for(Tower tower: towers) {
//            	tower.update(this);
//            }
//		}
//		
//		
//		if(enemyBase != null) {
//			enemyBase.update(this);
//			LinkedList<Tower> towers = enemyBase.getTowers();
//            for(Tower tower: towers) {
//            	tower.update(this);
//            }
//		}
      
        
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
        
        
        if(friendlyBase != null) {
			friendlyBase.draw(g);
			LinkedList<Tower> towers = friendlyBase.getTowers();
			for (Tower tower : towers) {
                tower.draw(g);
            }
		}
		
		
		if(enemyBase != null) {
			enemyBase.draw(g);
			LinkedList<Tower> towers = enemyBase.getTowers();
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
