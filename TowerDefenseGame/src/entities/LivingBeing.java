package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

import audio.AudioPlayer;
import game.Main;
import ui.GamePanel;
import utils.Path;

public abstract class LivingBeing extends Entity{
	
	private static final long serialVersionUID = 1L;
	
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
	private AudioPlayer attackingAudio;
	
	public LivingBeing(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		this.attack=attack;
		this.deathAnimationChanged = false;
        this.deathAnimationFrameCount = 0;
        this.dead = false;
	
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


	public AudioPlayer getAttackingAudio() {
		return attackingAudio;
	}

	public void setAttackingAudio(AudioPlayer player) {
		this.attackingAudio = player;
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

	@Override
	public void clear(Graphics g) {
		g.setColor(new Color(0, 0, 0, 0)); 
	    g.fillRect(super.getRect().getX(), super.getRect().getY(), super.getRect().getWidth(), super.getRect().getHeight());
	}
	
	
	public void deathStart() {
        setPathImage(deathPath.getName());
        loadImage();
        setDeathAnimationChanged(true);
        
        if(panel.getFriendlyLivingBeings().get(0).isDeathAnimationChanged() 
        		&& panel.getEnemyLivingBeings().get(0).isDeathAnimationChanged()) {
        	stopAttackSound();
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
            }
            
            if(!isFriendly() && panel.getEnemyWaitingBeings().contains(this)) {
            	panel.getEnemyWaitingBeings().remove(this);
            }
            
    	}
		
	}
	
	public void living() {
		
		if(panel.getFriendlyLivingBeings().size() > 0 && panel.getEnemyLivingBeings().size() > 0) {
			
		
			int differenceMiddle=0;
			
			if(panel.getEnemyLivingBeings().get(0).getRect().getX() < 900) {
				differenceMiddle=  Math.abs(panel.getEnemyLivingBeings().get(0).getRect().getX()-900);
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
	            		for(LivingBeing being: panel.getFriendlyWaitingBeings()) {
	            			if(isFriendly() && being.isFriendly() && this.getRect().getX()  
	            					>= being.getRect().getX()-waitingDistance) {
	            				flag=true;
	            			}
	            		}
	            		
	            		for(LivingBeing being: panel.getEnemyWaitingBeings()) { 
	            			if(!isFriendly() && !being.isFriendly() && this.getRect().getX()  
	            					<= being.getRect().getX()+waitingDistance) {
	            				flag=true;
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
		
		}else {
			
			if(isFriendly()) {
				if(panel.getFriendlyLivingBeings().size() > 0) {
					if(super.getRect().getX() < 
					GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-50-waitingDistance 
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
	            			moveAndRemoveFromWaitingQueue();	
	            		}else {
	            			
	            			waiting(); 
	            		}

					}else {
						waiting(); 
					}

				}
			}else {
					if(panel.getEnemyLivingBeings().size() > 0) {
						if(super.getRect().getX() > 200 && this == panel.getEnemyLivingBeings().get(0)) {
							moveAndRemoveFromWaitingQueue();
						}else if(this != panel.getEnemyLivingBeings().get(0) ){
							
							boolean flag=false;
							
							for(LivingBeing being: panel.getEnemyWaitingBeings()) { 
		            			if(this.getRect().getX()  <= being.getRect().getX()+waitingDistance && being != this) {
		            				flag=true;
		            			}
		            		}
		            		
		            		if(!flag || this == panel.getEnemyWaitingBeings().get(0)){
		            			moveAndRemoveFromWaitingQueue();	
		            		}else {
		            			waiting(); 
		            		}

						}else {
							waiting(); 
						}

					}
			}	
		}
	}
	
	
	private void attack() {

		setPathImage(attackingPath.getName());

        loadImage();
        if(panel.getEnemyLivingBeings().get(0).isDeathAnimationChanged() == false) {
        	
        	playAttackSound();
        	panel.getFriendlyLivingBeings().get(0).removeHealth(panel.getEnemyLivingBeings().get(0).getAttack()/10);
        }
        
        if(panel.getFriendlyLivingBeings().get(0).isDeathAnimationChanged() == false) {
        	
        	playAttackSound();
        	panel.getEnemyLivingBeings().get(0).removeHealth(panel.getFriendlyLivingBeings().get(0).getAttack()/10);
        }
      
		
	}
	
	private void waiting() {
		
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
		
		stopAttackSound();
		
		if(isFriendly()) {
			super.getRect().setX(super.getRect().getX()+getSpeed());
		}else {
			super.getRect().setX(super.getRect().getX()-getSpeed());
		}
	
	}
	
	
	private void playAttackSound() {
		if(attackingAudio.isPlay() == false && panel.getFriendlyLivingBeings().size() > 0) {
			attackingAudio.setPlay(true);
		}
		
	}
	
	
	private void stopAttackSound() {
		
		boolean flag=false;
		AudioPlayer certainPlayer=null;
		
		for(AudioPlayer player: Main.ATTACK_PLAYERS) {
			if(player.isPlay()) {
				certainPlayer=player;
				flag=true;
			}
		}
		
		if(flag) {
			
			if(panel.getFriendlyLivingBeings().size() > 0) {
				if(this == panel.getFriendlyLivingBeings().get(0)) {
					certainPlayer.setPlay(false);
				}
			}else if(panel.getEnemyLivingBeings().size() > 0) {
				if(this == panel.getEnemyLivingBeings().get(0)) {
					certainPlayer.setPlay(false);
				}
			}
    	
    	}
		
	}
	
	

}
