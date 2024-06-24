package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import audio.AudioPlayers;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import entities.bases.Base;
import entities.bases.Bases;
import entities.bases.Cave;
import entities.bases.Fortress;
import entities.livingbeings.Beings;
import entities.livingbeings.IntermediateLizard;
import entities.livingbeings.LivingBeing;
import entities.livingbeings.Lizard;
import entities.livingbeings.NormalLizard;
import entities.towers.MagicTower;
import entities.towers.Tower;
import game.spawner.SecondLineUpSpawner;
import game.spawner.FirstLineUpSpawner;
import game.spawner.Spawner;
import projectiles.Projectile;
import ui.BaseLifeBar;
import ui.BeingLifeBar;
import ui.ImageIconManager;
import utils.CoinValues;
import utils.Path;

public class GamePanel extends JPanel implements ActionListener {

	public final static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize(); 
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Timer timer;
	private Base friendlyBase;
	private Base enemyBase;
	private BaseLifeBar baseLifeBar;
	private BeingLifeBar beingLifeBar;
	//Alle lebenden freundlcihen Lebewesen --> auf unserer Seite
    private LinkedList<LivingBeing> friendlyLivingBeings;
    //Alle feindlichen Lebewesen --> auf der gegnerischen Seite
    private LinkedList<LivingBeing> enemyLivingBeings;
    //Temporäre Listen um Iterator-Fehler zu vermeiden
    private LinkedList<LivingBeing> friendlyNewBeings;
    private LinkedList<LivingBeing> enemyNewBeings;
    
    //Alle Lebewesen, die sich in einer Warteschlange befinden bzw. auf ihre vorderen Kollegen warten
    private LinkedList<LivingBeing> friendlyWaitingBeings;
    private LinkedList<LivingBeing> enemyWaitingBeings;
    
    //Reguliert alle Icons unten in der Anzeige
    private ImageIconManager imageIconManager;
    private Image backgroundImage;
    private int baseWidth;
    private int baseHeight;
    //Alle Türme --> nur der Spieler besitzt Türme
    private LinkedList<Tower>towers; 
    
    //Reguliert die Wellen
    private WaveManager waveManager;
    
    //Wird ausgeschaltet, wenn man verleirt oder gewinnt
    private boolean gameStart;
    private JLabel waveLabel; 
    private CardLayout layout;
    private int coins;

    {
    	friendlyLivingBeings = new LinkedList<>();
    	enemyLivingBeings = new LinkedList<>();
    	friendlyNewBeings = new LinkedList<>();
    	enemyNewBeings = new LinkedList<>();
        friendlyWaitingBeings = new LinkedList<>();
        enemyWaitingBeings = new LinkedList<>();
        towers = new LinkedList<>();
        //Währung des Spielers wird gesetzt auf den Standardwert
        coins=CoinValues.START_COIN_VALUE.getValue();
  
    }
    
    public GamePanel(int baseWidth,int baseHeight,
    		JFrame frame,CardLayout layout,JLabel waveLabel,LinkedList<JButton> imageIconButtons) {
    	this.frame=frame;
    	this.baseWidth=baseWidth;
    	this.baseHeight=baseHeight;
    	this.waveLabel=waveLabel;
    	this.layout=layout;
    	
    	//Setzen der Lebensbalken für Basen und Gegner
    	this.baseLifeBar=new BaseLifeBar(this,100,100,15,(GamePanel.SCREENSIZE.width/2)-500,10);
    	this.beingLifeBar=new BeingLifeBar(this,100,100,15,
    			(GamePanel.SCREENSIZE.width/2)-100,(GamePanel.SCREENSIZE.height/2)+(GamePanel.SCREENSIZE.height/4));
    	
    	this.imageIconManager=new ImageIconManager(this,imageIconButtons);
    	setLayout(new BorderLayout());
    	
    	this.setPreferredSize(SCREENSIZE);
    	imageIconManager.setImageIconButtonsEvents();
    	//setImageIconButtons();
    }
    

    public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
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
    
    
 
	public ImageIconManager getImageIconManager() {
		return imageIconManager;
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


	public LinkedList<Tower> getTowers() {
		return towers;
	}


	public void addTowers(Tower towersPlayer) {
		this.towers.add(towersPlayer);
	}


    public void setFriendlyBase(Base base) {
    	friendlyBase=base;
    }
    
    
    public void setEnemyBase(Base base) {
    	enemyBase=base;
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

    //Laden des Hintergrunds aus der File
    public void loadBackgroundImage(Path path) {
    	try {
           
            File file = new File(path.getName());
            backgroundImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    
    //Zeichnen des Hintergrunds
    public void drawBackground(Graphics g) {
    	if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null); 
        }
	}
    
    //Zurücksetzen des Spiels, alle Variablen werden zurückgesetzt auf den Ursprungswert
    //Timer werden gestoppt
    //Listen geleert etc.
    public void resetGame() throws IOException {
    	gameStart = false;
    	Main.stopAudio();
    	AudioPlayers.BACKGROUND_PLAYER.getPlayer().resetFrame();
    	
    	friendlyLivingBeings.clear();
        enemyLivingBeings.clear();
        friendlyNewBeings.clear();
        enemyNewBeings.clear();
        friendlyWaitingBeings.clear();
        enemyWaitingBeings.clear();
        
        for (Tower tower : towers) {
        	tower.resetTower();
            
        }
        
        towers.clear();
        
        coins=CoinValues.START_COIN_VALUE.getValue();
        waveManager.reset();
        
        for (Bases base : Bases.values()) {
        	
        	base.getBase().setHealth(base.getHealth());
           
        }
       
        imageIconManager.returnToOriginalLineUp();
       
    }
    

    //Wenn eine Aktion in der GUI ausgeführt wird
	@Override
	public void actionPerformed(ActionEvent e) {

		if(imageIconManager != null) {
			//Reset der Cooldowns für die Icons // Regulierung
			if(imageIconManager.getImageIconButtons().size() > 0) {
				imageIconManager.updateOnActionPerformed();
			}

		}
		
		//Spielm läuft nur, wenn das Leben der beiden Basen über null ist
		if(friendlyBase.getHealth() > 0 && enemyBase.getHealth() > 0 && gameStart && waveManager.getWave() <= waveManager.getWaveMax()) {
			updateGame(); 
	        repaint(); 
		}else {
			//Spieler hat verloren?
		    if(friendlyBase.getHealth() <= 0) {
		    	try {
					resetGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
		    	layout.show(Main.MAINPANEL, "LosingPanel");
		    //Gegner hat verloren?
		    }else if(enemyBase.getHealth() <= 0) {
		    	try {
					resetGame();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	layout.show(Main.MAINPANEL, "WinningPanel");
		    //Maximalwelle überschritten bzw. der Spieler hat überlebt?	
		    }else if(waveManager.getWave() > waveManager.getWaveMax()){
		    	
		    	try {
					resetGame();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	layout.show(Main.MAINPANEL, "WinningPanel2");

		    }
		   
		}
	}
	
	//Alle Türme, Basen und Lebewesen werden geupdatet
	private void updateGame() {
		
		if(friendlyBase != null) {
			friendlyBase.update(this);
            for(Tower tower: towers) {

            	if(enemyLivingBeings.size()>0) {
            		
            		if(enemyLivingBeings.get(0).getRect().getX() < tower.getRangeShot()) {
            			tower.update(this);
            		}
            		
            	
            	}else {
            		tower.getProjectiles().clear();
            	}

            }
		}
		
		//Alle Lebewesen werden überprüft wenn eins stirbt wird es aus der Liste entfernt
		//Listen werden aktualisiert
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
        
        //Alle Listen mit den Lebenwesen werden neugeschrieben
        friendlyLivingBeings.addAll(friendlyNewBeings);
        friendlyNewBeings.clear();
        enemyLivingBeings.addAll(enemyNewBeings);
        enemyNewBeings.clear();
    }
	

	//Alle Lebensbalken werden immer wieder neugezeichnet, alle Lebewesen, alle Türme usw.
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Hintergrund wird immer zuerst neugezeichnet
        drawBackground(g);
        
        //Neuzeichnen der Lebensbalken und dem darunter liegenden schwarzen Balken
        //Farbiger Balken zeigt aktuelles Leben an
        //Schwarzer Balken zeigt das verlorene Leben vom Urpsrungsleben an
        baseLifeBar.drawLifeBarBorder(g, true);
        baseLifeBar.drawLifeBarBorder(g, false); 
        baseLifeBar.drawLifeBar(g, friendlyBase.getHealth(), true);
        baseLifeBar.drawLifeBar(g, enemyBase.getHealth(), false);
        
        if(friendlyBase != null) {
			friendlyBase.draw(g);
			for (Tower tower : towers) {

                tower.draw(g);
                
                
                if(friendlyLivingBeings.size()>0) {
                	
                	//Wechseln der Turmanimationen
                	if(!tower.isAnimationChanged() && (tower instanceof MagicTower)) {
            			tower.setPathImage(Path.IMAGE_MAGIC_TOWER_02.getName());
                    	tower.loadImage();
                    	tower.setAnimationChanged(true);
            			
            		}
                	
                }else {
                	if(tower.isAnimationChanged() && (tower instanceof MagicTower)) {
            			tower.setPathImage(Path.IMAGE_MAGIC_TOWER_01.getName());
                    	tower.loadImage();
                    	tower.setAnimationChanged(false);
            			
            		}
                	
                }
                
                
                if(enemyLivingBeings.size()>0) {
                	
                	
                	
                	if(enemyLivingBeings.get(0).getRect().getX() < tower.getRangeShot()) {
                		
                		if(!tower.isAnimationChanged() && !(tower instanceof MagicTower)) {
                			tower.setPathImage(Path.IMAGE_CAVE_TOWER_02.getName());
                        	tower.loadImage();
                        	tower.setAnimationChanged(true);
                			
                		}
                		
                    	//tower.update(panel);
                    	
                		//Neuzeichnen der Projektile und wo sie hinfallen sollen
                		//Je nachdem ob der Gegner sich bewegt oder stehen bleibt
                    	Iterator<Projectile> iterator = tower.getProjectiles().iterator();
                        while (iterator.hasNext()) {
                        	Projectile projectile = iterator.next();
                        	
                        	
                        	int distance = 60;
                        	int realignSpawning = 200;
                        	int caveDistance = 90;
                        	
                        	if(enemyLivingBeings.get(0).isMovingState()) {
                        		distance = -30;
                        	}
                        	
                        	if(projectile.getRect().getY() < realignSpawning) {
                        		projectile.getRect().setX(enemyLivingBeings.get(0).getRect().getX()+distance);
                        	}
                        	projectile.update(this);
                        	projectile.draw(g);
                        	
                        	if(projectile.getRect().getY() >= enemyLivingBeings.get(0).getRect().getY()-200) {
                        		
                        	}
                        	
                        	if(projectile.getRect().getY() >= enemyLivingBeings.get(0).getRect().getY()) {

                        		
                        		if(projectile.getRect().getX() > enemyLivingBeings.get(0).getRect().getX() && 
                        				projectile.getRect().getX() < enemyLivingBeings.get(0).getRect().getX()+caveDistance) {
                        			
                        			
                        			
                        			enemyLivingBeings.get(0).setHealth(enemyLivingBeings.get(0).getHealth()-projectile.getAttack());
                        		}
                        		
                        		
                        		iterator.remove();
                                break;
                        	}

                        }

                	}else {
                		
                		if(tower.isAnimationChanged() && !(tower instanceof MagicTower)) {
                			tower.setPathImage(Path.IMAGE_CAVE_TOWER_01.getName());
                        	tower.loadImage();
                        	tower.getProjectiles().clear();
                        	tower.setAnimationChanged(false);
                		}

                	}

                }else {
                	if(tower.isAnimationChanged()) {
                		if(!(tower instanceof MagicTower)) {
                			tower.setPathImage(Path.IMAGE_CAVE_TOWER_01.getName());
                        	tower.loadImage();
                        	tower.getProjectiles().clear();
                        	tower.setAnimationChanged(false);
                			
                		}
            		}
                	
                }
                
            }
		}
		
		
		if(enemyBase != null) {
			enemyBase.draw(g);
		}
        
		
		//Die Lebensbalken der Lebewesen werden nur neuzeichnet wenn Einheiten der jeweiligen Partei auch existieren
		for(int i=0; i < friendlyLivingBeings.size();i++) {
			
			
			friendlyLivingBeings.get(i).draw(g);
			if(i==0) {
				beingLifeBar.drawLifeBarBorder(g, true); 
				if(friendlyLivingBeings.get(i).getHealth()>0) {
					beingLifeBar.drawLifeBar(g, friendlyLivingBeings.get(i).getHealth(), true);
				}
			}
				
		}
		
		
		for(int i=0; i < enemyLivingBeings.size();i++) {
			
			
			enemyLivingBeings.get(i).draw(g);
			if(i==0) {
				beingLifeBar.drawLifeBarBorder(g, false);
				if(enemyLivingBeings.get(i).getHealth()>0) {
					beingLifeBar.drawLifeBar(g, enemyLivingBeings.get(i).getHealth(), false);
				}
			}
				
		}

        
    }

}
