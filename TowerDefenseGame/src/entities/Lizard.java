package entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.GamePanel;
import utils.Path;

public class Lizard extends LivingBeing{
	//private static final Logger LOGGER = Logger.getLogger(Lizard.class.getName());
	private boolean friendly;
	private int attackingDistance;
	private int waitingDistance;
	
	public Lizard(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width,height, attack, health,friendly);
		this.friendly=friendly;
		this.attackingDistance=25;
		this.waitingDistance=100;
		super.addSpeed(5);
		super.setFrameDelay(100);
		super.setTotalDeathFrames(6);
		resetState(); 
		if(friendly) {
			super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
		}
		super.loadImage();
		//LOGGER.info("Lizard created: " + this);
	}
	
	public void resetState() {
        if(friendly && Beings.FRIENDLY_LIZARD != null) {
        	super.getRect().setX(Beings.FRIENDLY_LIZARD.getxPos());
        	super.getRect().setY(Beings.FRIENDLY_LIZARD.getyPos());
        	
        }else if(!friendly && Beings.ENEMY_LIZARD != null){
        	super.getRect().setX(Beings.ENEMY_LIZARD.getxPos());
        	super.getRect().setY(Beings.ENEMY_LIZARD.getyPos());
        }
        
        super.setDeathAnimationFrameCount(0);
        super.setDeathAnimationChanged(false);
        super.setDead(false); 
    }
	
	
	
	@Override
	public void update(GamePanel panel) {
		
		System.out.println("fr" + (Bases.FRIENDLY_CAVE.getBase().getRect().getWidth()-70));
		if (this.getHealth() > 0) {

			living(panel);

        } else if (!super.isDeathAnimationChanged()) {
        	if(friendly) {
        		super.setPathImage(Path.IMAGE_LIZARD_PLAYER_DEATH.getName());
    			
    		}else {
    			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_DEATH.getName());
    		}
           
            super.loadImage();
            super.setDeathAnimationChanged(true);
          
        } else {
        	super.addDeathAnimationFrameCount(1);
        	
        	int framesPerSecond = 60;
        	int framesForDeathAnimation = super.getTotalDeathFrames() * (framesPerSecond / (1000 / super.getFrameDelay()));
        	
            //System.out.println(framesForDeathAnimation);
        	if (super.getDeathAnimationFrameCount() >= framesForDeathAnimation) {
        		
                super.setDead(true);
                if(friendly && panel.getFriendlyWaitingBeings().contains(this)) {
                	panel.getFriendlyWaitingBeings().remove(this);
                }
                
                if(!friendly && panel.getEnemyWaitingBeings().contains(this)) {
                	panel.getEnemyWaitingBeings().remove(this);
                }
                
               
                
                //LOGGER.info("Lizard is dead: " + this);
            }
        }
	
		
	}
	
	
	private void living(GamePanel panel) {
		
		System.out.println(panel.getFriendlyWaitingBeings());
		System.out.println(panel.getEnemyWaitingBeings());
     	System.out.println("Warten-F " + panel.getFriendlyWaitingBeings().size());
     	System.out.println("Leben-F " + panel.getFriendlyLivingBeings().size());
     	System.out.println("Warten-E " + panel.getEnemyWaitingBeings().size());
     	System.out.println("Leben-E " + panel.getEnemyLivingBeings().size());
     	System.out.println("Leben-G " + (panel.getEnemyLivingBeings().size()+panel.getFriendlyLivingBeings().size()));
     	System.out.println();
		
		
		if(panel.getFriendlyLivingBeings().size() > 0 && panel.getEnemyLivingBeings().size() > 0) {
			
		
			int differenceMiddle=0;
			
			if(panel.getEnemyLivingBeings().get(0).getRect().getX() < 900) {
				differenceMiddle=  Math.abs(panel.getEnemyLivingBeings().get(0).getRect().getX()-900);
			}else {
				//differenceMiddle=Math.abs(panel.getLivingBeings().get(0).getRect().getX()-901);
			}
			
			
//			System.out.println("Friend " + (panel.getFriendlyLivingBeings().get(0).getRect().getX()+distance));
//			System.out.println("Enemy " + (panel.getEnemyLivingBeings().get(0).getRect().getX()-distance));
//			System.out.println(differenceMiddle);
//			System.out.println();
			
		
			if (this != panel.getFriendlyLivingBeings().get(0) &&  this != panel.getEnemyLivingBeings().get(0)|| 
					panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance 
					!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance ||
					panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance-differenceMiddle 
					!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance-differenceMiddle) {
				if(friendly) {
					super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
					
				}else {
					super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
				}
	            super.loadImage();
	            
	            if(this != panel.getFriendlyLivingBeings().get(0) &&  this != panel.getEnemyLivingBeings().get(0) && 
	            		(panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance 
	            				!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance
	            		||
						panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance-differenceMiddle 
						!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance-differenceMiddle) 
	            		|| ((friendly && this != panel.getFriendlyLivingBeings().get(0) 
	            		&& this.getRect().getX() < panel.getFriendlyLivingBeings().get(0).getRect().getX()-waitingDistance)
	            				|| (!friendly && this != panel.getEnemyLivingBeings().get(0) && this.getRect().getX() 
	            						> panel.getEnemyLivingBeings().get(0).getRect().getX()+waitingDistance ))) {
	            	
	            	//if(panel.getEnemyWaitingBeings().size() > 0) {
	            		
	            		boolean flag=false;
	            		for(LivingBeing being: panel.getFriendlyWaitingBeings()) {
	            			if(friendly && being.isFriendly() && this.getRect().getX()  
	            					>= being.getRect().getX()-waitingDistance) {
	            				flag=true;
	            			}
	            		}
	            		
	            		for(LivingBeing being: panel.getEnemyWaitingBeings()) { 
	            			if(!friendly && !being.isFriendly() && this.getRect().getX()  
	            					<= being.getRect().getX()+waitingDistance) {
	            				flag=true;
	            			}
	            		}
	            		
	            		if( (friendly && !flag)||
		            			(!friendly && !flag)){
		            		
	            			moveAndRemoveFromWaitingQueue(panel);	
		                 }else {
		                	 
		                	 //wait(panel); 
		                	 //moveAndRemoveFromWaitingQueue(panel);
		                	 
		                	 if(panel.getFriendlyWaitingBeings().size() > 0 && friendly) {
		                		 
		                		 
		                		 if((getRect().getX() < panel.getFriendlyWaitingBeings().get(
		                					 panel.getFriendlyWaitingBeings().size()-1).getRect().getX()-waitingDistance)
		                			 || this == panel.getFriendlyWaitingBeings().get(0)) {
		                			 
		                			 moveAndRemoveFromWaitingQueue(panel); 
		                		 }else {
		                			 wait(panel); 
		                		 }
		                		 
		                		
		                	 }else if (!friendly && panel.getEnemyWaitingBeings().size() > 0){
		                		 
		                		 if((getRect().getX() > panel.getEnemyWaitingBeings().get(
		                					 panel.getEnemyWaitingBeings().size()-1).getRect().getX()+waitingDistance)
		                		 || this == panel.getEnemyWaitingBeings().get(0)) {
		                			 
		                			 moveAndRemoveFromWaitingQueue(panel);
		                		 }else {
		                			 wait(panel); 
		                		 }
		                		 
		                		 
		                	 }else {

		                		 wait(panel); 
		                	 }
	
		                 }
	            	
	
	            }else if (this == panel.getFriendlyLivingBeings().get(0) ||  this == panel.getEnemyLivingBeings().get(0) &&
	            		(panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance 
	            				!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance
	            		||
						panel.getFriendlyLivingBeings().get(0).getRect().getX()+attackingDistance-differenceMiddle 
						!= panel.getEnemyLivingBeings().get(0).getRect().getX()-attackingDistance-differenceMiddle)) {
	            	
	            	moveAndRemoveFromWaitingQueue(panel);
	            	
	            	
	            }else if(this != panel.getFriendlyLivingBeings().get(0) &&  this != panel.getEnemyLivingBeings().get(0)) {
	            	wait(panel);
	            	//System.out.println(this);
	            	//System.out.println("Warten " + panel.getWaitingBeings().size());
	            	//System.out.println("Leben " + panel.getLivingBeings().size());
	            }
	
			}else {		
				
				attack(panel);
			}
		
		}else {
			
			if(friendly) {
				if(panel.getFriendlyLivingBeings().size() > 0) {
					if(super.getRect().getX() < 
					GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-50-waitingDistance 
					&& this == panel.getFriendlyLivingBeings().get(0)) {
						moveAndRemoveFromWaitingQueue(panel);
					}else if(this != panel.getFriendlyLivingBeings().get(0) ){
						
						boolean flag=false;
						
						for(LivingBeing being: panel.getFriendlyWaitingBeings()) { 
	            			if(this.getRect().getX()  >= being.getRect().getX()-waitingDistance && being != this) {
	            				flag=true;
	            		
	            			}
	            		}
						

	            		if(!flag || this == panel.getFriendlyWaitingBeings().get(0)){
	            			moveAndRemoveFromWaitingQueue(panel);	
	            		}else {
	            			
	            			wait(panel);
	            		}

					}else {
						wait(panel);
					}

				}
			}else {
					if(panel.getEnemyLivingBeings().size() > 0) {
						if(super.getRect().getX() > 200 && this == panel.getEnemyLivingBeings().get(0)) {
							moveAndRemoveFromWaitingQueue(panel);
						}else if(this != panel.getEnemyLivingBeings().get(0) ){
							
							boolean flag=false;
							
							for(LivingBeing being: panel.getEnemyWaitingBeings()) { 
		            			if(this.getRect().getX()  <= being.getRect().getX()+waitingDistance && being != this) {
		            				flag=true;
		            			}
		            		}
		            		
		            		if(!flag || this == panel.getEnemyWaitingBeings().get(0)){
		            			moveAndRemoveFromWaitingQueue(panel);	
		            		}else {
		            			wait(panel);
		            		}

						}else {
							wait(panel);
						}

					}
			}	
		}
	}
	
	
	private void attack(GamePanel panel) {
		if(friendly) {
			super.setPathImage(Path.IMAGE_LIZARD_PLAYER_ATTACK.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_ATTACK.getName());
		}
		
        super.loadImage();
        if(panel.getEnemyLivingBeings().get(0).isDeathAnimationChanged() == false) {
        	panel.getFriendlyLivingBeings().get(0).removeHealth(panel.getEnemyLivingBeings().get(0).getAttack()/10);
        }
        
        if(panel.getFriendlyLivingBeings().get(0).isDeathAnimationChanged() == false) {
        	panel.getEnemyLivingBeings().get(0).removeHealth(panel.getFriendlyLivingBeings().get(0).getAttack()/10);
        }
      
		
	}
	
	private void wait(GamePanel panel) {
		if(this.isFriendly() && !panel.getFriendlyWaitingBeings().contains(this)) {
    		panel.addFriendlyWaitingBeings(this);
    	}else if(!this.isFriendly() && !panel.getEnemyWaitingBeings().contains(this)) {
    		panel.addEnemyWaitingBeings(this);
    	}
    	
    	if(friendly) {
    		super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WAIT.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WAIT.getName());
		}
    	super.loadImage();
		
	}
	
	private void moveAndRemoveFromWaitingQueue(GamePanel panel) {
		move();
		if(friendly) {
    		super.setPathImage(Path.IMAGE_LIZARD_PLAYER_WALK.getName());
			
		}else {
			super.setPathImage(Path.IMAGE_LIZARD_ENEMY_WALK.getName());
		}
		
		super.loadImage();
		
		if(this.isFriendly() && panel.getFriendlyWaitingBeings().contains(this)) {
			panel.getFriendlyWaitingBeings().remove(this);
    	}else if(!this.isFriendly() && panel.getEnemyWaitingBeings().contains(this)) {
    		panel.getEnemyWaitingBeings().remove(this);
    	}
		
		
		//System.out.println("Warten " + panel.getWaitingBeings().size());
		
	}
	
	private void move() {
		if(friendly) {
			super.getRect().setX(super.getRect().getX()+super.getSpeed());
		}else {
			super.getRect().setX(super.getRect().getX()-super.getSpeed());
		}
	
	}
	

}
