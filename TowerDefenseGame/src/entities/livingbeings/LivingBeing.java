package entities.livingbeings;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import audio.AudioPlayer;
import audio.AudioPlayers;
import entities.Entity;
import entities.bases.Bases;
import entities.towers.MagicTower;
import entities.towers.Tower;
import game.GamePanel;
import game.Main;
import utils.CoinValues;
import utils.Path;

public abstract class LivingBeing extends Entity{
	
	private static final long serialVersionUID = 1L;
	private static final int MIDDLE_HITBOX = 900;
	private int attack;
	private ImageIcon imageIcon;
	private String pathImage;
	private int speed; 
	private boolean dead;
	private boolean deathAnimationChanged;
    private int deathAnimationFrameCount;
    private int totalDeathFrames;  
    private int frameDelay;
    private int attackingDistance;
	private int waitingDistance;
	private GamePanel panel;
	private Path walkingPath;
	private Path attackingPath; 
	private Path waitingPath;
	private Path deathPath;
	private boolean movingState;
	private boolean attackingBase;
	private boolean buff;
	private LinkedList<AudioPlayer> attackingAudio;
	//private AudioPlayer attackingAudio;
	
	{
		attackingAudio=new LinkedList<>();
	}
	
	public LivingBeing(int xPos, int yPos, int width, int height, int attack, double health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		this.attack=attack;
		this.deathAnimationChanged = false;
        this.deathAnimationFrameCount = 0;
        this.dead = false;
	
	}
	

	public boolean isBuff() {
		return buff;
	}


	public void setBuff(boolean buff) {
		this.buff = buff;
	}


	public boolean isMovingState() {
		return movingState;
	}


	public void setMovingState(boolean movingState) {
		this.movingState = movingState;
	}


	public GamePanel getPanel() {
		return panel;
	}

	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}

	public Path getDeathPath() {
		return deathPath;
	}

	public void setDeathPath(Path deathPath) {
		this.deathPath = deathPath;
	}

	public Path getWalkingPath() {
		return walkingPath;
	}

	public void setWalkingPath(Path walkingPath) {
		this.walkingPath = walkingPath;
	}

	public Path getAttackingPath() {
		return attackingPath;
	}


	public void setAttackingPath(Path attackingPath) {
		this.attackingPath = attackingPath;
	}

	public Path getWaitingPath() {
		return waitingPath;
	}

	public void setWaitingPath(Path waitingPath) {
		this.waitingPath = waitingPath;
	}


	public LinkedList<AudioPlayer> getAttackingAudio() {
		return attackingAudio;
	}

	public void addAttackingAudio(AudioPlayer player) {
		this.attackingAudio.add(player);
	}
	
	public void clearAttackingAudio() {
		this.attackingAudio.clear();
	}

	public int getAttackingDistance() {
		return attackingDistance;
	}

	public void setAttackingDistance(int attackingDistance) {
		this.attackingDistance = attackingDistance;
	}

	public int getWaitingDistance() {
		return waitingDistance;
	}

	public void setWaitingDistance(int waitingDistance) {
		this.waitingDistance = waitingDistance;
	}

	public int getSpeed() {
		return speed;
	}


	public void addSpeed(int speed) {
		this.speed += speed;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public String getPathImage() {
		return pathImage;
	}


	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }
	
	
	public void setTotalDeathFrames(int totalDeathFrames) {
        this.totalDeathFrames = totalDeathFrames;
    }
	

	public int getTotalDeathFrames() {
		return totalDeathFrames;
	}

	public int getFrameDelay() {
		return frameDelay;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isDeathAnimationChanged() {
		return deathAnimationChanged;
	}

	public void setDeathAnimationChanged(boolean deathAnimationChanged) {
		this.deathAnimationChanged = deathAnimationChanged;
	}

	public int getDeathAnimationFrameCount() {
		return deathAnimationFrameCount;
	}
	
	public void setDeathAnimationFrameCount(int deathAnimationFrameCount) {
		this.deathAnimationFrameCount = deathAnimationFrameCount;
	}

	public void addDeathAnimationFrameCount(int deathAnimationFrameCount) {
		this.deathAnimationFrameCount += deathAnimationFrameCount;
	}
	
	
	private void resetDeathAnimation() {
        this.deathAnimationFrameCount = 0;
        this.deathAnimationChanged = false;
        this.dead = false;
    }
	
	
	public void resetState(Beings being) {
        if(isFriendly() && being != null) {
        	super.getRect().setX(being.getxPos());
        	super.getRect().setY(being.getyPos());
        	
        }else if(!isFriendly() && being != null){
        	super.getRect().setX(being.getxPos());
        	super.getRect().setY(being.getyPos());
        }
        
        resetDeathAnimation(); 
    }

	public void loadImage() {
		imageIcon = new ImageIcon(getPathImage());
	}
	
	@Override
	public void update(GamePanel panel) {
		
		if(getPanel() == null) {
			setPanel(panel);
		}
		
		
		enemyNearCave();
		
		if (this.getHealth() > 0) {
			if(isFriendly()) {
				living();
			}else {
				living();
			}
			
        } else if (!isDeathAnimationChanged()) {
        	if(isFriendly()) {
        		deathStart();
        	}else {
        		
        		
        		if(this instanceof NormalLizard) {
        			panel.setCoins(panel.getCoins()+CoinValues.NORMAL_LIZARD_LOOT.getValue());
        		}else if(this instanceof IntermediateLizard) {
        			panel.setCoins(panel.getCoins()+CoinValues.INTERMEDIATE_LIZARD_LOOT.getValue());
        		}else if(this instanceof AdvancedLizard) {
        			panel.setCoins(panel.getCoins()+CoinValues.ADVANCED_LIZARD_LOOT.getValue());
        		}else if(this instanceof NormalBear) {
        			panel.setCoins(panel.getCoins()+CoinValues.NORMAL_BEAR_LOOT.getValue());
        		}
        		
        		panel.getWaveManager().updateWaveLabel();
            	panel.getImageIconManager().coinTestingForAllButRemove();
        		deathStart();
        	}
        	
        } else {
        	death();
        	
        }

	}

	@Override
	public void draw(Graphics g) {
		if (imageIcon != null) {
            imageIcon.paintIcon(null, g, getRect().getX(), getRect().getY());
        }
		
	}
	
	private void enemyNearCave() {
		int dangerDistance = 50;
		
		
		if(panel.getFriendlyBase().getHealth() > 0 && panel.getEnemyBase().getHealth() > 0) {
			
			
			if(panel.getEnemyLivingBeings().size() > 0) {
				
				if(!isFriendly() && this == panel.getEnemyLivingBeings().get(0) 
						&&this.getRect().getX()<= panel.getFriendlyBase().getRect().getWidth()+dangerDistance 
						&& !AudioPlayers.DANGER_PLAYER.getPlayer().isPlay()) {
					AudioPlayers.BACKGROUND_PLAYER.getPlayer().stop();
					AudioPlayers.DANGER_PLAYER.getPlayer().setPlay(true);
				}else if(panel.getEnemyLivingBeings().size() > 1) {
					
					if(!AudioPlayers.BACKGROUND_PLAYER.getPlayer().isPlay() && !isFriendly() && 
							panel.getEnemyLivingBeings().get(0).getRect().getX()> 
					panel.getFriendlyBase().getRect().getWidth()+dangerDistance
							&& panel.getEnemyLivingBeings().get(1).getRect().getX()> 
					panel.getFriendlyBase().getRect().getWidth()+(dangerDistance*4) ){
						
						AudioPlayers.DANGER_PLAYER.getPlayer().stop();
						AudioPlayers.BACKGROUND_PLAYER.getPlayer().setPlay(true);
						attackingBase=false;
						
						for(AudioPlayer player: attackingAudio) {
							if(!player.isLoop() && player.isPlay()) {
								player.stop();
							}
						}
						
					}
				}

			}else if(!AudioPlayers.BACKGROUND_PLAYER.getPlayer().isPlay()){
				AudioPlayers.DANGER_PLAYER.getPlayer().stop();
				AudioPlayers.BACKGROUND_PLAYER.getPlayer().setPlay(true);
				attackingBase=false;
				for(AudioPlayer player: attackingAudio) {
					if(!player.isLoop() && player.isPlay()) {
						player.stop();
					}
				}
			}
			
		}
		
		
	}

	
	
	public void deathStart() {
        setPathImage(deathPath.getName());
        loadImage();
        setDeathAnimationChanged(true);
        
        
        if(panel.getFriendlyLivingBeings().size() > 0 && panel.getEnemyLivingBeings().size() > 0) {
        	
        	if(panel.getFriendlyLivingBeings().get(0).isDeathAnimationChanged() 
            		&& panel.getEnemyLivingBeings().get(0).isDeathAnimationChanged()) {
            	stopAttackSound();
            }
        	
        }
        
		
	}
	
	
	public void death() {
		addDeathAnimationFrameCount(1);
    	
    	int framesPerSecond = 60;
    	int framesForDeathAnimation = getTotalDeathFrames() * (framesPerSecond / (1000 / getFrameDelay()));
    	
        //System.out.println(framesForDeathAnimation);
    	if (getDeathAnimationFrameCount() >= framesForDeathAnimation) {
    		
            setDead(true);
            if(isFriendly() && panel.getFriendlyWaitingBeings().contains(this)) {
            	panel.getFriendlyWaitingBeings().remove(this);
            }else if(!isFriendly() && panel.getEnemyWaitingBeings().contains(this)) {
            	panel.getEnemyWaitingBeings().remove(this);
            }
            
    	}
		
	}
	
	public void living() {
		
		if(panel.getFriendlyLivingBeings().size() > 0 && panel.getEnemyLivingBeings().size() > 0) {
			
			friendlyAndEnemyBeingsExist();
		
		}else {
			
			if(isFriendly()) {
				onlyFriendlyBeingsExist(); 
			}else {
				onlyEnemyBeingsExist();
			}	
		}
	}
	
	
	private void onlyFriendlyBeingsExist() {
		if(panel.getFriendlyLivingBeings().size() > 0) {
			if(super.getRect().getX() < 
			GamePanel.SCREENSIZE.width-panel.getEnemyBase().getRect().getWidth()-waitingDistance 
			&& this == panel.getFriendlyLivingBeings().get(0)) {
				moveAndRemoveFromWaitingQueue();
			}else if(this != panel.getFriendlyLivingBeings().get(0) ){
				
				boolean flag=false;
				
				for(LivingBeing being: panel.getFriendlyWaitingBeings()) { 
        			if(this.getRect().getX()  >= being.getRect().getX()-waitingDistance && being != this) {
        				flag=true;
        		
        			}
        		}
				

        		if(!flag || this == panel.getFriendlyWaitingBeings().get(0)){
        			
        			
        			if(panel.getFriendlyLivingBeings().size() > 0) {
        				
        				if(this != panel.getFriendlyLivingBeings().get(0) && panel.getFriendlyLivingBeings().get(0).getRect().getX() 
        						>= GamePanel.SCREENSIZE.width-panel.getEnemyBase().getRect().getWidth()
        						-waitingDistance) {
            				
            				if(getRect().getX() < panel.getFriendlyLivingBeings().get(0).getRect().getX()-waitingDistance) {
            					moveAndRemoveFromWaitingQueue();	
            				}else {
            					waiting(); 
            				}
            				
            				
            			}else {
            				moveAndRemoveFromWaitingQueue();	
            			}
        				
        			}
        		}else {
        			
        			waiting(); 
        		}

			}else {
				attack();
			}

		}
		
	}
	
	private void onlyEnemyBeingsExist() {
		
		int caveDistance = 30;
		
		if(panel.getEnemyLivingBeings().size() > 0) {
			if(super.getRect().getX() > panel.getFriendlyBase().getRect().getWidth()-caveDistance && this == panel.getEnemyLivingBeings().get(0)) {
				moveAndRemoveFromWaitingQueue();
			}else if(this != panel.getEnemyLivingBeings().get(0) ){
				
				boolean flag=false;
				
				for(LivingBeing being: panel.getEnemyWaitingBeings()) { 
        			if(this.getRect().getX()  <= being.getRect().getX()+waitingDistance && being != this) {
        				flag=true;
        			}
        		}
        		
        		if(!flag || this == panel.getEnemyWaitingBeings().get(0)){
        			
        			
        			if(panel.getEnemyLivingBeings().size() > 0) {
        				
        				if(this != panel.getEnemyLivingBeings().get(0) && panel.getEnemyLivingBeings().get(0).getRect().getX() 
	            				<= panel.getFriendlyBase().getRect().getWidth()-caveDistance) {
            				
            				if(getRect().getX() > panel.getEnemyLivingBeings().get(0).getRect().getX()+waitingDistance) {
            					moveAndRemoveFromWaitingQueue();	
            				}else {
            					waiting(); 
            				}
            				
            				
            			}else {
            				moveAndRemoveFromWaitingQueue();	
            			}
        				
        			}
        			
        		}else {
        			waiting(); 
        		}

			}else {
				attack();
				//waiting(); 
			}

		}
		
	}
	
	
	private void friendlyAndEnemyBeingsExist() {
		
//		System.out.println("ENEMY BASE " + panel.getEnemyBase().getHealth());
//    	System.out.println("FRIENDLY BASE " + panel.getFriendlyBase().getHealth());
		
		//System.out.println(panel.getBaseWidth());
		int differenceMiddle=0;
		
		if(panel.getEnemyLivingBeings().get(0).getRect().getX() < MIDDLE_HITBOX) {
			differenceMiddle=  Math.abs(panel.getEnemyLivingBeings().get(0).getRect().getX()-MIDDLE_HITBOX);
		}
		
	
		if (this != panel.getFriendlyLivingBeings().get(0) &&  this != panel.getEnemyLivingBeings().get(0)|| 
				panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance 
				!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance ||
				panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance-differenceMiddle 
				!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance-differenceMiddle) {
			
			setPathImage(walkingPath.getName());
            loadImage();
            
            if(this != panel.getFriendlyLivingBeings().get(0) &&  this != panel.getEnemyLivingBeings().get(0) && 
            		(panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance 
            				!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance
            		||
					panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance-differenceMiddle 
					!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance-differenceMiddle) 
            		|| ((isFriendly() && this != panel.getFriendlyLivingBeings().get(0) 
            		&& this.getRect().getX() < panel.getFriendlyLivingBeings().get(0).getRect().getX()-waitingDistance)
            				|| (!isFriendly() && this != panel.getEnemyLivingBeings().get(0) && this.getRect().getX() 
            						> panel.getEnemyLivingBeings().get(0).getRect().getX()+waitingDistance ))) {
            	
            		boolean flag=false;
            		if(isFriendly()) {
            			for(LivingBeing being: panel.getFriendlyWaitingBeings()) {
	            			if(isFriendly() && being.isFriendly() && this.getRect().getX()  
	            					>= being.getRect().getX()-waitingDistance) {
	            				flag=true;
	            			}
	            		}
            			
            			
            		}else {
            			
            			for(LivingBeing being: panel.getEnemyWaitingBeings()) { 
	            			if(!isFriendly() && !being.isFriendly() && this.getRect().getX()  
	            					<= being.getRect().getX()+waitingDistance) {
	            				flag=true;
	            			}
	            		}
            			
            		}
            		if( (isFriendly() && !flag)||
	            			(!isFriendly() && !flag)){
	            		
            			moveAndRemoveFromWaitingQueue();	
	                 }else {
	                	 
	                	 if(panel.getFriendlyWaitingBeings().size() > 0 && isFriendly()) {
	                		 
	                		 
	                		 if((getRect().getX() < panel.getFriendlyWaitingBeings().get(
	                					 panel.getFriendlyWaitingBeings().size()-1).getRect().getX()-waitingDistance)
	                			 || this == panel.getFriendlyWaitingBeings().get(0)) {
	                			 
	                			 moveAndRemoveFromWaitingQueue(); 
	                		 }else {
	                			 waiting();  
	                		 }
	                		 
	                		
	                	 }else if (!isFriendly() && panel.getEnemyWaitingBeings().size() > 0){
	                		 
	                		 if((getRect().getX() > panel.getEnemyWaitingBeings().get(
	                					 panel.getEnemyWaitingBeings().size()-1).getRect().getX()+waitingDistance)
	                		 || this == panel.getEnemyWaitingBeings().get(0)) {
	                			 
	                			 moveAndRemoveFromWaitingQueue();
	                		 }else {
	                			 waiting();  
	                		 }
	                		 
	                		 
	                	 }else {

	                		 waiting(); 
	                	 }

	                 }
            	

            }else if (this == panel.getFriendlyLivingBeings().get(0) ||  this == panel.getEnemyLivingBeings().get(0) &&
            		(panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance 
            				!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance
            		||
					panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance-differenceMiddle 
					!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance-differenceMiddle)) {
            	
            	moveAndRemoveFromWaitingQueue();
            	
            	
            }else if(this != panel.getFriendlyLivingBeings().get(0) &&  this != panel.getEnemyLivingBeings().get(0)) {
            	waiting(); 
     
            }

		}else {		
			
			attack();
		}
		
	}
	
	
	private void attack() {
		
		movingState=false;

		setPathImage(attackingPath.getName());

        loadImage();
        
        if(panel.getEnemyLivingBeings().size()>0 && panel.getFriendlyLivingBeings().size()>0) {
        	
        	if(panel.getEnemyLivingBeings().get(0).isDeathAnimationChanged() == false) {
            	
            	playAttackSound();
            	
            	int damage = (int) ((panel.getEnemyLivingBeings().get(0).getAttack() / 10.0));
            	panel.getFriendlyLivingBeings().get(0).removeHealth(damage);
            }
            
            if(panel.getFriendlyLivingBeings().get(0).isDeathAnimationChanged() == false) {
            	
            	playAttackSound();
            	
            	double multiplier = 1;

            	if(panel.getTowers().size()> 0 && !panel.getFriendlyLivingBeings().get(0).isBuff()) {
            		
            		boolean alreadyThere = false;
            		double premult = 1;
					
					for(Tower tower :panel.getTowers()) {
						if(tower instanceof MagicTower) {
							alreadyThere=true;
							premult=((MagicTower) tower).getBuff();
						}
						
					}
					
					if(alreadyThere) {
						multiplier=premult;
						panel.getFriendlyLivingBeings().get(0).setBuff(true);
					}
            	}else {
            		panel.getFriendlyLivingBeings().get(0).setBuff(false);
            	}
            	
            	double damage = ((panel.getFriendlyLivingBeings().get(0).getAttack() / 10.0) * multiplier);
            	
            	panel.getEnemyLivingBeings().get(0).removeHealth(damage);
            }
        	
        }else {
        	
        	if(panel.getEnemyBase().getHealth() > 0 && panel.getFriendlyBase().getHealth() > 0) {
        		playAttackSound();
        	}else {
        		stopAttackSound();
        	}
        	
//        	System.out.println("ENEMY BASE " + panel.getEnemyBase().getHealth());
//        	System.out.println("FRIENDLY BASE " + panel.getFriendlyBase().getHealth());
        	//playAttackSound();
        	
        	if(isFriendly() && panel.getEnemyBase().getHealth() > 0) {
        		panel.getEnemyBase().removeHealth(panel.getFriendlyLivingBeings().get(0).getAttack()/10);
        	}else if (!isFriendly() && panel.getFriendlyBase().getHealth() > 0){
        		panel.getFriendlyBase().removeHealth(panel.getEnemyLivingBeings().get(0).getAttack()/10);
        	}
        	
        }
        
        
      
		
	}
	
	private void waiting() {
		movingState=false;
		stopAttackSound();
		
		if(this.isFriendly() && !panel.getFriendlyWaitingBeings().contains(this)) {
    		panel.addFriendlyWaitingBeings(this);
    	}else if(!this.isFriendly() && !panel.getEnemyWaitingBeings().contains(this)) {
    		panel.addEnemyWaitingBeings(this);
    	}

        setPathImage(waitingPath.getName());
    	loadImage();
	}
	
	private void moveAndRemoveFromWaitingQueue() {
		move();
		
    	setPathImage(walkingPath.getName());
		loadImage();
		
		if(this.isFriendly() && panel.getFriendlyWaitingBeings().contains(this)) {
			panel.getFriendlyWaitingBeings().remove(this);
    	}else if(!this.isFriendly() && panel.getEnemyWaitingBeings().contains(this)) {
    		panel.getEnemyWaitingBeings().remove(this);
    	}
		
		
		//System.out.println("Warten " + panel.getWaitingBeings().size());
		
	}
	
	private void move() {
		movingState=true;
		stopAttackSound();
		
		if(isFriendly()) {
			super.getRect().setX(super.getRect().getX()+getSpeed());
		}else {
			super.getRect().setX(super.getRect().getX()-getSpeed());
		}
	
	}
	
	
	private void playAttackSound() {
		
		if(panel.getFriendlyBase().getHealth() > 0 && panel.getEnemyBase().getHealth() > 0) {
			for(AudioPlayer audio : attackingAudio) {
				
				if(audio.isPlay() == false && panel.getFriendlyLivingBeings().size() > 0) {
					audio.setPlay(true);
				}else if (audio.isPlay() == false && !isFriendly()) {
					if(panel.getEnemyLivingBeings().get(0) == this && 
							super.getRect().getX() <= panel.getFriendlyBase().getRect().getWidth()) {
						
							if(audio.isLoop()) {
								audio.setPlay(true);
							}else if(!attackingBase){
								audio.setPlay(true);
								attackingBase=true;
							}
							
					}
				}else if (audio.isPlay() == false && isFriendly()) {
					if(panel.getFriendlyLivingBeings().get(0) == this && 
					super.getRect().getX() > GamePanel.SCREENSIZE.width-panel.getEnemyBase().getRect().getWidth()
					-50-waitingDistance) {
						
						if(audio.isLoop()) {
							audio.setPlay(true);
						}else if(!attackingBase){
							audio.setPlay(true);
							attackingBase=true;
							
						}
					}
				}else if(!audio.isLoop() && attackingBase && panel.getFriendlyLivingBeings().size() > 0) {
					audio.setPlay(true);
					attackingBase=false;
				}
				

			}
			
		}else {
			stopAttackSound();
		}
		
		
		

		
	}
	
	
	private void stopAttackSound() {
		
	
		//AudioPlayer certainPlayer=null;
		LinkedList<AudioPlayer> certainPlayers = new LinkedList<>();
		
		for(AudioPlayer player: Main.ATTACK_PLAYERS) {
			if(player.isPlay()) {
				certainPlayers.add(player);
				//certainPlayer=player;
			
			}
		}
		
		if(certainPlayers.size() > 0) {
			
			if(panel.getFriendlyLivingBeings().size() > 0) {
				if(this == panel.getFriendlyLivingBeings().get(0)) {
					for(AudioPlayer player: certainPlayers) {
						player.setPlay(false);
					}
					
				}
			}else if(panel.getEnemyLivingBeings().size() > 0) {
				if(this == panel.getEnemyLivingBeings().get(0)) {
					for(AudioPlayer player: certainPlayers) {
						player.setPlay(false);
					}
				}
			}
    	
    	}
		
	}
	
	

}
